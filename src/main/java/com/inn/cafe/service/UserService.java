package com.inn.cafe.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    ResponseEntity<String>signup(Map<String,String> requestMap);
    ResponseEntity<String>lognin(Map<String,String> requestMap);
}
