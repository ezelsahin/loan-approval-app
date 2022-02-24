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
        ResponseEntity<?> response = null;
        Applicant applicant = null;

        try {
            applicant = applicantService.getApplicant(id);
            response = new ResponseEntity<>(applicant, HttpStatus.OK);
        } catch (NotFoundException exception) {
            response = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            response = new ResponseEntity<>(exception.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }

        return response;

    }

    @PostMapping ("/add")
    public Applicant saveApplicant(@RequestBody Applicant applicant){
        return applicantService.addApplicant(applicant);
    }

    @PutMapping("/update/{id}")
    public Applicant updateApplicant(@RequestParam int id, @RequestBody Applicant applicant) {
        return applicantService.updateApplicant(id, applicant);
    }

    @DeleteMapping("/delete")
    public boolean deleteApplicant(@RequestParam int id){
        return applicantService.deleteApplicant(id);
    }
}
