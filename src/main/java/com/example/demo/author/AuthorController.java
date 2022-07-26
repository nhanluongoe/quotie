package com.example.demo.author;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.quote.Quote;
import com.example.demo.quote.QuoteService;
import com.example.demo.util.Util;

@Controller
@RequestMapping(path = "/author")
public class AuthorController {

  private AuthorService authorService;
  private QuoteService quoteService;

  @Autowired
  public AuthorController(AuthorService authorService, QuoteService quoteService) {
    this.authorService = authorService;
    this.quoteService = quoteService;
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
  public String authorDetail(@PathVariable("authorId") Long authorId, @RequestParam(defaultValue = "0") Integer page,
      Model model) {
    Author author = authorService.getAuthorById(authorId);
    model.addAttribute("author", author);

    Pageable pagination = PageRequest.of(page, 9);
    Page<Quote> quotesPage = quoteService.getQuotesByAuthor(author, pagination);
    model.addAttribute("quotesPage", quotesPage);

    int totalPages = quotesPage.getTotalPages();
    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
      model.addAttribute("pageNumbers", pageNumbers);
    }

    return "author/author-detail";
  }
}
