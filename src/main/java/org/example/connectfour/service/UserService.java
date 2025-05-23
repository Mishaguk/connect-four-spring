package org.example.connectfour.service;


import org.example.connectfour.entity.User;

public interface UserService {
    void register(String userName, String password);
    User login(String userName, String password);
    boolean isExist(String userName);
}
