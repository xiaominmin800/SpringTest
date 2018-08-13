package controller;

import com.sun.deploy.association.utility.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.groupService;

/**
 * created by chenminqing
 */
@Controller
@RequestMapping("api")
@Api(value = "测试类",tags = "假设返回接口")
public class FactorsController {

    @Autowired
    private groupService groupService;
    @ApiOperation(value = "删除factors")
    @ResponseBody
    @RequestMapping(value =  "group/delFactor", method = RequestMethod.DELETE)
    public String delFactor(@ApiParam(value = "id") @RequestParam(value="id") Integer id) {
        //return "nnn";

        return  groupService.delFactor(id);
    }
}
