package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.UserService;

@Controller
public class AuthorizationController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/login")
  public String loginPage() {
    return "login/index";
  }
}
