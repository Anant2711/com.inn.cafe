package com.inn.cafe.JWT;

import com.inn.cafe.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
@Slf4j//log info to track the user action
@Service
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    private com.inn.cafe.pojo.user userDetail;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername{}" ,username);//here we are tracking username info
       userDetail =userDao.findByEmailId(username);
       if(!Objects.isNull(userDetail))
           return new User(userDetail.getEmail(),userDetail.getPassword(),new ArrayList<>());
       else
           throw new UsernameNotFoundException("user not found.");
    }
    public com.inn.cafe.pojo.user getUserDetail(){
        return userDetail;
    }
}
