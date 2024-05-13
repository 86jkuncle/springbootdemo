package com.lybb.ms.service.impl;

import com.lybb.ms.entity.User;
import com.lybb.ms.exception.MSException;
import com.lybb.ms.mapper.UserMapper;
import com.lybb.ms.service.IUserService;
import com.lybb.ms.utils.MD5Util;
import com.lybb.ms.vo.LoginVO;
import java.time.LocalDateTime;
import javax.xml.ws.Action;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;


/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/20 17:02 $
*/
@Service
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;
    private final TransactionTemplate transactionTemplate;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,TransactionTemplate transactionTemplate) {
        this.userMapper = userMapper;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean tx() {
        LocalDateTime localDateTime = LocalDateTime.now();
        User user = new User();
        user.setNickname("ls");
        user.setRegisterDate(localDateTime);

        User user2 = new User();
//        user2.setId(1);
        user2.setNickname("zss");
        user2.setRegisterDate(localDateTime);

        Boolean result = transactionTemplate.execute(status -> {
            userMapper.insert(user);
            userMapper.insert(user2);
            return Boolean.TRUE;
        });

        return Boolean.TRUE.equals(result);
    }

    @Override
    public boolean login(LoginVO loginVO) {
        if(StringUtils.isBlank(loginVO.getTel())){
            throw new MSException("登录账号不能为空");
        }

        if(StringUtils.isBlank(loginVO.getPassword())){
            throw new MSException("密码不能为空");
        }

        User user = userMapper.findByTel(loginVO);
        if(user == null){
            throw new MSException("登录失败");
        }

        String password = MD5Util.formPassToDBPass(loginVO.getPassword(), user.getSalt());
        if(!user.getPassword().equals(password)){
            throw new MSException("用户名或密码错误");
        }
        return true;
    }
}
