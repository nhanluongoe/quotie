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
    User currentUser = userService.getUserByUsername(principal.getName());
    model.addAttribute("currentUser", currentUser);

    UserDetails userDetails = userDetailsService.getUserDetailsByUserId(currentUser.getId());
    if (userDetails != null) {
      model.addAttribute("userDetails", userDetails);
    }

    return "profile/index";
  }
}
