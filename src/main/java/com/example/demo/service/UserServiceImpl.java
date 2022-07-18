package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.error.ResourceAlreadyExistsException;
import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) throws ResourceNotFoundException {
        log.info("@@@ UserServiceImpl: in findByUsername()");
        User user = userRepository.findByUsername(username).
                orElseThrow(() -> new ResourceNotFoundException("No user with username: " + username));
        return user;
    }

    @Override
    public List<User> getUsers() {
        log.info("@@@ UserServiceImpl: in getUsers()");
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) throws ResourceAlreadyExistsException {
        log.info("@@@ UserServiceImpl: in saveUser()");
        Optional<User> userBD = userRepository.findByUsername(user.getUsername());
        if (userBD.isPresent())
            throw new ResourceAlreadyExistsException("User with username: " + user.getUsername() + " already exists");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) throws ResourceAlreadyExistsException {
        log.info("@@@ UserServiceImpl: in saveRole()");
        Optional<Role> roleBD = roleRepository.findByName(role.getName());
        if (roleBD.isPresent())
            throw new ResourceAlreadyExistsException("Role with name: " + role.getName() + " already exists");
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) throws ResourceNotFoundException {
        log.info("@@@ UserServiceImpl: in addRoleToUser()");
        User user = userRepository.findByUsername(username).
                orElseThrow(() -> new ResourceNotFoundException("No user with username: " + username));
        Role role = roleRepository.findByName(roleName).
                orElseThrow(() -> new ResourceNotFoundException("No role with name: " + roleName));

        user.getRoles().add(role);
    }
}
