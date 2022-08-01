package com.example.demo.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.author.AuthorRepository;
import com.example.demo.quote.QuoteRepository;

@Service
public class UserDetailsService {

  @Autowired
  private UserDetailsRepository userDetailsRepository;

  @Autowired
  private QuoteRepository quoteRepository;

  @Autowired
  private AuthorRepository authorRepository;

  public UserDetails getUserById(Long id) {
    return userDetailsRepository.findById(id).orElse(null);
  }

  public UserDetails getUserDetailsByUserId(Long id) {
    return userDetailsRepository.findByUserId(id);
  }

  @Transactional
  public void likeQuote(Long userDetailsId, Long quoteId) {
    userDetailsRepository.likeQuote(userDetailsId, quoteId);
  }

  // @Transactional
  // public void likeAuthor(Long userDetailsId, Long authorId) {
  // UserDetails userDetails =
  // userDetailsRepository.findById(userDetailsId).orElse(null);
  // List<Author> existingAuthors = authorRepository

  // }
}
