package com.manning.javapersistence.springdatajpa;

import com.manning.javapersistence.springdatajpa.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ModifyQueryTest extends SpringdatajpaScratchApplicationTests {
  @Test
  void testModifyLevel() {
    int updated = userRepo.updateLevel(0, 4);
    List<User> users = userRepo.findByLevel(4, Sort.by("username"));

    assertAll(
        () -> assertEquals(2, updated),
        () -> assertEquals(2, users.size()),
        () -> assertEquals("Linda", users.get(1).getUsername())
    );
  }
}
