package com.talkie.database.repositories;

import com.talkie.database.model.FriendRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepository extends CrudRepository<FriendRelation, Integer> {
    FriendRelation findOneByWhoAndWith(Integer who, Integer with);
}