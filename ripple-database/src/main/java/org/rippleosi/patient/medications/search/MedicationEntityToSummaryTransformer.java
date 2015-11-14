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

import org.apache.commons.collections4.Transformer;
import org.hl7.fhir.instance.model.Medication;
import org.rippleosi.patient.medication.model.MedicationSummary;
import org.rippleosi.patient.medications.model.MedicationEntity;
import org.rippleosi.patient.problems.model.ProblemEntity;
import org.rippleosi.patient.problems.model.ProblemSummary;

public class MedicationEntityToSummaryTransformer implements Transformer<MedicationEntity, MedicationSummary> {

    @Override
    public MedicationSummary transform(MedicationEntity entity) {
        MedicationSummary summary = new MedicationSummary();

        summary.setSourceId(String.valueOf(entity.getId()));
        summary.setSource("Crimson Tide iOS");
        summary.setName(entity.getName());
        summary.setDoseAmount(entity.getDoseAmount());

        return summary;
    }
}
