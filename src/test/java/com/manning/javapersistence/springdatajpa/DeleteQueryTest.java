package com.manning.javapersistence.springdatajpa;

import com.manning.javapersistence.springdatajpa.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteQueryTest extends SpringdatajpaScratchApplicationTests {
  @Test
  void testDeleteByLevel() {
    int deleted = userRepo.deleteByLevel(2);
    System.out.println("deleted: " + deleted);
    List<User> users = userRepo.findByLevel(2, Sort.by("username"));
    assertEquals(0, users.size());
  }

  @Test
  void testDeleteBulkByLevel() {
    int deleted = userRepo.deleteBulkByLevel(2);
    System.out.println("deleted: " + deleted);
    List<User> users = userRepo.findByLevel(2, Sort.by("username"));
    assertEquals(0, users.size());
  }
}
