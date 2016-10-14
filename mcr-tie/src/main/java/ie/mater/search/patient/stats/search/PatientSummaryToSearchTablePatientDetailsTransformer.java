/*
 * Copyright 2016 Mater Care Record
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ie.mater.search.patient.stats.search;

import java.util.Date;

import org.apache.commons.collections4.Transformer;
import org.rippleosi.patient.summary.model.PatientSummary;
import org.rippleosi.search.patient.stats.model.RecordHeadline;

public class PatientSummaryToSearchTablePatientDetailsTransformer implements Transformer<PatientSummary, MaterSearchTablePatientDetails> {

    @Override
    public MaterSearchTablePatientDetails transform(PatientSummary patientSummary) {
        MaterSearchTablePatientDetails patientDetails = new MaterSearchTablePatientDetails();

        patientDetails.setSourceId(patientSummary.getId());
        patientDetails.setNhsNumber(patientSummary.getId());
        patientDetails.setMrnNumber(patientSummary.getId());

        patientDetails.setName(patientSummary.getName());
        patientDetails.setAddress(patientSummary.getAddress());
        patientDetails.setDateOfBirth(patientSummary.getDateOfBirth());
        patientDetails.setGender(patientSummary.getGender());

        patientDetails.setVitalsHeadline(getPopulateVitalsHeadline());
        patientDetails.setOrdersHeadline(getPopulateOrdersHeadline());
        patientDetails.setMedsHeadline(getPopulateMedsHeadline());
        patientDetails.setResultsHeadline(getPopulateResultsHeadline());
        patientDetails.setTreatmentsHeadline(getPopulateTreatmentsHeadline());

        return patientDetails;
    }

    private RecordHeadline getPopulateVitalsHeadline() {
        return populateDummyHeadline();
    }

    private RecordHeadline getPopulateOrdersHeadline() {
        return populateDummyHeadline();
    }

    private RecordHeadline getPopulateMedsHeadline() {
        return populateDummyHeadline();
    }

    private RecordHeadline getPopulateResultsHeadline() {
        return populateDummyHeadline();
    }

    private RecordHeadline getPopulateTreatmentsHeadline() {
        return populateDummyHeadline();
    }

    private RecordHeadline populateDummyHeadline() {
        RecordHeadline record = new RecordHeadline();

        record.setSource("");
        record.setSourceId("");
        record.setLatestEntry(new Date());
        record.setTotalEntries("0");

        return record;
    }
}
