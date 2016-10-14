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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ie.mater.common.service.AbstractMaterService;
import org.apache.commons.collections4.CollectionUtils;
import org.rippleosi.patient.summary.model.PatientSummary;
import org.rippleosi.search.common.model.PageableTableQuery;
import org.rippleosi.search.patient.stats.PatientStatsSearch;
import org.rippleosi.search.patient.stats.model.SearchTablePatientDetails;
import org.rippleosi.search.patient.stats.model.SearchTableResults;
import org.springframework.stereotype.Service;

@Service
public class MaterPatientStatsSearch extends AbstractMaterService implements PatientStatsSearch {

    @Override
    public SearchTableResults findAssociatedPatientData(PageableTableQuery tableQuery, List<PatientSummary> patientSummaries) {
        SearchTableResults searchTableResults = new SearchTableResults();

        String totalPatients = String.valueOf(patientSummaries.size());
        searchTableResults.setTotalPatients(totalPatients);

        List<SearchTablePatientDetails> patientDetails =
            CollectionUtils.collect(patientSummaries, new PatientSummaryToSearchTablePatientDetailsTransformer(), new ArrayList<>());

        searchTableResults.setPatientDetails(patientDetails);

        return searchTableResults;
    }
}
