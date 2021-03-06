package service;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@ApiModel(value="用户信息")
@Data
public class User {
    @ApiModelProperty("用户id")
    private Integer userCode;
    @ApiModelProperty("用户类型")
    private String userType;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("用户手机号")
    private String mobileNumber;

    public Integer getUserCode() {
        return userCode;
    }

    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
