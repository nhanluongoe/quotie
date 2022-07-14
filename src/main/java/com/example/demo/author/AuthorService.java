package com.example.demo.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
  private final AuthorRepository authorRepository;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  public Author getAuthorById(Long id) {
    return authorRepository.findById(id).orElse(null);
  }
}