package com.fielamigo.app.FielAmigo.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.dao.UserDao;
import com.fielamigo.app.FielAmigo.dto.UserDto;

@Component
public class UserBl {
    private final UserDao userDao;

    @Autowired
    public UserBl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDto createUser(UserDto userDto) {
        return userDao.createUser(userDto);
    }

}
