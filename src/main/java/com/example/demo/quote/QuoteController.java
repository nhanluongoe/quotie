package com.example.demo.quote;

import java.security.Principal;
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

import com.example.demo.user.User;
import com.example.demo.user.UserDetails;
import com.example.demo.user.UserDetailsService;
import com.example.demo.user.UserService;

@Controller
@RequestMapping(path = "/quote")
public class QuoteController {

  private QuoteService quoteService;
  private UserDetailsService userDetailsService;
  private UserService userService;

  @Autowired
  public QuoteController(QuoteService quoteService, UserDetailsService userDetailsService, UserService userService) {
    this.quoteService = quoteService;
    this.userDetailsService = userDetailsService;
    this.userService = userService;
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
      @RequestParam("query") String query, Principal principal) {
    String currentUrl = request.getParameter("url");
    quoteService.upVoteQuote(id);

    User user = userService.getUserByUsername(principal.getName());
    UserDetails userDetails = userDetailsService.getUserDetailsByUserId(user.getId());
    userDetailsService.likeQuote(userDetails.getId(), id);

    Long authorId = quoteService.getQuoteById(id).getAuthor().getId();
    userDetailsService.likeAuthor(userDetails.getId(), authorId);

    return "redirect:" + currentUrl + "?page=" + page + "&" + "query=" + query;
  }
}
