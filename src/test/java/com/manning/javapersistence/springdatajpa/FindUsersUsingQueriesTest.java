package com.manning.javapersistence.springdatajpa;

import com.manning.javapersistence.springdatajpa.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FindUsersUsingQueriesTest extends SpringdatajpaScratchApplicationTests {
  @Test
  void testFindAll() {
    List<User> users = userRepo.findAll();
    assertEquals(10, users.size());
  }

  @Test
  void testFindUser() {
    User emily = userRepo.findByUsername("emily");
    assertEquals("emily", emily.getUsername().toLowerCase());
  }

  @Test
  void testFindAllByOrderByUsernameAsc() {
    List<User> users = userRepo.findAllByOrderByUsernameAsc();
    assertAll(() -> assertEquals(10, users.size()),
        () -> assertEquals("david", users.get(0).getUsername().toLowerCase()),
        () -> assertEquals("elizabeth", users.get(1).getUsername().toLowerCase()));
  }

  @Test
  void testFindByRegistrationDateBetween() {
    List<User> users = userRepo.findByRegistrationDateBetween(
        LocalDate.of(2015, Month.JANUARY, 1),
        LocalDate.of(2020, Month.DECEMBER, 31)
    );

    assertEquals(4, users.size());
  }

  @Test
  void testFindByUsernameAndEmail() {
    List<User> user0 = userRepo.findByUsernameAndEmail("William", "william@somedomain.com");
    List<User> user1 = userRepo.findByUsernameAndEmail("Sarah", "sarah@somedomain.com");
    assertAll(
        () -> assertEquals("William", user0.get(0).getUsername()),
        () -> assertEquals("Sarah", user1.get(0).getUsername())
    );
  }

  @Test
  void testFindByUsernameOrEmail() {
    List<User> user0 = userRepo.findByUsernameOrEmail("William", "");
    List<User> user1 = userRepo.findByUsernameOrEmail("", "sarah@somedomain.com");
    assertAll(
        () -> assertEquals("william@somedomain.com", user0.get(0).getEmail()),
        () -> assertEquals("Sarah", user1.get(0).getUsername())
    );
  }
}
