package controller;


import baseinfo.LogUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.StudentService;
import service.User;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

//@RestController
@Controller
@RequestMapping("api")
@Api(value = "测试类",tags = "测试接口")
public class LoginController {
    @ApiOperation(value = "获取在线操作员")
    @ResponseBody
    @RequestMapping(value = "/getOperator", method = RequestMethod.GET)
    public String getOperator(@ApiParam(value = "操作员编号", required = true) @RequestParam(value = "operatorNo") String operatorNo,
                              @ApiParam(value = "当前页") @RequestParam(value = "pageName",required = false) Integer pageName,
                              @ApiParam(value = "每页显示数量") @RequestParam(value = "pageSize",required = false) Integer pageSize) {
        if (pageName != null){
            if (pageSize != null) {
                return "操作员编号: " + operatorNo + ",当前页码: " +pageName + ",每页显示数量: " + pageSize;
            } else {
                return "操作员编号: " + operatorNo + ",当前页码: " +pageName;
            }
        }
        return "操作员编号: " + operatorNo;
    }

    @ApiOperation("添加备注")
    @ResponseBody
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public Boolean addremark(@RequestBody(required = true) User content){
        User user=new User();
        try {
            user.setUserCode(content.getUserCode());
            user.setMobileNumber(content.getMobileNumber());
            user.setUserName(content.getUserName());
            user.setUserType(content.getUserType());
            Logger.getLogger("获取的user信息为：").info(content.toString());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}