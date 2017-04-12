package com.project.database.repositories;

import com.project.database.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findOneByLogin(String login);
    List<User> findByLoginStartsWithIgnoreCaseOrNameStartsWithIgnoreCaseOrLastNameStartsWithIgnoreCase(String lettersLogin, String lettersName, String lettersLastName);
}