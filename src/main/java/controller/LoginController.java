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
@RequestMapping("test")
@Api(value = "测试类",tags = "测试接口")
public class LoginController {
    @Autowired
    private StudentService testService;
    @RequestMapping(value = "testData",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation("测试读写分离")
    public String testDateSource(
            @ApiParam(name = "userCode",value = "用户id",required = true)
            @RequestParam Integer userCode){
        return "oo";
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(LoginController.class, args);
    }
}