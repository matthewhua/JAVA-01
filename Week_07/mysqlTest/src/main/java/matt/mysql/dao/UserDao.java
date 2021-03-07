package matt.mysql.dao;

import matt.mysql.entity.User;
import matt.mysql.tkMapper.GenericDao;
import org.springframework.stereotype.Repository;

/**
 * @author Matthew
 * @date 2021-03-07 21:41
 */
@Repository
public interface UserDao extends GenericDao<User> {
}
