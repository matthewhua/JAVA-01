package matt.mysql.service;

import matt.mysql.entity.User;

import java.util.List;

/**
 * @author Matthew
 * @date 2021-03-07 0:36
 */
public interface UserService {

    int addUser(User user);

    int[] addBatchUser(List<User> users);
}
