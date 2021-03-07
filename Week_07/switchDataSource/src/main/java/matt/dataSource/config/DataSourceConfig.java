package matt.dataSource.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import matt.dataSource.aspect.MyDataSourceAspect;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Matthew
 * @date 2021-03-07 20:51
 */
@Configuration
@EnableConfigurationProperties(DataSources.class)
public class DataSourceConfig {

    @Autowired
    private DataSources sources;


    @Bean
    @Primary
    @ConditionalOnProperty(prefix = "matt", name = "main", havingValue = "ds1")
    public DynamicDataSource dataSource() {
        final Map<Object, Object> targetDataSources = new HashMap<>();
        Map<String, DataSourceProperties> dataSources = sources.getDataSources();
        dataSources.forEach((key, dataSourceProperties) -> {

            String url = dataSourceProperties.getUrl();
            String driverClassName = dataSourceProperties.getDriverClassName();
            String username = dataSourceProperties.getUsername();
            String password = dataSourceProperties.getPassword();

            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(url); //url
            hikariConfig.setUsername(username); //用户名
            hikariConfig.setPassword(password); //密码
            hikariConfig.setDriverClassName(driverClassName);
            final DataSource dataSource;
            try {
                dataSource = new HikariDataSource(hikariConfig);
            } catch (Exception e) {
                throw new RuntimeException("数据源错误");
            }

            targetDataSources.put(key, dataSource);
        });

        final DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);

        final String defaultTarget = sources.getMain();

        if (StringUtils.isNotBlank(defaultTarget) && targetDataSources.containsKey(defaultTarget)) {
            dynamicDataSource.setDefaultTargetDataSource(targetDataSources.get(defaultTarget));
        }

        return dynamicDataSource;
    }



    @Bean
    @ConditionalOnMissingBean(SqlSessionFactory.class)
    @ConditionalOnBean(DynamicDataSource.class)
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) throws Exception{

        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dynamicDataSource);

        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resourcePatternResolver.getResources("classpath*:mapper/*.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.setConfiguration(configuration);
        return sqlSessionFactory.getObject();
    }


    @Bean
    @ConditionalOnMissingBean(DataSourceTransactionManager.class)
    @ConditionalOnBean(DynamicDataSource.class)
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean
    @ConditionalOnBean(DynamicDataSource.class)
    public MyDataSourceAspect mySqLDataSourceAspect() throws Exception {
        return new MyDataSourceAspect();
    }
}