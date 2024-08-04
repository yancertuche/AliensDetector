package com.cardif.dnatest.services.impls;

import com.cardif.dnatest.reposotories.DnaRepository;
import com.cardif.dnatest.responses.DnaStatsResponse;
import com.cardif.dnatest.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private DnaRepository dnaRepository;

    @Override
    public DnaStatsResponse getStats() {
        Long countAlien = dnaRepository.countByIsAlien(true);
        Long countHuman = dnaRepository.countByIsAlien(false);
        Double ratio = (countAlien + countHuman) == 0 ? 0 : (double) countAlien / (countAlien + countHuman);
        return new DnaStatsResponse(countAlien, countHuman, ratio);
    }
}
