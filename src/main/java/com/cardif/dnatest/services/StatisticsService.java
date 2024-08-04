package com.cardif.dnatest.services;

import com.cardif.dnatest.responses.DnaStatsResponse;
import org.springframework.stereotype.Service;

public interface StatisticsService {

    public DnaStatsResponse getStats();
}
