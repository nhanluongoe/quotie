package com.example.demo.quote;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

  @Query(value = "SELECT * FROM quote q INNER JOIN quote_tag qt on q.id = qt.quote_id WHERE qt.tag_id = ?1", nativeQuery = true)
  public List<Quote> findAllByTag(Long tag_id);
}
