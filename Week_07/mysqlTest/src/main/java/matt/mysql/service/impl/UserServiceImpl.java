package matt.mysql.service.impl;

import matt.mysql.entity.User;
import matt.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Matthew
 * @date 2021-03-07 0:38
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public int[] addBatchUser(List<User> users) {
        System.out.println("开始操作入库。。。");
        ExecutorService service = Executors.newCachedThreadPool();
        String sql = "insert into user(nickname, PassWord) values (:nickname, :password)";
        long start = System.currentTimeMillis();
        Future<int[]> future = service.submit(() -> {
            System.out.println("开始执行");
            int[] result = namedParameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(users));
            System.out.println("执行结束。。。" + (System.currentTimeMillis() - start) / 1000 + "秒");
            return result;
        });
        int[] exeResult = null;
        try {
            exeResult = (int[]) future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("批量插入耗时："+(System.currentTimeMillis()-start));
        return exeResult;
    }
}
