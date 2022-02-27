package com.paycore.loanapproval.service;

import com.paycore.loanapproval.entity.Applicant;

import java.util.List;

/**
 * Service for all applicant CRUD operations
 */
public interface ApplicantService {

    /**
     * Gets an applicant entity from database with given id value
     * @param id An id value which given to applicant while saving it to database table
     * @return Returns the applicant entity which has given id value
     */
    Applicant getApplicant(int id);

    /**
     * Gets an applicant entity from database with given idNumber value
     * @param idNumber An idNumber value which an applicant entity has
     * @return Returns the applicant entity which has given idNumber value
     */
    Applicant getApplicant(String idNumber);

    /**
     * Shows all applicant information in the database
     * @return Returns all applicant entities that are saved to the database
     */
    List<Applicant> getAllApplicants();

    /**
     * Adds new applicant entities
     * @param applicant A given applicant entity with all parameters
     * @return Returns the applicant entity which is added to the database
     */
    Applicant addApplicant(Applicant applicant);

    /**
     * Updates an already saved applicants parameters which has given id value with given applicant parameters
     * @param id An id value which given to applicant while saving it to database table
     * @param applicant A given applicant entity with all parameters
     */
    void updateApplicant(int id, Applicant applicant);

    /**
     * Deletes a saved applicant entity which has given id value
     * @param id An id value which given to applicant while saving it to database table
     */
    void deleteApplicant(int id);

}
