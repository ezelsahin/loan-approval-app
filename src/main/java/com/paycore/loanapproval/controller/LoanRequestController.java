package com.paycore.loanapproval.controller;

import com.paycore.loanapproval.entity.Applicant;
import com.paycore.loanapproval.entity.Loan;
import com.paycore.loanapproval.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controls all requests send to the "/loan" url and responses them
 */
@RestController
@RequestMapping("/loan")
public class LoanRequestController {

    @Autowired
    private LoanRequestService loanRequestService;

    @Autowired
    private ApplicantController applicantController;

    /**
     * When applicant form on index page is filled and clicked to submit button this method gets called and saves
     * the new applicant entity to the database with given information on form. Then calls the sendRequest method
     * with required parameters for calculation of maximum loan limit for given applicant.
     * @param applicant A given applicant entity with all parameters
     */
    @PostMapping("/submit")
    public void submitRequest(Applicant applicant) {
        applicantController.saveApplicant(applicant);
        loanRequestService.sendRequest(applicant.getIdNumber(), applicant.getMonthlyIncome());
    }

    /**
     * @return Returns all loan entities which are saved in the database on "/loan/all" url
     */
    @GetMapping("/all")
    public List<Loan> getAllLoanRequest() {      //tüm başvuruları görüntüleme metodu
        return loanRequestService.getAllLoanRequests();
    }

    /**
     * @param idNumber An idNumber value which an applicant entity has
     * @return Returns the loan entity which has given idNumber value on "/loan/idNumber/{idNumber}" url
     */
    @GetMapping("/idNumber/{idNumber}")
    public ResponseEntity<?> getLoan(@PathVariable String idNumber) {   //başvuru sorgulama metodu
        Loan loan = loanRequestService.getLoan(idNumber);
        ResponseEntity<?> response = new ResponseEntity<>(loan, HttpStatus.OK);
        return response;
    }

    /**
     * @param phoneNumber Value of a phoneNumber parameter which belongs to an applicant entity
     * @return Returns the SMS message which is generated for owner of the given phoneNumber value
     * on "/loan/sms/{phoneNumber}" url
     */
    @GetMapping("/sms/{phoneNumber}")
    public String getSms(@PathVariable String phoneNumber){
        return loanRequestService.showSms(phoneNumber);
    }

}
