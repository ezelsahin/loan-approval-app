package com.paycore.loanapproval.service.impl;

import com.paycore.loanapproval.service.LoanRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanRequestServiceImpl implements LoanRequestService {

    private int ratio = 4 ;

    @Override
    public int maxLimit(int rating, int monthlyIncome){ // maaş ve kredi skoruna göre maksimum limit hesabı yapılıyor
        return ( rating > 500 ? ( (rating < 1000) ? (monthlyIncome > 5000 ? 20000 : 10000 ) : (monthlyIncome * ratio) ) : 0);
    }

    @Override
    public boolean loanStatus(int maxLimit){ // maksimum limit 0 dönerse talep reddedilmiş, 0'dan farklıysa onaylanmış demektir
        return maxLimit != 0;
    }

}
