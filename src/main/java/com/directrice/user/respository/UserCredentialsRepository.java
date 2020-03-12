package com.directrice.user.respository;

import com.directrice.user.entity.UserCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends MongoRepository<UserCredentials,String> {
}
