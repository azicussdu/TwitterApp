package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;

import java.util.List;


public interface UserService {
    User findByUsername(String username);
    List<User> getUsers();
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
}
