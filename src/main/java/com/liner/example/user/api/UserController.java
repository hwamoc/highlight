package com.liner.example.user.api;

import com.liner.example.user.domain.User;
import com.liner.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public User add() {
        User user = userService.add();
        return user;
    }

//    @PostMapping("/login")
//    public User login() {
//        User user = userService.login((long) 1);
//        return user;
//    }
}
