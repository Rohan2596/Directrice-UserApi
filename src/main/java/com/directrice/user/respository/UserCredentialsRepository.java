package com.directrice.user.respository;

import com.directrice.user.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials,UUID> {
    Optional<UserCredentials> findByEmailID(String emailId);
}
