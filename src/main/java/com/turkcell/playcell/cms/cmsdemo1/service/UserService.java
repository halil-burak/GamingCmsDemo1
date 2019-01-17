package com.turkcell.playcell.cms.cmsdemo1.service;

import com.turkcell.playcell.cms.cmsdemo1.entity.User;

import java.util.List;

public interface UserService {

    public List<User> retrieveUsers();

    public void saveUser(User user);

    public User retrieveUser(Long id);

    public void deleteUser(Long id);

    public boolean existsById(Long id);
}
