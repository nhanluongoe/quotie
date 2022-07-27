package com.example.demo.home;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.quote.Quote;
import com.example.demo.quote.QuoteService;

@Controller
public class HomeController {

  private QuoteService quoteService;

  @Autowired
  public HomeController(QuoteService quoteService) {
    this.quoteService = quoteService;
  }

  @GetMapping(value = "/")
  public String home(Model model) {
    model.addAttribute("quotes", quoteService.getAllQuotes());
    model.addAttribute("popular_quotes", quoteService.getPopularQuotes());
    model.addAttribute("random_quote", quoteService.getRandomQuote());
    return "home/home";
  }

  @GetMapping(value = "/search")
  public String search(@RequestParam(defaultValue = "") String query, @RequestParam(defaultValue = "0") Integer page,
      Model model) {
    Pageable pagination = PageRequest.of(page, 10);
    String postgresFormattedQuery = query.replaceAll(" ", " | ");
    Page<Quote> quotesPage = quoteService.getQuotesByQuery(postgresFormattedQuery, pagination);
    model.addAttribute("quotesPage", quotesPage);

    int totalPages = quotesPage.getTotalPages();
    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
      model.addAttribute("pageNumbers", pageNumbers);
    }

    return "search/search";
  }

  @PostMapping(value = "/search")
  public String searchSubmit(ServletRequest request) {
    String query = request.getParameter("query");
    String encodedQuery = query.replaceAll(" ", "+");
    return "redirect:/search?query=" + encodedQuery;
  }
}
