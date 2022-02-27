package com.paycore.loanapproval.service;

/**
 * Service for creating a rating value which is used for calculating max loan limit.
 */
public interface RatingCalculationService {

    int getRating();

}
