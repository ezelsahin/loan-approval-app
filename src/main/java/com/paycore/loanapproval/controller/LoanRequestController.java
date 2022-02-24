package com.paycore.loanapproval.controller;

import com.paycore.loanapproval.entity.Loan;
import com.paycore.loanapproval.exception.NotFoundException;
import com.paycore.loanapproval.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanRequestController {

    @Autowired
    private LoanRequestService loanRequestService;

    public boolean sendRequest(String idNumber, int monthlyIncome) {    //başvuru yapma metodu
        return true;
    }

    public List<Loan> getAllLoanRequest() {      //tüm başvuruları görüntüleme metodu
        return loanRequestService.getAllLoanRequests();
    }

    public ResponseEntity<?> getLoan(@PathVariable String idNumber) {   //başvuru sorgulama metodu
        ResponseEntity<?> response = null;
        Loan loan = null;

        try {
            loan = loanRequestService.getLoan(idNumber);
            response = new ResponseEntity<>(loan, HttpStatus.OK);
        } catch (NotFoundException exception) {
            response = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            response = new ResponseEntity<>(exception.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }

        return response;
    }

}
