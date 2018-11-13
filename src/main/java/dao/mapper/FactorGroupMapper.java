package dao.mapper;

import domain.FactorGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * created by chenminqing
 */
public interface FactorGroupMapper {

    List<FactorGroup> findListByNameIfAbsent( @Param("name") String name);

    int deleteByPrimaryKey(@Param("name") String name);


}
