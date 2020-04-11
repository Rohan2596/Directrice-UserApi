package com.directrice.user.service;

import com.directrice.user.dto.KycDTO;
import com.directrice.user.entity.KYCDetails;
import com.directrice.user.entity.User;
import com.directrice.user.enumeration.KYCStatus;
import com.directrice.user.exception.UserApiException;
import com.directrice.user.respository.KYCRepository;
import com.directrice.user.respository.UserRepository;
import com.directrice.user.utility.TokenGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KYCServiceImp implements KYCService {

    @Autowired
    KYCRepository kycRepository;

    @Autowired
    TokenGenerators tokenGenerators;

    @Autowired
    UserRepository userRepository;

    private String getUserId(String token){
      UUID userId =tokenGenerators.decodeToken(token);
       User user=userRepository.findById(userId)
                        .orElseThrow(()->new UserApiException(UserApiException.ExceptionTypes.UNAUTHORIZED));
        return user.getUserId().toString();
    }

    @Override
    public String createKYCDocuments(String token) {
        String  userId= this.getUserId(token);
       Optional<KYCDetails> kycOptional=kycRepository.findByUserId(userId);
       if(kycOptional.isPresent()){
           throw new UserApiException(UserApiException.ExceptionTypes.ALREADY_KYC_INITIALIZE);
       }
        KYCDetails kycDetails =new KYCDetails(userId);
        kycRepository.save(kycDetails);
        return "KYCDetails created.";
    }

    @Override
    public String updateKYCDocuments(String token, KycDTO kycDTO) {
        this.getUserId(token);
        KYCDetails kycDetails = kycRepository.findById(UUID.fromString(kycDTO.getKycId()))
                    .orElseThrow(() ->  new UserApiException(UserApiException.ExceptionTypes.INVALID_KYC_ID));
        kycDetails.setAadharCardNo(kycDTO.getAadharCardNo());
        kycDetails.setStatus(KYCStatus.VALIDATION_ASKED.name());
        kycDetails.setPanCardNo(kycDTO.getPanCardNo());
        kycDetails.setUpdatedTimeStamp(LocalDateTime.now());
        kycRepository.save(kycDetails);
        return "Updated Successfully";
    }

    @Override
    public String getUserDocuments(String token,String kycId) {
        getUserId(token);
        KYCDetails kycDetails = kycRepository.findById(UUID.fromString(kycId))
                .orElseThrow(() ->  new UserApiException(UserApiException.ExceptionTypes.INVALID_KYC_ID));
        if(kycDetails.getAadharCardNo().equals("") && kycDetails.getPanCardNo().equals("")){
            return KYCStatus.VALIDATION_ASKED.name();
        }
        return KYCStatus.VALIDATED.name();
    }

    @Override
    public List<KYCDetails> getAllUserDocuments() {
        return kycRepository.findAll();
    }


}
