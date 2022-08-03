package com.example.demo.quote;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.comment.Comment;
import com.example.demo.comment.CommentService;
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
  private CommentService commentService;

  @Autowired
  public QuoteController(QuoteService quoteService, UserDetailsService userDetailsService, UserService userService,
      CommentService commentService) {
    this.quoteService = quoteService;
    this.userDetailsService = userDetailsService;
    this.userService = userService;
    this.commentService = commentService;
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

  @GetMapping(value = "/{id}")
  public String quote(@PathVariable("id") Long id, @RequestParam(defaultValue = "0") Integer page, Model model) {
    Quote quote = quoteService.getQuoteById(id);
    model.addAttribute("quote", quote);

    Pageable commentPagination = PageRequest.of(page, 10);
    Page<Comment> comments = commentService.getCommentsByQuote(quote, commentPagination);
    model.addAttribute("comments", comments);

    return "quote/index";
  }

  @PostMapping(value = "/comment/{id}")
  public String comment(@PathVariable("id") Long id, HttpServletRequest request, Principal principal) {
    User user = userService.getUserByUsername(principal.getName());
    UserDetails userDetails = userDetailsService.getUserDetailsByUserId(user.getId());
    Quote quote = quoteService.getQuoteById(id);
    String content = request.getParameter("content");

    commentService.save(new Comment(content, userDetails, quote));

    return "redirect:/quote/" + id;
  }
}
