package com.paycore.loanapproval.controller;

import com.paycore.loanapproval.entity.Applicant;
import com.paycore.loanapproval.exception.NotFoundException;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getApplicant(@PathVariable int id){
        Applicant applicant = applicantService.getApplicant(id);
        ResponseEntity<?> response = new ResponseEntity<>(applicant, HttpStatus.OK);
        return response;
    }

    @PostMapping("/add")
    public Applicant saveApplicant(@RequestBody Applicant applicant){
        return applicantService.addApplicant(applicant);
    }

    @PutMapping("/update/{id}")
    public boolean updateApplicant(@PathVariable int id, @RequestBody Applicant applicant) {
        return applicantService.updateApplicant(id, applicant);
    }

    @DeleteMapping("/delete")
    public boolean deleteApplicant(@RequestParam int id){
        return applicantService.deleteApplicant(id);
    }
}
