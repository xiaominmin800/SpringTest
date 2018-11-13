package service;

import dao.mapper.FactorGroupMapper;
import domain.FactorGroup;
import io.netty.util.internal.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;


/**
 * created by chenminqing
 */
@Slf4j
@Service
public class groupService {
    @Autowired
    private FactorGroupMapper factorGroupMapper;
    public String getFactor(String name){

        List<FactorGroup> groups = factorGroupMapper.findListByNameIfAbsent(name);
        log.info("这个数据是：：：："+groups.toString());
        String names="";
        if (!ObjectUtils.isEmpty(groups))
        {
             names = groups.get(0).getName().toString();
        }
        else {
            names = "该组不存在";
        }

        log.info(names);
        return names;
    }

    public String delFactor(String name){

        String content="";

        List<FactorGroup> groups = factorGroupMapper.findListByNameIfAbsent(name);
        log.info("这个数据是：：：："+groups.toString());

        if (ObjectUtils.isEmpty(groups))
        {
            content = "该组不存在";
        }
        else {
            int conut = factorGroupMapper.deleteByPrimaryKey(name);
            content = "删除行为："+conut;
        }
        return content;

    }
}
