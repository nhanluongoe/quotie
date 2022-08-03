package com.example.demo.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.quote.Quote;

@Service
public class CommentService {

  @Autowired
  private CommentRepository commentRepository;

  public Page<Comment> getCommentsByQuote(Quote quote, Pageable pageable) {
    return commentRepository.findByQuoteOrderByCreatedAtDesc(quote, pageable);
  }

  public Comment save(Comment comment) {
    return commentRepository.save(comment);
  }
}
