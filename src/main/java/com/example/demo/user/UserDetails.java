package com.example.demo.user;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.demo.author.Author;
import com.example.demo.comment.Comment;
import com.example.demo.quote.Quote;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_details")
public class UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "favorite_quote")
  private String favoriteQuote;

  @Column(name = "about_me")
  private String aboutMe;

  @Column(name = "created_at")
  @CreationTimestamp
  private Date createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Date updatedAt;

  @OneToOne(mappedBy = "userDetails", cascade = CascadeType.ALL)
  private User user;

  @ManyToMany(mappedBy = "userDetails")
  private Set<Quote> likedQuotes;

  @ManyToMany
  @JoinTable(name = "user_details_author", joinColumns = @JoinColumn(name = "user_details_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
  private Set<Author> likedAuthors;

  @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
  private List<Comment> comments;
}
