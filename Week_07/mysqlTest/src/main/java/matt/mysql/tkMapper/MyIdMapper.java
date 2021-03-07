package matt.mysql.tkMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.additional.idlist.IdListProvider;
import tk.mybatis.mapper.common.IdsMapper;

import java.util.List;

/**
 * @author Matthew
 * @date 2021-03-07 22:02
 */
public interface MyIdMapper<T> extends IdsMapper<T> {

    @SelectProvider(
            type = IdListProvider.class,
            method = "dynamicSql"
    )
    List<T> selectByIdList(@Param("idList") List<?> var1);

}
