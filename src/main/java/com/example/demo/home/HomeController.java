package com.example.demo.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.quote.QuoteService;

@Controller
public class HomeController {

  private QuoteService quoteService;

  @Autowired
  public HomeController(QuoteService quoteService) {
    this.quoteService = quoteService;
  }

  @GetMapping(value = "/")
  public String home(Model model) {
    model.addAttribute("quotes", quoteService.getAllQuotes());
    model.addAttribute("popular_quotes", quoteService.getPopularQuotes());
    return "home/home";
  }
}
