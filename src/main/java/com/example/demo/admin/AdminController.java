package com.example.demo.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.author.Author;
import com.example.demo.author.AuthorService;
import com.example.demo.quote.Quote;
import com.example.demo.quote.QuoteService;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
  private AuthorService authorService;
  private QuoteService quoteService;

  @Autowired
  public AdminController(AuthorService authorService, QuoteService quoteService) {
    this.authorService = authorService;
    this.quoteService = quoteService;
  }

  @GetMapping
  public String indexPage() {
    return "admin/admin";
  }

  /*
   * Author module
   */
  @GetMapping("/author")
  public String authorPage(Model model) {
    model.addAttribute("authors", authorService.getAllAuthors());
    return "admin/author/author_admin";
  }

  @GetMapping("/author/add")
  public String addAuthorForm(Model model) {
    model.addAttribute("author", new Author());
    return "admin/author/author-add_admin";
  }

  @PostMapping("/author/save")
  /**
   * The BindingResult must come right after the model object
   * that is validated or else Spring will fail to validate
   * the object and throw an exception.
   */
  public String addAuthorFormSubmit(@Valid Author author, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "admin/author/author-add_admin";
    }

    try {
      Author newAuthor = authorService.saveAuthor(author);
      model.addAttribute("author", newAuthor);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return "redirect:/admin/author";
  }

  @GetMapping(value = "/author/edit/{id}")
  public String editAuthorForm(@PathVariable("id") Long id, Model model) {
    model.addAttribute("author", authorService.getAuthorById(id));
    return "admin/author/author-update_admin";
  }

  @GetMapping(value = "/author/delete/{id}")
  public String deleteAuthor(@PathVariable("id") Long id) {
    try {
      authorService.deleteAuthorById(id);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return "redirect:/admin/author";
  }

  /*
   * Quote module
   */
  @GetMapping(value = "/quote")
  public String quotePage(Model model) {
    model.addAttribute("quotes", quoteService.getAllQuotes());
    return "admin/quote/quote_admin";
  }

  @GetMapping(value = "/quote/save")
  public String saveQuoteForm(Model model) {
    Quote newQuote = new Quote();
    model.addAttribute("quote", newQuote);
    model.addAttribute("authors", authorService.getAllAuthors());
    return "admin/quote/quote-save_admin";
  }

  @PostMapping(value = "/quote/save")
  public String saveQuoteFormSubmit(@Valid Quote quote, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("authors", authorService.getAllAuthors());
      return "admin/quote/quote-save_admin";
    }

    try {
      quoteService.saveQuote(quote);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return "redirect:/admin/quote";
  }

  @GetMapping(value = "/quote/edit/{id}")
  public String editQuoteForm(Model model, @PathVariable("id") Long id) {
    model.addAttribute("quote", quoteService.getQuoteById(id));
    model.addAttribute("authors", authorService.getAllAuthors());
    return "admin/quote/quote-save_admin";
  }

  @GetMapping(value = "/quote/delete/{id}")
  public String deleteQuote(@PathVariable("id") Long id) {
    try {
      quoteService.deleteQuoteById(id);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return "redirect:/admin/quote";
  }
}
