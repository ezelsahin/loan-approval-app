package com.paycore.loanapproval.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

    @GetMapping
    public String example(){
        return "Testing is successful!";
    }
}
