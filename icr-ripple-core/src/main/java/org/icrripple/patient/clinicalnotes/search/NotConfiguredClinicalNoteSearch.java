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
package org.icrripple.patient.clinicalnotes.search;

import java.util.List;

import org.rippleosi.common.exception.ConfigurationException;
import org.icrripple.patient.clinicalnotes.model.ClinicalNoteDetails;
import org.icrripple.patient.clinicalnotes.model.ClinicalNoteSummary;
import org.rippleosi.common.types.RepoSource;
import org.rippleosi.common.types.RepoSourceType;

public class NotConfiguredClinicalNoteSearch implements ClinicalNoteSearch {

    @Override
    public RepoSource getSource() {
        return RepoSourceType.NONE;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public List<ClinicalNoteSummary> findAllClinicalNotes(String patientId) {
        throw ConfigurationException.unimplementedTransaction(ClinicalNoteSearch.class);
    }

    @Override
    public ClinicalNoteDetails findClinicalNote(String patientId, String clinicalNoteId) {
        throw ConfigurationException.unimplementedTransaction(ClinicalNoteSearch.class);
    }
}
