package com.paycore.loanapproval.service.impl;

import com.paycore.loanapproval.entity.Loan;
import com.paycore.loanapproval.repository.LoanResultRepository;
import com.paycore.loanapproval.service.LoanRequestService;
import com.paycore.loanapproval.service.RatingCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanRequestServiceImpl implements LoanRequestService {

    @Autowired
    private LoanResultRepository loanResultRepository;

    @Autowired
    private RatingCalculationService ratingCalculationService;

    private int ratio = 4 ;

    @Override
    public int maxLimit(int rating, int monthlyIncome){ // maaş ve kredi skoruna göre maksimum limit hesabı yapılıyor
        return ( rating > 500 ? ( (rating < 1000) ? (monthlyIncome > 5000 ? 20000 : 10000 ) : (monthlyIncome * ratio) ) : 0);
    }

    @Override
    public boolean loanStatus(int maxLimit){ // maksimum limit 0 dönerse talep reddedilmiş, 0'dan farklıysa onaylanmış demektir
        return maxLimit != 0;
    }

    @Override
    public Loan sendRequest(String idNumber, int monthlyIncome){
        int rating = ratingCalculationService.getRating();
        int maxLimit = maxLimit(rating, monthlyIncome);
        String requestStatus = (loanStatus(maxLimit) == true ? "APPROVED" : "DENIED");

        Loan loan = new Loan(idNumber, requestStatus, maxLimit);

        return loanResultRepository.save(loan);
    }

    @Override
    public Loan getLoan(String idNumber){

        return loanResultRepository.findByIdNumber(idNumber); //orElseThrow(() -> new NotFoundException("Loan application"));
    }

    @Override
    public List<Loan> getAllLoanRequests(){
        return loanResultRepository.findAll();
    }

}
