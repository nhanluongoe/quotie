package com.example.demo.quote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/quote")
public class QuoteController {

  private QuoteService quoteService;

  @Autowired
  public QuoteController(QuoteService quoteService) {
    this.quoteService = quoteService;
  }

  @GetMapping
  public String quoteList(Model model) {
    List<Quote> quotes = quoteService.getAllQuotes();
    model.addAttribute("quotes", quotes);
    return "quote/quote-list";
  }
}
