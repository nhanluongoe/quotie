package com.example.demo.author;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
  @Query(value = "SELECT * FROM author a WHERE LOWER(a.name) LIKE ?1%", nativeQuery = true)
  public List<Author> findAuthorsByFirstLetterName(Character letter);

  @Query(value = "SELECT * FROM author a WHERE LOWER(a.name) LIKE ?1% LIMIT ?2", nativeQuery = true)
  public List<Author> findFirstAuthorsByFirstLetterName(Character letter, Integer limit);

  @Query(value = "UPDATE author SET name = ?1, day_of_birth = ?2, day_of_death = ?3, biography = ?4 WHERE id = ?5", nativeQuery = true)
  public Author updateAuthorById(String name, String dob, String dod, String bio, Long id);
}
