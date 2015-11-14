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

import java.util.Date;

import org.apache.commons.collections4.Transformer;
import org.rippleosi.common.util.DateFormatter;
import org.rippleosi.patient.medication.model.MedicationDetails;
import org.rippleosi.patient.medications.model.MedicationEntity;

public class MedicationEntityToDetailsTransformer implements Transformer<MedicationEntity, MedicationDetails> {

    @Override
    public MedicationDetails transform(MedicationEntity entity) {
        MedicationDetails details = new MedicationDetails();

        details.setSourceId(String.valueOf(entity.getId()));
        details.setSource("Crimson Tide iOS");
        details.setName(entity.getName());
        details.setDoseAmount(entity.getDoseAmount());
        details.setDoseDirections(entity.getDoseDirections());
        details.setDoseTiming(entity.getDoseTiming());
        details.setRoute(entity.getRoute());
        details.setMedicationTerminology(entity.getTerminology());
        details.setMedicationCode(entity.getTerminologyCode());
        details.setAuthor(entity.getAuthor());
        details.setDateCreated(entity.getDateCreated());

        Date startDatetime = entity.getStartDatetime();
        String datetimeString = DateFormatter.toString(startDatetime);

        details.setStartDate(DateFormatter.toDateOnly(datetimeString));
        details.setStartTime(DateFormatter.toTimeOnly(datetimeString));

        return details;
    }
}
