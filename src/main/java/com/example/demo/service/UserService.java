package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.error.ResourceAlreadyExistsException;
import com.example.demo.error.ResourceNotFoundException;

import java.util.List;


public interface UserService {
    User findByUsername(String username) throws ResourceNotFoundException;
    List<User> getUsers();
    User saveUser(User user) throws ResourceAlreadyExistsException;
    Role saveRole(Role role) throws ResourceAlreadyExistsException;
    void addRoleToUser(String email, String roleName) throws ResourceNotFoundException;
}
