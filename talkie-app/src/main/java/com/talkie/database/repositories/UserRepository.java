package com.talkie.database.repositories;

import com.talkie.database.model.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserModel, Integer> {
    UserModel findOneByLogin(String login);
    List<UserModel> findByLoginStartsWithIgnoreCaseOrNameStartsWithIgnoreCaseOrLastNameStartsWithIgnoreCase(String lettersLogin, String lettersName, String lettersLastName);
}