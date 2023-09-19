package com.manning.javapersistence.springdatajpa.repository;

import com.manning.javapersistence.springdatajpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
