package service;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@ApiModel(value="用户信息")
public class User {
    @ApiModelProperty("用户id")
    private Integer userCode;
    @ApiModelProperty("用户类型")
    private String userType;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("用户手机号")
    private String mobileNumber;
}
