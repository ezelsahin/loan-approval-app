package com.paycore.loanapproval.controller;

import com.paycore.loanapproval.entity.Applicant;
import com.paycore.loanapproval.entity.Loan;
import com.paycore.loanapproval.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanRequestController {

    @Autowired
    private LoanRequestService loanRequestService;

    @Autowired
    private ApplicantController applicantController;

    @PostMapping("/submit")
    public void submitRequest(Applicant applicant) {    //başvuruyu alan ve gerçekleştiren metot
        applicantController.saveApplicant(applicant);
        loanRequestService.sendRequest(applicant.getIdNumber(), applicant.getMonthlyIncome());
    }

//    public boolean sendRequest(String idNumber, int monthlyIncome) {    //başvuru işlemini gerçekleştiren metot
//        loanRequestService.sendRequest(idNumber, monthlyIncome)
//        return true;
//    }

    @GetMapping("/all")
    public List<Loan> getAllLoanRequest() {      //tüm başvuruları görüntüleme metodu
        return loanRequestService.getAllLoanRequests();
    }  //tüm başvuruları getir

    @GetMapping("/idNumber/{idNumber}")
    public ResponseEntity<?> getLoan(@PathVariable String idNumber) {   //başvuru sorgulama metodu
        Loan loan = loanRequestService.getLoan(idNumber);
        ResponseEntity<?> response = new ResponseEntity<>(loan, HttpStatus.OK);
        return response;
    }

    @GetMapping("/sms/{phoneNumber}")
    public String getSms(@PathVariable String phoneNumber){
        return loanRequestService.showSms(phoneNumber);
    }

}
