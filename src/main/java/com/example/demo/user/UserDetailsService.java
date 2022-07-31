package com.example.demo.user;

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
}
