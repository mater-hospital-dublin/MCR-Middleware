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
package org.rippleosi.patient.haematology.eq5l.search;

import java.util.List;
import java.util.Map;

import org.rippleosi.common.exception.DataNotFoundException;
import org.rippleosi.common.service.AbstractQueryStrategy;
import org.rippleosi.patient.haematology.eq5l.model.EQ5LDetails;

public class EQ5LDetailsQueryStrategy extends AbstractQueryStrategy<EQ5LDetails> {

    private final String eq5lId;

    EQ5LDetailsQueryStrategy(String patientId, String eq5lId) {
        super(patientId);
        this.eq5lId = eq5lId;
    }

    @Override
    public String getQuery(String namespace, String patientId) {
        return "select a/uid/value as uid, " +
            "b_a/data[at0001|Event Series|]/events[at0002|Any event|]/data[at0003]/items[at0004|Mobility|]/value/symbol/value as mobility, " +
            "b_a/data[at0001|Event Series|]/events[at0002|Any event|]/data[at0003]/items[at0006|Self-care|]/value/symbol/value as self_care, " +
            "b_a/data[at0001|Event Series|]/events[at0002|Any event|]/data[at0003]/items[at0007|Usual activities|]/value/symbol/value as usual_activities, " +
            "b_a/data[at0001|Event Series|]/events[at0002|Any event|]/data[at0003]/items[at0008|Pain/discomfort|]/value/symbol/value as pain, " +
            "b_a/data[at0001|Event Series|]/events[at0002|Any event|]/data[at0003]/items[at0009|Anxiety/depression|]/value/symbol/value as anxiety, " +
            "b_a/data[at0001|Event Series|]/events[at0002|Any event|]/data[at0003]/items[at0010|Overall health|]/value/magnitude as life_score, " +
            "b_a/data[at0001|Event Series|]/origin/value as date_recorded " +
            "from EHR e " +
            "contains COMPOSITION a[openEHR-EHR-COMPOSITION.encounter.v1] " +
            "contains OBSERVATION b_a[openEHR-EHR-OBSERVATION.eq_5d_5l.v0] " +
            "where a/name/value='Patient visit - Scores' " +
            "and a/uid/value='" + eq5lId + "' " +
            "and e/ehr_status/subject/external_ref/namespace = '" + namespace + "' " +
            "and e/ehr_status/subject/external_ref/id/value = '" + patientId + "'";
    }

    @Override
    public EQ5LDetails transform(List<Map<String, Object>> resultSet) {

        if (resultSet.isEmpty()) {
            throw new DataNotFoundException("No results found");
        }

        return new EQ5LDetailsTransformer().transform(resultSet);
    }
}
