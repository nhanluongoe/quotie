package com.example.demo.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

  @OneToOne(mappedBy = "userDetails", cascade = CascadeType.ALL)
  private User user;
}
