package com.example.demo.quote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.author.Author;

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

  public List<Quote> getQuotesByTagId(Long tag_id) {
    return quoteRepository.findAllByTag(tag_id);
  }

  public List<Quote> getPopularQuotes() {
    return quoteRepository.findTopUpvoteQuotes();
  }

  public Quote saveQuote(Quote newQuote) {
    return quoteRepository.save(newQuote);
  }

  public void deleteQuoteById(Long id) {
    quoteRepository.deleteById(id);
  }

  public void upVoteQuote(Long id) {
    quoteRepository.upVote(id);
  }

  public Quote getRandomQuote() {
    return quoteRepository.findRandom();
  }

  public Page<Quote> getQuotesByAuthor(Author author, Pageable pageable) {
    return quoteRepository.findByAuthorOrderByIdAsc(author, pageable);
  }
}
