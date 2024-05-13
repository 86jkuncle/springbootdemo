package com.lybb.ms.service;

import com.lybb.ms.entity.User;
import com.lybb.ms.vo.LoginVO;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/20 17:01 $
*/
public interface IUserService {
    User getById(Integer id);

    boolean tx();

    boolean login(LoginVO loginVO);
}
