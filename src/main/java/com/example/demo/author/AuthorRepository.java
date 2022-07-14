package com.example.demo.author;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
  List<Author> findAll();
  Optional<Author> findById(Long id);
}
