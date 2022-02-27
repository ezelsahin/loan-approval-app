package com.paycore.loanapproval.service.impl;

import com.paycore.loanapproval.service.RatingCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class RatingCalculationServiceImpl implements RatingCalculationService {

    private static final int max = 1500;
    private static final int min = 100;

    @Override
    public int getRating(){
        return getRating(max, min);
    }

    /**
     * Returns randomly generated value between given parameters.
     * @param max Top value of the interval
     * @param min Min value of the interval
     * @return
     */
    private int getRating(int max, int min){ // max ve min değerleri arasında random sayı üreten metot
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }
}
