package matt.mysql.tkMapper;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Matthew
 * @date 2021-03-07 21:43
 */
public interface GenericDao<T> extends Mapper<T>, MySqlMapper<T>, MyIdMapper<T>  {
}
