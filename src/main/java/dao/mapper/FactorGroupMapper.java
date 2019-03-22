package dao.mapper;

import domain.FactorGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * created by chenminqing
 */
public interface FactorGroupMapper {

    List<Map> findListByNameIfAbsent(@Param("name") String name);

    int deleteByPrimaryKey(@Param("name") String name);


}
