package com.cardif.dnatest.services.impls;

import com.cardif.dnatest.entities.DnaEntity;
import com.cardif.dnatest.reposotories.DnaRepository;
import com.cardif.dnatest.services.AlienDetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AlienDetectorServiceImpl implements AlienDetectorService {
    private static final int SEQUENCE_LENGTH = 4;
    private static final int REQUIRED_SEQUENCES = 2;
    @Autowired
    private DnaRepository dnaRepository;

    @Override
    @Cacheable("dna")
    public Boolean isAlien(String[] dna) {
        int n = dna.length;
        int foundSequences = 0;
        Boolean isAlienDetected = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (checkSequence(dna, i, j, 0, 1) || // Horizontal
                        checkSequence(dna, i, j, 1, 0) || // Vertical
                        checkSequence(dna, i, j, 1, 1) || // Diagonal Top-Left to Bottom-Right
                        checkSequence(dna, i, j, 1, -1))  // Diagonal Bottom-Left to Top-Right
                {
                    foundSequences++;
                    if (foundSequences >= REQUIRED_SEQUENCES) {
                        isAlienDetected = true;
                        saveDna(dna, isAlienDetected);
                        return true;
                    }
                }
            }
        }
        saveDna(dna, isAlienDetected);
        return isAlienDetected;
    }

    private static Boolean checkSequence(String[] dna, int row, int col, int rowIncrement, int colIncrement) {
        int n = dna.length;
        char firstChar = dna[row].charAt(col);
        int count = 1;

        for (int k = 1; k < SEQUENCE_LENGTH; k++) {
            int newRow = row + k * rowIncrement;
            int newCol = col + k * colIncrement;

            if (newRow >= n || newCol >= n || newCol < 0 || dna[newRow].charAt(newCol) != firstChar) {
                return false;
            }
            count++;
        }

        return count == SEQUENCE_LENGTH;
    }

    public void saveDna(String[] dna, Boolean isAlien) {
        String sequence = String.join("", dna);
        if (!dnaRepository.existsBySequence(sequence)) {
            DnaEntity dnaEntity = new DnaEntity();
            dnaEntity.setSequence(sequence);
            dnaEntity.setIsAlien(isAlien);
            dnaRepository.save(dnaEntity);
        }
    }

}
