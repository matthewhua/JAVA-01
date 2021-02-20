package SpringJDBC.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resources;
import java.sql.*;
/**
 * Hikari 连接池
 *
 * @author Matthew
 * @date 2021-02-21 2:08
 */
@Repository
public class HikariUtil {
    @Value("${jdbc.mysql.url}")
    private  String url;
    @Value("${jdbc.mysql.username}")
    private  String userName;
    @Value("${jdbc.mysql.password}")
    private  String passWord;
    private HikariDataSource hikariDataSource;
    @PostConstruct
    public void initJdbcDriver(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(passWord);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setIdleTimeout(50000);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setConnectionTimeout(20000);
        hikariConfig.setPoolName("MyHikariPool");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }
    public  Connection getJdbcConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
    public  void close(ResultSet resultSet, Statement statement, Connection connection){
        closeResult(resultSet);
        closeSeesion(statement);
        closeConnection(connection);
    }
    public  void closeResult(ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public  void closeSeesion(Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public  void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
