package com.example.demo.author;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.util.Util;

@Controller
@RequestMapping(path = "/author")
public class AuthorController {

  private AuthorService authorService;

  @Autowired
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping
  public String authorList(Model model) {
    List<Author> authors = authorService.getAllAuthors();
    model.addAttribute("authors", authors);

    List<Character> letters = Util.convertStringToCharList("abcdefghijklmnopqrstuvwxyz");
    model.addAttribute("letters", letters);

    List<List<Author>> authorsByFirstLetterNameList = new ArrayList<>();
    for (Character letter : letters) {
      List<Author> authorsByFirstLetterName = authorService.getFirstAuthorsByFirstNameLetter(letter, 10);
      authorsByFirstLetterNameList.add(authorsByFirstLetterName);
    }
    model.addAttribute("authorsByFirstLetterName", authorsByFirstLetterNameList);

    return "author/author";
  }

  @GetMapping(value = "{authorId}")
  public String authorDetail(@PathVariable("authorId") Long authorId, Model model) {
    model.addAttribute("author", authorService.getAuthorById(authorId));
    return "author/author-detail";
  }
}
