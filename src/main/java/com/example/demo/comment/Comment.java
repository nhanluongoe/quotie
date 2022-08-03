package com.example.demo.comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.example.demo.quote.Quote;
import com.example.demo.user.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "content", columnDefinition = "TEXT")
  @NotEmpty(message = "Content cannot be empty!")
  private String content;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserDetails userDetails;

  @ManyToOne
  @JoinColumn(name = "quote_id")
  private Quote quote;
}
