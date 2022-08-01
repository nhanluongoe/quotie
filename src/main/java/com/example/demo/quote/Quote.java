package com.example.demo.quote;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.example.demo.author.Author;
import com.example.demo.tag.Tag;
import com.example.demo.user.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quote", indexes = @Index(name = "content_index", columnList = "content"))
public class Quote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "content")
  @NotBlank(message = "Quote cannot be empty!")
  private String content;

  @Column(name = "up_votes")
  private Long upVotes = 0l;

  @Column(name = "down_votes")
  private Long downVotes = 0l;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private Author author;

  @ManyToMany
  @JoinTable(name = "user_details_quote", joinColumns = @JoinColumn(name = "quote_id"), inverseJoinColumns = @JoinColumn(name = "user_details_id"))
  private Set<UserDetails> userDetails;

  @ManyToMany
  @JoinTable(name = "quote_tag", joinColumns = @JoinColumn(name = "quote_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private List<Tag> tags;
}
