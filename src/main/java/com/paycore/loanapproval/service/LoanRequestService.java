package com.paycore.loanapproval.service;

public interface LoanRequestService {

    int maxLimit(int rating, int monthlyIncome);

    boolean loanStatus(int maxLimit);

}
