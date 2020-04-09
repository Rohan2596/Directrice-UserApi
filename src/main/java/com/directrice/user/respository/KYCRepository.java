package com.directrice.user.respository;

import com.directrice.user.entity.KYC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KYCRepository extends JpaRepository<KYC, UUID>{
}
