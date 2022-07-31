package com.example.demo.user;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.quote.Quote;
import com.example.demo.quote.QuoteRepository;

@Service
public class UserDetailsService {

  @Autowired
  private UserDetailsRepository userDetailsRepository;

  @Autowired
  private QuoteRepository quoteRepository;

  public UserDetails getUserById(Long id) {
    return userDetailsRepository.findById(id).orElse(null);
  }

  public UserDetails getUserDetailsByUserId(Long id) {
    return userDetailsRepository.findByUserId(id);
  }

  @Transactional
  public void likeQuote(Long userDetailsId, Long quoteId) {
    UserDetails userDetails = userDetailsRepository.findById(userDetailsId).orElse(null);
    List<Quote> existingQuotes = quoteRepository.findByUserDetails(userDetails);
    List<Long> existingQuoteIds = existingQuotes.stream().map(q -> q.getId()).collect(Collectors.toList());
    if (existingQuoteIds.contains(quoteId)) {
      return;
    }

    userDetailsRepository.likeQuote(userDetailsId, quoteId);
  }
}
