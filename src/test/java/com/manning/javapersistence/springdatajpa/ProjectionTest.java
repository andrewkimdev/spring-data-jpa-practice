package com.manning.javapersistence.springdatajpa;

import com.manning.javapersistence.springdatajpa.model.Projection;
import com.manning.javapersistence.springdatajpa.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectionTest extends SpringdatajpaScratchApplicationTests {
  @Test
  void testProjectionUsername() {
    List<Projection.UsernameOnly> users = userRepo.findByEmail("john@somedomain.com", Projection.UsernameOnly.class);

    assertAll(
        () -> assertEquals(1, users.size()),
        () -> assertEquals("John", users.get(0).getUsername())
    );
  }

  @Test
  void testDynamicProjection() {
    List<Projection.UsernameOnly> usernames = userRepo.findByEmail("emily@somedomain.com", Projection.UsernameOnly.class);

    List<User> users = userRepo.findByEmail("emily@somedomain.com", User.class);

    assertAll(
        () -> assertEquals(1, usernames.size()),
        () -> assertEquals("Emily", usernames.get(0).getUsername()),
        () -> assertEquals(1, users.size()),
        () -> assertEquals("Emily", users.get(0).getUsername())
    );
  }
}
