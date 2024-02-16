package com.inn.cafe.dao;

import com.inn.cafe.pojo.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<user,Integer> {
    user findByEmailId(@Param("email")String email);
}
