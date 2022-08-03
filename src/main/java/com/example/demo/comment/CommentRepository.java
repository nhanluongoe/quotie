package com.example.demo.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.quote.Quote;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  Page<Comment> findByQuote(Quote quote, Pageable pageable);
}
