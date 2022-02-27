package com.paycore.loanapproval.controller;

import com.paycore.loanapproval.entity.Applicant;
import com.paycore.loanapproval.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/applicant")
@Validated
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @GetMapping
    public String test() {
        return "Testing is successful!";
    }

    @GetMapping("/all")
    public List<Applicant> getAllApplicants() {
        return applicantService.getAllApplicants();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getApplicant(@PathVariable int id) {
        Applicant applicant = applicantService.getApplicant(id);
        return new ResponseEntity<>(applicant, HttpStatus.OK);
    }

    @GetMapping("/{idNumber}")
    public ResponseEntity<?> getApplicant(@PathVariable String idNumber) {
        Applicant applicant = applicantService.getApplicant(idNumber);
        return new ResponseEntity<>(applicant, HttpStatus.OK);
    }

    @PostMapping("/add")
    public Applicant saveApplicant(@RequestBody Applicant applicant) {
        return applicantService.addApplicant(applicant);
    }

    @PutMapping("/update/{id}")
    public void updateApplicant(@PathVariable int id, @Valid @RequestBody Applicant applicant) {
        applicantService.updateApplicant(id, applicant);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteApplicant(@PathVariable int id) {
        applicantService.deleteApplicant(id);
    }
}
