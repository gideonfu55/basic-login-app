package com.project.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.login.entity.User;
import com.project.login.repository.UserRepository;

@Service
public class UserService {
  
  @Autowired
  private UserRepository userRepository;

  public void addUser(User user) {
    userRepository.save(user);
  }

  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public boolean authenticate(String username, String password) {
    User user = findByUsername(username);
    if (user != null && user.getPassword().equals(password)) {
      return true;
    }
    return false;
  }

}
