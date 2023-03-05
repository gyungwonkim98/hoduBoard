package com.example.hoduBoard.service;

import com.example.hoduBoard.model.UserDTO;
import org.apache.tomcat.jni.User;

public interface UserService{
    Long createUser(UserDTO userDTO);
}