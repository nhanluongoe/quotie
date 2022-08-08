package com.example.demo.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.User;
import com.example.demo.user.UserDetails;
import com.example.demo.user.UserService;

@Controller
public class AuthorizationController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/login")
  public String loginPage() {
    return "security/login";
  }

  @GetMapping(value = "/register")
  public String registerPage(Model model) {
    model.addAttribute("user", new User());
    return "security/register";
  }

  @PostMapping(value = "/register")
  public String register(@Valid User user, BindingResult result) {
    String username = user.getUsername();
    User existedUser = userService.getUserByUsername(username);
    if (existedUser != null) {
      result.rejectValue("username", "error.user",
          "There is already a user registered with the username provided");
    }

    if (result.hasErrors()) {
      return "security/register";
    }

    user.setUserDetails(new UserDetails(user.getName()));
    userService.saveUser(user);

    return "redirect:/login?register=true";
  }

  @GetMapping(value = "/access-denied")
  public String accessDeniedPage() {
    return "security/access-denied";
  }
}
