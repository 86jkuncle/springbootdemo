package com.lybb.ms.vo;

import com.lybb.ms.validator.ValidateMobile;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/25 16:10 $
*/
public class LoginVO {
    @NotNull
    @ValidateMobile
    private String tel;
    @NotNull
    private String password;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
            "tel='" + tel + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
