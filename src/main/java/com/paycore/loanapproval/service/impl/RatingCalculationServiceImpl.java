package com.paycore.loanapproval.service.impl;

import com.paycore.loanapproval.service.RatingCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RatingCalculationServiceImpl implements RatingCalculationService {

    private static final int max = 1500;
    private static final int min = 100;

    /**
     * Calls getRating(int max, int min) with specified parameters in class.
     * @return Returns the generated value by getRating(int max, int min).
     */
    @Override
    public int getRating(){
        return getRating(max, min);
    }

    /**
     * @param max Top value of the interval
     * @param min Min value of the interval
     * @return Returns randomly generated value between given parameters.
     */
    private int getRating(int max, int min){
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }
}
