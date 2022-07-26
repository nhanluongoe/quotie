package com.example.demo.quote;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.author.Author;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

  @Query(value = "SELECT * FROM quote q INNER JOIN quote_tag qt on q.id = qt.quote_id WHERE qt.tag_id = ?1", nativeQuery = true)
  public List<Quote> findAllByTag(Long tag_id);

  @Query(value = "SELECT * FROM quote q ORDER BY q.up_votes DESC LIMIT 8", nativeQuery = true)
  public List<Quote> findTopUpvoteQuotes();

  @Transactional
  @Modifying
  @Query(value = "UPDATE quote SET up_votes = up_votes + 1 WHERE id = ?1", nativeQuery = true)
  public void upVote(Long id);

  @Query(value = "SELECT * FROM quote ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
  public Quote findRandom();

  public Page<Quote> findByAuthor(Author author, Pageable pageable);
}
