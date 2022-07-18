package com.example.demo.quote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

  private QuoteRepository quoteRepository;

  @Autowired
  public QuoteService(QuoteRepository quoteRepository) {
    this.quoteRepository = quoteRepository;
  }

  public List<Quote> getAllQuotes() {
    return quoteRepository.findAll();
  }

  public Quote getQuoteById(Long id) {
    return quoteRepository.findById(id).orElse(null);
  }
}
