package com.manning.javapersistence.springdatajpa;

import com.manning.javapersistence.springdatajpa.model.User;
import com.manning.javapersistence.springdatajpa.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class SpringdatajpaScratchApplicationTests {

  @Autowired
  UserRepository userRepo;

  @BeforeAll
  void beforeAll() {
    userRepo.saveAll(generateUsers());
  }

  private static List<User> generateUsers() {
    List<User> users = new ArrayList<>();

    users.add(createUser("John", 2020, Month.APRIL, 13, "john@somedomain.com", 1, true));
    users.add(createUser("Emily", 2018, Month.FEBRUARY, 5, "emily@somedomain.com", 2, true));
    users.add(createUser("James", 2010, Month.JULY, 23, "james@somedomain.com", 0, true));
    users.add(createUser("Sarah", 2015, Month.SEPTEMBER, 17, "sarah@somedomain.com", 3, true));
    users.add(createUser("William", 2022, Month.JANUARY, 1, "william@somedomain.com", 2, false));
    users.add(createUser("Jessica", 2013, Month.MARCH, 9, "jessica@someotherdomain.com", 2, true));
    users.add(createUser("David", 2011, Month.AUGUST, 12, "david@someotherdomain.com", 1, false));
    users.add(createUser("Linda", 2019, Month.JUNE, 20, "linda@someotherdomain.com", 0, true));
    users.add(createUser("Michael", 2022, Month.MAY, 11, "michael@someotherdomain.com", 3, true));
    users.add(createUser("Elizabeth", 2021, Month.OCTOBER, 2, "elizabeth@somedomain.com", 1, true));

    return users;
  }

  private static User createUser(String name, int year, Month month, int dayOfMonth, String email, int level, boolean active) {
    User user = new User(name, LocalDate.of(year, month, dayOfMonth));
    user.setEmail(email);
    user.setLevel(level);
    user.setActive(active);
    return user;
  }

  @AfterAll
  void afterAll() {
    userRepo.deleteAll();
  }
}
