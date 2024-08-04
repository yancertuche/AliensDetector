package com.cardif.dnatest.services.impls;

import com.cardif.dnatest.entities.DnaEntity;
import com.cardif.dnatest.reposotories.DnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AlienDetectorServiceImplTest {

    @Mock
    private DnaRepository dnaRepository;

    @InjectMocks
    private AlienDetectorServiceImpl alienDetectorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Es un alien")
    public void testIsAlienDna() {
        String[] dna = {"HTGCDH", "CHDTDC", "TTHTDT", "AGHHDD", "CCCCTH", "TCHCTD"};

        when(dnaRepository.existsBySequence(anyString())).thenReturn(false);

        boolean result = alienDetectorService.isAlien(dna);

        assertTrue(result);
        verify(dnaRepository, times(1)).save(any(DnaEntity.class));
    }

    @Test
    @DisplayName("No es un alien")
    public void testIsNotAlienDna() {
        String[] dna = {"HTGCDH", "CHDTDC", "TTHTDT", "AGHHDD"};

        when(dnaRepository.existsBySequence(anyString())).thenReturn(false);

        boolean result = alienDetectorService.isAlien(dna);

        assertFalse(result);
        verify(dnaRepository, times(1)).save(any(DnaEntity.class));
    }

    @Test
    @DisplayName("Es un alien que ya está en BD")
    public void testIsAlienWithExistingDna() {
        String[] dna = {"HTGCDH", "CHDTDC", "TTHTDT", "AGHHDD", "CCCCTH", "TCHCTD"};

        when(dnaRepository.existsBySequence(anyString())).thenReturn(true);

        boolean result = alienDetectorService.isAlien(dna);

        assertTrue(result);
        verify(dnaRepository, times(0)).save(any(DnaEntity.class));
    }
}
