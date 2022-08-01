package com.example.demo.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.user.UserDetails;

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

  public List<Author> getAuthorByFirstNameLetter(Character letter) {
    return authorRepository.findAuthorsByFirstLetterName(letter);
  }

  public List<Author> getFirstAuthorsByFirstNameLetter(Character letter, Integer limit) {
    return authorRepository.findFirstAuthorsByFirstLetterName(letter, limit);
  }

  public Author saveAuthor(Author author) {
    return authorRepository.save(author);
  }

  public void deleteAuthorById(Long id) {
    authorRepository.deleteById(id);
  }

  public Page<Author> getAuthorsByUserDetails(UserDetails userDetails, Pageable pageable) {
    return authorRepository.findByUserDetails(userDetails, pageable);
  }
}
