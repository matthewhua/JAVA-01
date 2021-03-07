package matt.dataSource.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.*;

/**
 * @author Matthew
 * @date 2021-03-06 20:48
 */
@ConfigurationProperties(prefix = "matt")
public class DataSources {

    private Map<String, DataSourceProperties> dataSources = new HashMap<>();

    private String main;

    public Map<String, DataSourceProperties> getDataSources() {
        return dataSources;
    }


    public void setDataSources(Map<String, DataSourceProperties> dataSources) {
        this.dataSources = dataSources;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public List<String> getSlaves() {
        Set<String> allDatasource = dataSources.keySet();
        allDatasource.remove(main);
        return new ArrayList<>(allDatasource);
    }
}
