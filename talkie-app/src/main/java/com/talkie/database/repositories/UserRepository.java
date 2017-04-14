package com.talkie.database.repositories;

import com.talkie.database.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {
    UserModel findOneByLogin(String login);

    @Query("SELECT u.name FROM UserModel u WHERE u.name LIKE CONCAT('%',:letters,'%') OR u.lastName LIKE CONCAT('%',:letters,'%')")
    List<UserModel> findUsersWithPartOfNameOrLastName(@Param("letters") String letters);
    
}
