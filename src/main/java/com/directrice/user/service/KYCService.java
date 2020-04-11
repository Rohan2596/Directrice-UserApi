package com.directrice.user.service;

import com.directrice.user.dto.KycDTO;
import com.directrice.user.entity.KYCDetails;

import java.util.List;

public interface KYCService {

    String createKYCDocuments(String token);
    String updateKYCDocuments(String token, KycDTO kycDTO);
    String getUserDocuments(String token,String kycId);
    List<KYCDetails> getAllUserDocuments();
}
