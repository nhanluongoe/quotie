package com.example.demo.user;

import java.util.HashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.demo.role.Role;

@Entity
@Table(name = "userq") // "user" is a reserved word in PostgresQL, thus userq, q stands for Quotie
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "name")
  @NotEmpty(message = "Name cannot be empty!")
  private String name;

  @NotEmpty(message = "Name cannot be empty!")
  @Column(name = "username")
  private String username;

  @Size(min = 6, message = "Password length must be at least 6 characters!")
  @Column(name = "password")
  private String password;

  @Column(name = "active")
  private Boolean active = true;

  @ManyToMany(cascade = CascadeType.MERGE)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_detail_id")
  private UserDetails userDetails;

  public Set<Role> getRoles() {
    return roles;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public String getName() {
    return name;
  }
}
