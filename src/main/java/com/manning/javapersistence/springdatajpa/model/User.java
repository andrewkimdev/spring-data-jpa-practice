package com.manning.javapersistence.springdatajpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "USERS")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  private String username;
  private LocalDate registrationDate;

  private String email;

  private int level;

  private boolean active;

  public User() {
  }

  public User(String username) {
    this.username = username;
  }

  public User(String username, LocalDate registrationDate) {
    this.username = username;
    this.registrationDate = registrationDate;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isActive() {
    return this.active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(LocalDate registrationDate) {
    this.registrationDate = registrationDate;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + "'" +
        ", registrationDate=" + registrationDate +
        "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    User user = (User) o;

    if (level != user.level)
      return false;
    if (active != user.active)
      return false;
    if (!id.equals(user.id))
      return false;
    if (!username.equals(user.username))
      return false;
    if (!registrationDate.equals(user.registrationDate))
      return false;
    return email.equals(user.email);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + username.hashCode();
    result = 31 * result + registrationDate.hashCode();
    result = 31 * result + email.hashCode();
    result = 31 * result + level;
    result = 31 * result + (active ? 1 : 0);
    return result;
  }
}
