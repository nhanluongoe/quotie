package com.example.demo.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.author.Author;
import com.example.demo.author.AuthorService;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
  private AuthorService authorService;

  @Autowired
  public AdminController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping
  public String indexPage() {
    return "admin/admin";
  }

  @GetMapping("/author")
  public String authorPage(Model model) {
    model.addAttribute("authors", authorService.getAllAuthors());
    return "admin/author_admin";
  }

  @GetMapping("/author/add")
  public String addAuthorForm(Model model) {
    model.addAttribute("author", new Author());
    return "admin/author-add_admin";
  }

  @PostMapping("/author/add")
  public String addAuthorFormSubmit(@ModelAttribute Author author, Model model) {
    Author newAuthor = authorService.addAuthor(author);
    model.addAttribute("author", newAuthor);
    return "admin/author-add_admin";
  }
}
