import matt.mysql.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Matthew
 * @date 2021-03-06 0:15
 */
public class BatchInsert {
    private static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);


    //手写jdbc
/*    static {
        //预热
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection(true);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //JDBCUtils.closeJDBCResourceQuiet(connection, null, null);
        }
    }*/
}
