package com.fielamigo.app.FielAmigo.api;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fielamigo.app.FielAmigo.bl.UserBl;
import com.fielamigo.app.FielAmigo.dto.UserDto;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/users")
public class UsersController {
    private UserBl userBl;

    @Autowired
    public UsersController(UserBl userBl) {
        this.userBl = userBl;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto user) {
        System.out.println("Creating user...");
        return userBl.createUser(user);
    }
}
