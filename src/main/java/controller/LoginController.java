package controller;


import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.StudentService;
import service.User;

@Controller
@RequestMapping("api")
@Api(value = "测试类",tags = "测试接口")
public class LoginController {
    @ApiOperation(value = "获取在线操作员")
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
}