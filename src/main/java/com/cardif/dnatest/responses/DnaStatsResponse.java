package com.cardif.dnatest.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@Data
@AllArgsConstructor
@Generated
public class DnaStatsResponse {
    private Long countAlienDna;
    private Long countHumanDna;
    private Double ratio;
}
