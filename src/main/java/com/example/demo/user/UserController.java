package com.example.demo.user;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.author.Author;
import com.example.demo.author.AuthorService;
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

  @Autowired
  private AuthorService authorService;

  @GetMapping
  public String profilePage(Principal principal, Model model) {
    UserDetails userDetails = getCurrentUserDetails(principal, model);

    if (userDetails != null) {
      model.addAttribute("userDetails", userDetails);

      Pageable quotePagination = PageRequest.of(0, 5);
      Page<Quote> likedQuotesPage = quoteService.getQuotesByUserDetails(userDetails, quotePagination);
      model.addAttribute("likedQuotes", likedQuotesPage);
      int numberOfQuotePages = likedQuotesPage.getTotalPages();
      model.addAttribute("hasMoreQuotes", numberOfQuotePages > 1);

      Pageable authorPagination = PageRequest.of(0, 5);
      Page<Author> likedAuthorsPage = authorService.getAuthorsByUserDetails(userDetails, authorPagination);
      model.addAttribute("likedAuthors", likedAuthorsPage);
      int numberOfAuthorPages = likedAuthorsPage.getTotalPages();
      model.addAttribute("hasMoreAuthors", numberOfAuthorPages > 1);
    }

    return "profile/index";
  }

  @GetMapping(value = "/quote")
  public String profileQuote(@RequestParam(defaultValue = "0") Integer page, Principal principal, Model model) {
    UserDetails userDetails = getCurrentUserDetails(principal, model);

    Pageable pagination = PageRequest.of(page, 12);
    Page<Quote> quotesPage = quoteService.getQuotesByUserDetails(userDetails, pagination);
    model.addAttribute("quotesPage", quotesPage);

    int totalPages = quotesPage.getTotalPages();
    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
      model.addAttribute("pageNumbers", pageNumbers);
    }

    return "profile/quote";
  }

  @GetMapping(value = "/author")
  public String profileAuthor(@RequestParam(defaultValue = "0") Integer page, Principal principal, Model model) {
    UserDetails userDetails = getCurrentUserDetails(principal, model);

    Pageable pagination = PageRequest.of(page, 12);
    Page<Author> authorsPage = authorService.getAuthorsByUserDetails(userDetails, pagination);
    model.addAttribute("authorsPage", authorsPage);

    int totalPages = authorsPage.getTotalPages();
    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
      model.addAttribute("pageNumbers", pageNumbers);
    }

    return "profile/author";
  }

  public UserDetails getCurrentUserDetails(Principal principal, Model model) {
    User user = userService.getUserByUsername(principal.getName());
    model.addAttribute("user", user);
    UserDetails userDetails = userDetailsService.getUserDetailsByUserId(user.getId());
    return userDetails;
  }
}
