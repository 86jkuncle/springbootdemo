package com.lybb.ms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lybb.ms.entity.User;
import com.lybb.ms.vo.LoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/20 16:54 $
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where tel = #{tel}")
    User findByTel(LoginVO loginVO);
}
