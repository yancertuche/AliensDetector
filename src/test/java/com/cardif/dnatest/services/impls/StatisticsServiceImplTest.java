package com.cardif.dnatest.services.impls;

import com.cardif.dnatest.reposotories.DnaRepository;
import com.cardif.dnatest.responses.DnaStatsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StatisticsServiceImplTest {
    @Mock
    private DnaRepository dnaRepository;

    @InjectMocks
    private StatisticsServiceImpl statisticsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Con estadisticas disponibles")
    public void testGetStats() {
        when(dnaRepository.countByIsAlien(true)).thenReturn(40L);
        when(dnaRepository.countByIsAlien(false)).thenReturn(100L);

        DnaStatsResponse stats = statisticsService.getStats();

        assertEquals(40L, stats.getCountAlienDna());
        assertEquals(100L, stats.getCountHumanDna());
        assertEquals(0.2857142857142857, stats.getRatio(), 0.001);
    }

    @Test
    @DisplayName("Sin estadisticas disponibles")
    public void testGetStatsNoData() {
        when(dnaRepository.countByIsAlien(true)).thenReturn(0L);
        when(dnaRepository.countByIsAlien(false)).thenReturn(0L);

        DnaStatsResponse stats = statisticsService.getStats();

        assertEquals(0L, stats.getCountAlienDna());
        assertEquals(0L, stats.getCountHumanDna());
        assertEquals(0.0, stats.getRatio(), 0.0001);
    }
}
