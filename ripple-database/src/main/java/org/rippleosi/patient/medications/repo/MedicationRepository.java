package org.rippleosi.patient.medications.repo;

import java.util.List;

import org.rippleosi.patient.medications.model.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<MedicationEntity, Long> {

    List<MedicationEntity> findAllByNhsNumber(String nhsNumber);
}
