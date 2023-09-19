package com.manning.javapersistence.springdatajpa;

import com.manning.javapersistence.springdatajpa.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FindUsersSortingAndPagingTest extends SpringdatajpaScratchApplicationTests {
  @Test
  void testOrder() {
    User user1 = userRepo.findFirstByOrderByUsernameAsc();
    User user2 = userRepo.findTopByOrderByRegistrationDateDesc();
    Page<User> userPage = userRepo.findAll(PageRequest.of(1, 3));
    List<User> users = userRepo.findFirst2ByLevel(2, Sort.by("registrationDate"));

    assertAll(() -> assertEquals("David", user1.getUsername()),
        () -> assertEquals("Michael", user2.getUsername()), () -> assertEquals(2, users.size()),
        () -> assertEquals(3, userPage.getSize()),
        () -> assertEquals("Jessica", users.get(0).getUsername()),
        () -> assertEquals("Emily", users.get(1).getUsername()));
  }

  @Test
  void testFindByLevel() {
    Sort.TypedSort<User> user = Sort.sort(User.class);

    List<User> users = userRepo.findByLevel(3, user.by(User::getRegistrationDate).descending());

    assertAll(() -> assertEquals(2, users.size()),
        () -> assertEquals("Michael", users.get(0).getUsername()));
  }

  @Test
  void testFindByActive() {
    List<User> users =
        userRepo.findByActive(true, PageRequest.of(1, 4, Sort.by("registrationDate")));
    assertAll(
        () -> assertEquals(4, users.size()),
        () -> assertEquals("Linda", users.get(0).getUsername())
    );
  }
}
