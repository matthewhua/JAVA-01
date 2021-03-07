package matt.mysql.service.impl;

import matt.dataSource.annotation.SwitchDs;
import matt.mysql.dao.UserDao;
import matt.mysql.entity.User;
import matt.mysql.service.IReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Matthew
 * @date 2021-03-07 21:39
 */
@Service
public class IReadServiceImpl implements IReadService {



    @Autowired
    private UserDao userDao;

    @Override
    @SwitchDs(readOnly = true)
    public List<User> readFromSlaves() {
        return userDao.selectAll();
    }

    @Override
    @SwitchDs(value = "ds2")
    public List<User> readFromDs2() {
        return userDao.selectAll();
    }

    @Override
    public List<User> readFromMain() {
        return userDao.selectAll();
    }

    public static void main(String[] args) {
        User u = new User();
        u.setNickname("111");
        u.setPassword("2222");
        List<User> list = new LinkedList<>();
        list.add(u);
        System.out.println("====>" + list);
    }
}
