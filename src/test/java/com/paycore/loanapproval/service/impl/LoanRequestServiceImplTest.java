package com.paycore.loanapproval.service.impl;

import com.paycore.loanapproval.entity.Loan;
import com.paycore.loanapproval.exception.NotFoundException;
import com.paycore.loanapproval.repository.LoanResultRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanRequestServiceImplTest {

    @Mock
    private LoanResultRepository loanResultRepository;

    @InjectMocks
    private LoanRequestServiceImpl loanRequestService;

    @Test
    void maxLimit_rating_lesser_than_500() {
        //init
        int rating = 300;
        int monthlyIncome = 4000;

        //validation
        Assert.assertEquals(loanRequestService.maxLimit(rating, monthlyIncome), 0);
    }

    @Test
    void maxLimit_rating_between_500_and_1000_and_monthlyIncome_lesser_than_5000() {
        //init
        int rating = 800;
        int monthlyIncome = 4000;

        //validation
        Assert.assertEquals(loanRequestService.maxLimit(rating, monthlyIncome), 10000);
    }

    @Test
    void maxLimit_rating_between_500_and_1000_and_monthlyIncome_greater_than_5000() {
        //init
        int rating = 800;
        int monthlyIncome = 6000;

        //validation
        Assert.assertEquals(loanRequestService.maxLimit(rating, monthlyIncome), 20000);
    }

    @Test
    void maxLimit_rating_greater_than_1000() {
        //init
        int rating = 1200;
        int monthlyIncome = 4000;
        int ratio = 4;

        //validation
        Assert.assertEquals(loanRequestService.maxLimit(rating, monthlyIncome), monthlyIncome * ratio);
    }

    @Test
    void loanStatus_true() {
        //init
        int maxLimit = 10000;

        //validation
        Assert.assertEquals(loanRequestService.loanStatus(maxLimit), true);

    }

    @Test
    void loanStatus_false() {
        //init
        int maxLimit = 0;

        //validation
        Assert.assertEquals(loanRequestService.loanStatus(maxLimit), false);

    }

    @Test
    void sendRequest() {
    }

    @Test
    void getLoan_successful() {
        //init
        Loan expectedLoan = new Loan("98765432101", "APPROVED", 20000);

        //stub
        when(loanResultRepository.findByIdNumber(expectedLoan.getIdNumber())).thenReturn(expectedLoan);

        //then
        Loan actualLoan = loanRequestService.getLoan("98765432101");

        //validation
        Assert.assertEquals(expectedLoan.getLoanLimit(), actualLoan.getLoanLimit());

    }

    @Test
    void getLoan_not_found() {

        //validation
        Assert.assertThrows(NotFoundException.class,
                () -> {
                    loanRequestService.getLoan("98765432101");
                }

        );

    }

    @Test
    void getAllLoanRequests() {
        //init
        Loan loan1 = new Loan("98765432101", "APPROVED", 20000);
        Loan loan2 = new Loan("98765432102", "DENIED", 0);
        List<Loan> expectedLoans = new ArrayList<>();
        expectedLoans.add(loan1);
        expectedLoans.add(loan2);

        //stub
        when(loanResultRepository.findAll()).thenReturn(expectedLoans);

        //then
        List<Loan> actualLoans = loanRequestService.getAllLoanRequests();
        Assert.assertEquals(expectedLoans.size(), actualLoans.size());
    }
}