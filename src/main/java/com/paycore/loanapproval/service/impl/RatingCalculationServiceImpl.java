package com.paycore.loanapproval.service.impl;

import com.paycore.loanapproval.service.RatingCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RatingCalculationServiceImpl implements RatingCalculationService {

    private int max = 1500;
    private int min = 100;

    public int getRating(int max, int min){ // max ve min değerleri arasında random sayı üreten metot
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

    @Override
    public int getRating(){
        return getRating(max, min);
    }
}
