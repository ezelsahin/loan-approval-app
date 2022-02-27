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

/**
 * Controls all requests send to the "/applicant" url and responses them
 */
@RestController
@RequestMapping("/applicant")
@Validated
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    /**
     * A basic test method
     * @return Returns test message
     */
    @GetMapping
    public String test() {
        return "Testing is successful!";
    }

    /**
     * @return Returns all applicant entities on "/applicant/all" url
     */
    @GetMapping("/all")
    public List<Applicant> getAllApplicants() {
        return applicantService.getAllApplicants();
    }

    /**
     * @param id An id value which given to applicant while saving it to database table
     * @return Returns an applicant entity with given id value on "/applicant/id/{id}" url
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getApplicant(@PathVariable int id) {
        Applicant applicant = applicantService.getApplicant(id);
        return new ResponseEntity<>(applicant, HttpStatus.OK);
    }

    /**
     * @param idNumber An idNumber value which an applicant entity has
     * @return Returns an applicant entity with given idNumber value on "/applicant/idNumber/{idNumber}" url
     */
    @GetMapping("/idNumber/{idNumber}")
    public ResponseEntity<?> getApplicant(@PathVariable String idNumber) {
        Applicant applicant = applicantService.getApplicant(idNumber);
        return new ResponseEntity<>(applicant, HttpStatus.OK);
    }

    /**
     * Adds new applicant entities on "/applicant/add" url
     * @param applicant A given applicant entity with all parameters
     * @return Returns the applicant entity which is added to the database on "/applicant/add" url
     */
    @PostMapping("/add")
    public Applicant saveApplicant(@RequestBody @Valid Applicant applicant) {
        return applicantService.addApplicant(applicant);
    }

    /**
     * Updates an already saved applicants parameters which has given id value with given applicant parameters on
     * "/applicant/update/{id}" url
     * @param id An id value which given to applicant while saving it to database table
     * @param applicant A given applicant entity with all parameters
     */
    @PutMapping("/update/{id}")
    public void updateApplicant(@PathVariable int id, @Valid @RequestBody Applicant applicant) {
        applicantService.updateApplicant(id, applicant);
    }

    /**
     * Deletes a saved applicant entity which has given id value on "/delete/{id}" url
     * @param id An id value which given to applicant while saving it to database table
     */
    @DeleteMapping("/delete/{id}")
    public void deleteApplicant(@PathVariable int id) {
        applicantService.deleteApplicant(id);
    }
}
