package com.lybb.ms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lybb.ms.entity.User;
import com.lybb.ms.mapper.UserMapper;
import com.lybb.ms.result.CommonResult;
import com.lybb.ms.result.IResult;
import com.lybb.ms.enums.ResultCodeEnum;
import com.lybb.ms.service.IUserService;
import com.lybb.ms.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/20 15:15 $
*/
@Controller
public class DemoController {

    private final IUserService userService;

    private final RedisUtil redisUtil;

    @Autowired
    public DemoController(IUserService userService,RedisUtil redisUtil) {
        this.userService = userService;
        this.redisUtil = redisUtil;
    }

    @RequestMapping("/hello")
    @ResponseBody
    CommonResult<IResult> home(){
        return CommonResult.failed(ResultCodeEnum.FAILED);
    }

    @RequestMapping("/thymeleaf")
    String thymeleaf(Model model){
        model.addAttribute("name", "thymeleaf");
        return "hello";
    }

    @RequestMapping("/db")
    @ResponseBody
    CommonResult<User> db() {
        return CommonResult.success(
            userService.getById(1),
            "查询成功"
        );
    }

    @RequestMapping("/tx")
    @ResponseBody
    CommonResult<Boolean> tx(){
        userService.tx();
        return CommonResult.success(true);
    }

    @RequestMapping("/redis/inc")
    @ResponseBody
    CommonResult<Long> redisInc(){
        return CommonResult.success(redisUtil.increment("gg"));
    }

    @RequestMapping("/redis/dec")
    @ResponseBody
    CommonResult<Long> redisDec(){
        return CommonResult.success(redisUtil.decrement("gg"));
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    CommonResult<Boolean> redisSet(){
        User user = userService.getById(1);
        redisUtil.set("user1", user);
        return CommonResult.success(Boolean.TRUE);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    CommonResult<Object> redisGet(){
        return CommonResult.success(redisUtil.get("user1"));
    }

}
