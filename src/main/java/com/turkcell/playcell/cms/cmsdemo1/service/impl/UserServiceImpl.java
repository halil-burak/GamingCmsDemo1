package com.hburak.cms.cmsdemo1.service.impl;

import com.hburak.cms.cmsdemo1.entity.User;
import com.hburak.cms.cmsdemo1.repo.UserRepository;
import com.hburak.cms.cmsdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> retrieveUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User retrieveUser(Long id) {
        User user = userRepository.getOne(id);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
