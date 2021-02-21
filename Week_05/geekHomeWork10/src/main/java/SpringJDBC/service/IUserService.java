package SpringJDBC.service;

import SpringJDBC.entity.User;

import java.util.List;

/**
 * @author Matthew
 * @date
 */
public interface IUserService
{
	List<User> findUsers();
	boolean insertUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(String name);
	long saveBatch(int count);
}
