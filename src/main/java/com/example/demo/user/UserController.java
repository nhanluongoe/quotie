package com.example.demo.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.quote.Quote;
import com.example.demo.quote.QuoteService;

@Controller
@RequestMapping(value = "/profile")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private QuoteService quoteService;

  @GetMapping
  public String profilePage(Principal principal, Model model) {
    User user = userService.getUserByUsername(principal.getName());
    model.addAttribute("user", user);

    UserDetails userDetails = userDetailsService.getUserDetailsByUserId(user.getId());
    if (userDetails != null) {
      model.addAttribute("userDetails", userDetails);

      Pageable pagination = PageRequest.of(0, 10);
      Page<Quote> likedQuotesPage = quoteService.getQuotesByUserDetails(userDetails, pagination);
      model.addAttribute("likedQuotes", likedQuotesPage);

      int numberOfPages = likedQuotesPage.getTotalPages();
      model.addAttribute("hasMore", numberOfPages > 1);
    }

    return "profile/index";
  }
}
