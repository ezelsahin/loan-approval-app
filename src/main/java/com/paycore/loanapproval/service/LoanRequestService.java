package com.paycore.loanapproval.service;

import com.paycore.loanapproval.entity.Loan;

import java.util.List;

public interface LoanRequestService {

    int maxLimit(int rating, int monthlyIncome);  //maximum alınabilecek kredi limitini hesapla

    boolean loanStatus(int maxLimit);  //maximum limite göre kredi alıp alamama durumunu belirle

    Loan sendRequest(String idNumber, int monthlyIncome);  //başvuru alınan metot (boolean olacak)

    Loan getLoan(String idNumber);  //mevcut kredi başvurusunu sorgula

    List<Loan> getAllLoanRequests();  //mevcut tüm başvuruları getir

}
