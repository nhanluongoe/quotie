package com.example.demo.quote;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @PostMapping(value = "/upvote/{id}")
  public String upVote(@PathVariable("id") Long id, HttpServletRequest request,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam("query") String query) {
    String currentUrl = request.getParameter("url");
    quoteService.upVoteQuote(id);
    return "redirect:" + currentUrl + "?page=" + page + "&" + "query=" + query;
  }
}
