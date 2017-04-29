package com.talkie.database.repositories;

import com.talkie.database.model.FriendRelation;
import com.talkie.database.model.FriendRelationPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepository extends CrudRepository<FriendRelation, FriendRelationPK> {
    FriendRelation findOneByWhoAndWithWhom(Integer who, Integer withWhom);

}