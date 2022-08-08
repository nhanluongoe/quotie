package com.example.demo.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/about")
public class AboutController {

  @GetMapping
  public String aboutPage() {
    return "about/index";
  }
}
