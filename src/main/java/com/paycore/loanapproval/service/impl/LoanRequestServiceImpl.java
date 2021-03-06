package com.paycore.loanapproval.service.impl;

import com.paycore.loanapproval.entity.Loan;
import com.paycore.loanapproval.entity.RequestStatus;
import com.paycore.loanapproval.exception.NotFoundException;
import com.paycore.loanapproval.repository.ApplicantRepository;
import com.paycore.loanapproval.repository.LoanResultRepository;
import com.paycore.loanapproval.service.LoanRequestService;
import com.paycore.loanapproval.service.RatingCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Contains all loan CRUD operations and other operations methods
 */
@Service
@RequiredArgsConstructor
public class LoanRequestServiceImpl implements LoanRequestService {

    @Autowired
    private LoanResultRepository loanResultRepository;

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private RatingCalculationService ratingCalculationService;

    private int ratio = 4 ;

    @Override
    public int maxLimit(int rating, int monthlyIncome){
        return ( rating >= 500 ? ( (rating < 1000) ? (monthlyIncome > 5000 ? 20000 : 10000 ) : (monthlyIncome * ratio) ) : 0);
    }

    @Override
    public boolean loanStatus(int maxLimit){ // maksimum limit 0 dönerse talep reddedilmiş, 0'dan farklıysa onaylanmış demektir
        return maxLimit != 0;
    }

    @Override
    public Loan sendRequest(String idNumber, int monthlyIncome){
        int rating = ratingCalculationService.getRating();
        int maxLimit = maxLimit(rating, monthlyIncome);
        RequestStatus requestStatus = (loanStatus(maxLimit) == true ? RequestStatus.APPROVED : RequestStatus.DENIED);

        Loan loan = new Loan(idNumber, requestStatus, maxLimit, rating);

        System.out.println(sendSms(idNumber, requestStatus, maxLimit));

        return loanResultRepository.save(loan);
    }

    @Override
    public String sendSms(String idNumber, RequestStatus requestStatus, int maxLimit){

        String phoneNumber = applicantRepository.findByIdNumber(idNumber).getPhoneNumber();

        String message = phoneNumber + ": Your loan request " + requestStatus + "! The maximum loan limit you can get is " + maxLimit + " TL!";

        return message;
    }

    @Override
    public String showSms(String phoneNumber){
        String idNumber = applicantRepository.findByPhoneNumber(phoneNumber).getIdNumber();
        Optional<String> message = Optional.ofNullable(sendSms(idNumber,
                loanResultRepository.findByIdNumber(idNumber).getRequestStatus(),
                loanResultRepository.findByIdNumber(idNumber).getLoanLimit()));
        return message.orElseThrow(() -> new NotFoundException("There is no SMS to show! Loan application"));
    }

    @Override
    public Loan getLoan(String idNumber){
        Optional<Loan> byIdNumber = Optional.ofNullable(loanResultRepository.findByIdNumber(idNumber));
        return byIdNumber.orElseThrow(() -> new NotFoundException("Loan application"));
    }

    @Override
    public List<Loan> getAllLoanRequests(){
        return loanResultRepository.findAll();
    }

}
