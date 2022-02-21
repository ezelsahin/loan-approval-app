package com.paycore.loanapproval.controller;

import com.paycore.loanapproval.entity.Applicant;
import com.paycore.loanapproval.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Applicant getApplicant(@RequestParam int id){
        return applicantService.getApplicant(id);
    }

    @GetMapping("/add")
    public Applicant saveApplicant(@RequestBody Applicant applicant){
        return applicantService.addApplicant(applicant);
    }

    @GetMapping("/update/{id}")
    public Applicant updateApplicant(@RequestParam int id, @RequestBody Applicant applicant) {
        return applicantService.updateApplicant(id, applicant);
    }

    @GetMapping("/delete")
    public boolean deleteApplicant(@RequestParam int id){
        return applicantService.deleteApplicant(id);
    }
}
