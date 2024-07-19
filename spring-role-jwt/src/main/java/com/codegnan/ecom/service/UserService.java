package com.codegnan.ecom.service;

import java.util.List;

import com.codegnan.ecom.model.User;
import com.codegnan.ecom.model.UserDto;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}
