package com.lybb.ms.controller;

import com.lybb.ms.result.CommonResult;
import com.lybb.ms.service.IUserService;
import com.lybb.ms.vo.LoginVO;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/25 16:08 $
*/
@RestController
public class LoginController {

    private final IUserService userService;

    @Autowired
    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public CommonResult<Boolean> login(@Validated LoginVO loginVO){
        userService.login(loginVO);
        return CommonResult.success(Boolean.TRUE);
    }
}
