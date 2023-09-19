package com.manning.javapersistence.springdatajpa;

import com.manning.javapersistence.springdatajpa.model.User;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

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
}
