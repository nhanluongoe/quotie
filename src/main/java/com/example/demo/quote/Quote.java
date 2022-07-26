package com.example.demo.quote;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.example.demo.author.Author;
import com.example.demo.tag.Tag;

@Entity
@Table(name = "quote")
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
  @JoinTable(name = "quote_tag", joinColumns = @JoinColumn(name = "quote_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private List<Tag> tags;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public Long getUpVotes() {
    return upVotes;
  }

  public void setUpVotes(Long upVotes) {
    this.upVotes = upVotes;
  }

  public Long getDownVotes() {
    return downVotes;
  }

  public void setDownVotes(Long downVotes) {
    this.downVotes = downVotes;
  }
}
