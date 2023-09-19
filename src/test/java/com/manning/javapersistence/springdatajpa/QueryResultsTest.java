package com.manning.javapersistence.springdatajpa;

import com.manning.javapersistence.springdatajpa.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryResultsTest extends SpringdatajpaScratchApplicationTests {
  @Test
  void testStreamable() {
    try(Stream<User> result = userRepo.findByEmailContaining("li")
        .and(userRepo.findByLevel(0))
        .stream().distinct()
    ) {
      assertEquals(4, result.count());
    }
  }

  @Test
  void testFindByAsArrayAndSort() {
    List<Object[]> usersList1 =
        userRepo.findByAsArrayAndSort("a", Sort.by("username"));
    List<Object[]> usersList2 =
        userRepo.findByAsArrayAndSort("a", Sort.by("email_length").descending());
    List<Object[]> usersList3 =
        userRepo.findByAsArrayAndSort("a", JpaSort.unsafe("LENGTH(u.email)"));

    assertAll(
        () -> assertEquals(7, usersList1.size())
    );
  }
}
