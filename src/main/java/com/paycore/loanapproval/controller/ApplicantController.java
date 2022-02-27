package com.paycore.loanapproval.controller;

import com.paycore.loanapproval.entity.Applicant;
import com.paycore.loanapproval.exception.InvalidRequestException;
import com.paycore.loanapproval.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @GetMapping
    public String example(){
        return "Testing is successful!";
    }

    @GetMapping("/all")
    public List<Applicant> getAllApplicants(){
        return applicantService.getAllApplicants();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getApplicant(@PathVariable int id){
        Applicant applicant = applicantService.getApplicant(id);
        ResponseEntity<?> response = new ResponseEntity<>(applicant, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{idNumber}")
    public ResponseEntity<?> getApplicant(@PathVariable String idNumber){
        Applicant applicant = applicantService.getApplicant(idNumber);
        ResponseEntity<?> response = new ResponseEntity<>(applicant, HttpStatus.OK);
        return response;
    }

    @PostMapping("/add")
    public Applicant saveApplicant(@RequestBody Applicant applicant){
        return applicantService.addApplicant(applicant);
    }

    @PutMapping("/update/{id}")
    public boolean updateApplicant(@PathVariable int id, @RequestBody Applicant applicant) {
        if (id <= 0) {
            throw new InvalidRequestException("Id Number can not be 0 or negative!");
        }
        if (applicant.getIdNumber() == null ) {
            throw new InvalidRequestException("Id Number can not be null!");
        }
        if (applicant.getPhoneNumber() == null ) {
            throw new InvalidRequestException("Phone Number can not be null!");
        }
        if (applicant.getFirstName() == null || applicant.getLastName() == null) {
            throw new InvalidRequestException("Name can not be null!");
        }
        return applicantService.updateApplicant(id, applicant);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteApplicant(@PathVariable int id){
        return applicantService.deleteApplicant(id);
    }
}
