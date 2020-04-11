package com.directrice.user.respository;

import com.directrice.user.entity.KYCDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface KYCRepository extends JpaRepository<KYCDetails, UUID> {

    Optional<KYCDetails> findByUserId(String userId);
    Optional<KYCDetails> findByUserIdAndKycId(String userId,String kycId);

}
