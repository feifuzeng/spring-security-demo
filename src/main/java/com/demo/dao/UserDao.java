package com.demo.dao;

import com.demo.domain.DbUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    /**
     * 查用户列表
     * @param user
     * @return
     */
    public List<DbUser> queryUserList(@Param("user")DbUser user);
}
