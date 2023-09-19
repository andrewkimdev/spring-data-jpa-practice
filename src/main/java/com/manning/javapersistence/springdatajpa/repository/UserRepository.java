package com.manning.javapersistence.springdatajpa.repository;

import com.manning.javapersistence.springdatajpa.model.Projection;
import com.manning.javapersistence.springdatajpa.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);

  List<User> findAllByOrderByUsernameAsc();

  List<User> findByRegistrationDateBetween(LocalDate start, LocalDate end);

  List<User> findByUsernameAndEmail(String username, String email);

  List<User> findByUsernameOrEmail(String username, String email);

  List<User> findByUsernameIgnoreCase(String username);

  List<User> findByLevelOrderByUsernameDesc(int level);

  List<User> findByLevelGreaterThanEqual(int level);

  List<User> findByUsernameContaining(String text);

  List<User> findByUsernameLike(String text);

  List<User> findByUsernameStartingWith(String start);

  List<User> findByUsernameEndingWith(String end);

  List<User> findByActive(boolean active);

  List<User> findByRegistrationDateIn(Collection<LocalDate> dates);

  List<User> findByRegistrationDateNotIn(Collection<LocalDate> dates);

  // Add paging and sorting
  User findFirstByOrderByUsernameAsc();

  User findTopByOrderByRegistrationDateDesc();

  Page<User> findAll(Pageable pageable);

  List<User> findFirst2ByLevel(int level, Sort sort);

  List<User> findByLevel(int level, Sort sort);

  List<User> findByActive(boolean active, Pageable pageable);

  // Streaming Results
  Streamable<User> findByEmailContaining(String text);

  Streamable<User> findByLevel(int level);

  // Using @Query annotation
  @Query("select count(u) from User u where u.active = ?1")
  int findNumberOfUsersByActivity(boolean active);

  @Query("select u from User u where u.level = :level and u.active = :active")
  List<User> findByLevelAndActive(@Param("level") int level, @Param("active") boolean active);

  @Query("select u.username, LENGTH(u.email) as emai_length from #{#entityName} u where u.username like %?1%")
  List<Object[]> findByAsArrayAndSort(String text, Sort sort);

  // Using projection
  List<Projection.UserSummary> findByRegistrationDateAfter(LocalDate date);

  List<Projection.UsernameOnly> findByEmail(String username);

  <T> List<T> findByEmail(String username, Class<T> type);
}
