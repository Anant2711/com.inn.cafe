package com.inn.cafe.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path="/user")//end point whenever our user want to hit our api show will hit this user
//why we need in class level in one class level multiple api we use
public interface userRest {
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signup(@RequestBody(required = true)Map<String,String>requestMap);
    @PostMapping(path = "/lognin")
    public ResponseEntity<String> lognin(@RequestBody(required = true)Map<String,String>requestMap);
}
