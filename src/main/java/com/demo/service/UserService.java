package com.demo.service;

import com.demo.dao.UserDao;
import com.demo.domain.DbUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description TOOD
 * @Date 2018/8/8 10:26
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<DbUser> queryUserList(DbUser user){
        List<DbUser> list = userDao.queryUserList(user);
        return  list;
    }
}
