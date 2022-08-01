package com.example.demo.author;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.example.demo.quote.Quote;
import com.example.demo.user.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "author")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  @NotEmpty(message = "Name cannot be empty")
  private String name;

  @Column(name = "day_of_birth")
  private String dob;

  @Column(name = "day_of_death")
  private String dod;

  @Column(name = "biography", columnDefinition = "TEXT")
  private String biography;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  private List<Quote> quotes;

  @ManyToMany(mappedBy = "likedAuthors")
  private List<UserDetails> userDetails;
}
