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
package org.rippleosi.patient.clinicalnotes.search;

import java.util.List;

import org.hl7.fhir.utilities.ucum.Search;
import org.rippleosi.common.service.AbstractOpenEhrService;
import org.rippleosi.patient.clinicalnotes.model.ClinicalNoteDetails;
import org.rippleosi.patient.clinicalnotes.model.ClinicalNoteSummary;
import org.rippleosi.patient.haematology.bleeds.model.BleedDetails;
import org.rippleosi.patient.haematology.bleeds.model.BleedSummary;
import org.rippleosi.patient.haematology.bleeds.search.BleedSearch;
import org.springframework.stereotype.Service;

@Service
public class OpenEHRClinicalNoteSearch extends AbstractOpenEhrService implements ClinicalNoteSearch {

    @Override
    public List<ClinicalNoteSummary> findAllClinicalNotes(String patientId) {
        ClinicalNoteSummaryQueryStrategy query = new ClinicalNoteSummaryQueryStrategy(patientId);

        return findData(query);
    }

    @Override
    public ClinicalNoteDetails findClinicalNote(String patientId, String clinicalNoteId) {
        ClinicalNoteDetailsQueryStrategy query = new ClinicalNoteDetailsQueryStrategy(patientId, clinicalNoteId);

        return findData(query);
    }
}
