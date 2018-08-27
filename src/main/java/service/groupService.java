package service;

import dao.mapper.FactorGroupMapper;
import domain.FactorGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * created by chenminqing
 */
@Slf4j
@Service
public class groupService {
    @Autowired
    private FactorGroupMapper factorGroupMapper;
    public String delFactor(String name){

        List<FactorGroup> groups = factorGroupMapper.findListByNameIfAbsent(name);
        String names = groups.get(0).getName().toString();
        log.info(names);
        return names;
    }
}
