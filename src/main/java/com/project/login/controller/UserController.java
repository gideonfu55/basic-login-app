package com.project.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.login.entity.User;
import com.project.login.service.UserService;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  /* Methods for handling Login Authentication */
  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody User user) {
    if (userService.findByUsername(user.getUsername()) != null) {
      return new ResponseEntity<>("The username already exists", HttpStatus.BAD_REQUEST);
    }

    userService.addUser(user);
    return new ResponseEntity<>("User is registered successfully", HttpStatus.CREATED); 
  }

  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody User user) {
    // Check if the user exists and the password is correct
    if (!userService.authenticate(user.getUsername(), user.getPassword())) {
      // Return an unauthorized response
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } else {
      // Return a successful response with the User object.
      return ResponseEntity.ok(userService.findByUsername(user.getUsername()));
    }
  }

  /* Methods for handling page access after a successful authentication */
  @GetMapping("/login-success")
  public ModelAndView getPage() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("login-success");
    return mav;
  }

  @GetMapping("/user-access")
  public ModelAndView userPage() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("user-access");
    return mav;
  }

  @GetMapping("/manager-access")
  public ModelAndView managerPage() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("manager-access");
    return mav;
  }

  /* Methods for handling logout */
}
