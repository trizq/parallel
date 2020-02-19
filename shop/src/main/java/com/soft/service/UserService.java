package com.soft.service;

import com.soft.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> selectUser();
}
