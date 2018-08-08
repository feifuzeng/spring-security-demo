package com.demo.service;

import com.demo.domain.DbUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description TOOD
 * @Date 2018/8/8 10:27
 */
public class CustomDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomDetailsService.class);
    @Autowired
    private UserService userService;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetail =null;
        try{
            DbUser user = new DbUser();
            DbUser resuser = new DbUser();
            user.setUsername(username);
            List<DbUser> list = userService.queryUserList(user);
            resuser = list.get(0);
            userDetail = new User(resuser.getUsername(), resuser.getPassword()
                    .toLowerCase(), true, true, true, true,
                    getAuthorities(resuser.getAccess()));
        }catch (Exception e){
            logger.error("Exception:"+e);
        }
        return userDetail;
    }

    /**
     * 获得访问角色权限
     *
     * @param access
     * @return
     */
    public Collection<GrantedAuthority> getAuthorities(Integer access) {

        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

        // 所有的用户默认拥有ROLE_USER权限
        logger.debug("Grant ROLE_USER to this user");
        authList.add(new GrantedAuthorityImpl("ROLE_USER"));

        // 如果参数access为1.则拥有ROLE_ADMIN权限
        if (access.compareTo(1) == 0) {
            logger.debug("Grant ROLE_ADMIN to this user");
            authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
        }

        return authList;
    }
}
