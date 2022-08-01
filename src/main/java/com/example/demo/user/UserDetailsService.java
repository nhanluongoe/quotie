package com.example.demo.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

  @Autowired
  private UserDetailsRepository userDetailsRepository;

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

  @Transactional
  public void likeAuthor(Long userDetailsId, Long authorId) {
    userDetailsRepository.likeAuthor(userDetailsId, authorId);
  }
}
