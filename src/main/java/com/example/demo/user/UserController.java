package com.example.demo.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserDetailsService userDetailsService;

  @GetMapping(value = "/profile")
  public String profilePage(Principal principal, Model model) {
    User user = userService.getUserByUsername(principal.getName());
    model.addAttribute("user", user);

    UserDetails userDetails = userDetailsService.getUserDetailsByUserId(user.getId());
    if (userDetails != null) {
      model.addAttribute("userDetails", userDetails);
    }

    return "profile/index";
  }
}
