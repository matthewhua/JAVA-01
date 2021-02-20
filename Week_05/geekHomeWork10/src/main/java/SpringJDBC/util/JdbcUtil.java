package SpringJDBC.util;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


import java.sql.*;

/**
 *  JDBC连接
 *
 * @author Matthew
 * @date 2021-02-21 1:56
 */
@Component
public class JdbcUtil {


    @Value("${jdbc.mysql.url}")
    private  String url;
    @Value("${jdbc.mysql.username}")
    private  String userName;
    @Value( "${jdbc.mysql.password}")
    private  String passWord;
    @PostConstruct
    public void initJdbcDriver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getJdbcConnection() throws SQLException {
        return DriverManager.getConnection(url,userName,passWord);
    }

    public  void close(ResultSet resultSet,Statement statement,Connection connection){
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
