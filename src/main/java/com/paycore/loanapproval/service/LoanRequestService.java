package com.paycore.loanapproval.service;

import com.paycore.loanapproval.entity.Loan;
import com.paycore.loanapproval.entity.RequestStatus;

import java.util.List;

/**
 * Service for all applicant CRUD operations and other operations
 */
public interface LoanRequestService {

    /**
     * Calculates the max loan limit according to given parameters with the business logic inside
     * @param rating A random generated value which comes from another service
     * @param monthlyIncome The monthlyIncome value which belongs to an applicant entity
     * @return Returns the max loan limit value which is calculated by the business logic inside the method
     */
    int maxLimit(int rating, int monthlyIncome);

    /**
     * Gets maxLimit value and controls if it is equal to zero. If it is equal to zero returns false, otherwise
     * retuns true
     * @param maxLimit The maximum loan limit value for an applicant entity which is calculated by maxLimit method
     * @return Returns the boolean value which is generated according to given maxLimit value
     */
    boolean loanStatus(int maxLimit);

    /**
     * This is the main method which calculates the loan application approval with given parameters according to
     * business logic. It calls getRating method and gets a rating value. Then calls maxLimit method with that
     * rating value and monthlyIncome value which was given at method call. Then calls loanStatus method with
     * maxLimit value which is just generated and creates a requestStatus value which is enum type. Then saves
     * the created loan entity to database with generated and taken values. After that, calls sendSms method with
     * required parameters for to transmit a message about max loan approval. Then finally returns the loan entity
     * which is just saved to the database.
     * @param idNumber An idNumber value which an applicant entity has
     * @param monthlyIncome The monthlyIncome value which belongs to an applicant entity
     * @return Returns generated loan entity in the method according to business logic
     */
    Loan sendRequest(String idNumber, int monthlyIncome);

    /**
     * Method for SMS simulation. It gets the phoneNumber value of an applicant entity from database with the given
     * idNumber value. Then creates a message about result of maximum loan limit approval query and simulates to
     * send it to the given phoneNumber as SMS message.
     * @param idNumber An idNumber value which an applicant entity has
     * @param requestStatus An enum value which is generated in sendRequest method according to maxLimit value
     * @param maxLimit The maximum loan limit value for an applicant entity which is calculated by maxLimit method
     * @return Returns a message about result of maximum loan limit approval query
     */
    String sendSms(String idNumber, RequestStatus requestStatus, int maxLimit);

    /**
     * Gets idNumber value from database with given phoneNumber value. Then gets requestStatus and maxLimit values
     * with that idNumber value. Then calls sendSms method with these values to create a message about maximum loan
     * approval.
     * @param phoneNumber Value of a phoneNumber parameter which belongs to an applicant entity
     * @return Returns the message which is created by sendSms method
     */
    String showSms(String phoneNumber);

    /**
     * Gets a loan entity from database with given idNumber value
     * @param idNumber An idNumber value which an applicant entity has
     * @return Returns the loan entity which has given idNumber value
     */
    Loan getLoan(String idNumber);

    /**
     * @return Returns all loan entities which are saved in the database
     */
    List<Loan> getAllLoanRequests();

}
