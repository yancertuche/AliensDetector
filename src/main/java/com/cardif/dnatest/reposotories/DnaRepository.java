package com.cardif.dnatest.reposotories;

import com.cardif.dnatest.entities.DnaEntity;
import lombok.Generated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Generated
public interface DnaRepository extends JpaRepository<DnaEntity, Long> {
    Boolean existsBySequence(String sequence);
    Long countByIsAlien(Boolean isAlien);

}
