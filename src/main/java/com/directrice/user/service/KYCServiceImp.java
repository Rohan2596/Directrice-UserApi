package com.directrice.user.service;

import com.directrice.user.dto.KycDTO;
import com.directrice.user.entity.KYC;
import com.directrice.user.enumeration.KYCStatus;
import com.directrice.user.exception.UserApiException;
import com.directrice.user.respository.KYCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class KYCServiceImp implements KYCService {

    @Autowired
    KYCRepository kycRepository;

    @Override
    public String createKYCDocuments(String token) {
        KYC kyc=new KYC(UUID.fromString(token));
        kycRepository.save(kyc);
        return "KYC created.";
    }

    @Override
    public String updateKYCDocuments(String token, KycDTO kycDTO) {
     KYC kyc = kycRepository.findById(UUID.fromString(kycDTO.getKycId()))
                .orElseThrow(() ->  new UserApiException(UserApiException.ExceptionTypes.INVALID_KYC_ID));
     kyc.setAadhaarCardNo(kycDTO.getAadhaarCardNo());
     kyc.setStatus(KYCStatus.VALIDATION_ASKED.name());
     kyc.setPanCardNo(kyc.getPanCardNo());
     kyc.setUpdatedTimeStamp(LocalDateTime.now());
     kycRepository.save(kyc);
     return "Updated Successfully";
    }

    @Override
    public String getUserDocuments(String token) {
        KYC kyc = kycRepository.findById(UUID.fromString(token))
                .orElseThrow(() ->  new UserApiException(UserApiException.ExceptionTypes.INVALID_KYC_ID));
        return KYCStatus.VALIDATED.name();
    }

    @Override
    public List<KYC> getAllUserDocuments() {
        return kycRepository.findAll();
    }


}
