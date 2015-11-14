/*
 *   Copyright 2015 Ripple OSI
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */
package org.rippleosi.patient.medications.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.hl7.fhir.instance.model.Condition;
import org.rippleosi.common.exception.ConfigurationException;
import org.rippleosi.patient.medication.model.MedicationDetails;
import org.rippleosi.patient.medication.model.MedicationHeadline;
import org.rippleosi.patient.medication.model.MedicationSummary;
import org.rippleosi.patient.medication.search.MedicationSearch;
import org.rippleosi.patient.medications.model.MedicationEntity;
import org.rippleosi.patient.medications.repo.MedicationRepository;
import org.rippleosi.patient.problems.model.ProblemDetails;
import org.rippleosi.patient.problems.model.ProblemEntity;
import org.rippleosi.patient.problems.model.ProblemHeadline;
import org.rippleosi.patient.problems.model.ProblemSummary;
import org.rippleosi.patient.problems.repo.ProblemRepository;
import org.rippleosi.patient.problems.search.ProblemSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LegacyMedicationSearch implements MedicationSearch {

    @Value("${legacy.datasource.priority:1100}")
    private int priority;

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public String getSource() {
        return "Crimson Tide iOS";
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public List<MedicationHeadline> findMedicationHeadlines(String patientId) {
        List<MedicationEntity> problems = medicationRepository.findAllByNhsNumber(patientId);
        return CollectionUtils.collect(problems, new MedicationEntityToHeadlineTransformer(), new ArrayList<>());
    }

    @Override
    public List<MedicationSummary> findAllMedications(String patientId) {
        List<MedicationEntity> problems = medicationRepository.findAllByNhsNumber(patientId);
        return CollectionUtils.collect(problems, new MedicationEntityToSummaryTransformer(), new ArrayList<>());
    }

    @Override
    public MedicationDetails findMedication(String patientId, String medicationId) {
        MedicationEntity problem = medicationRepository.findOne(Long.valueOf(medicationId));
        return new MedicationEntityToDetailsTransformer().transform(problem);
    }
}
