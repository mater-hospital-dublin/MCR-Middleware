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
package org.rippleosi.patient.haematology.jointscores.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.rippleosi.common.service.AbstractListQueryStrategy;
import org.rippleosi.patient.haematology.jointscores.model.JointScoreSummary;

public class JointScoreSummaryQueryStrategy extends AbstractListQueryStrategy<JointScoreSummary> {

    JointScoreSummaryQueryStrategy(String patientId) {
        super(patientId);
    }

    @Override
    public String getQuery(String namespace, String patientId) {
        return "select a/uid/value as uid, " +
            "a_a/data[at0001|Event Series|]/events[at0002|Any event|]/data[at0003]/items[at0004|Overall score|]/value/magnitude as total_score, " +
            "a_a/data[at0001|Event Series|]/origin/value as date_recorded " +
            "from EHR e " +
            "contains COMPOSITION a[openEHR-EHR-COMPOSITION.encounter.v1] " +
            "contains OBSERVATION a_a[openEHR-EHR-OBSERVATION.haemophilia_joint_assessment_score.v0] " +
            "where a/name/value='Patient visit - Scores' " +
            "and e/ehr_status/subject/external_ref/namespace = '" + namespace + "' " +
            "and e/ehr_status/subject/external_ref/id/value = '" + patientId + "'";
    }

    @Override
    public List<JointScoreSummary> transform(List<Map<String, Object>> resultSet) {
        return CollectionUtils.collect(resultSet, new JointScoreSummaryTransformer(), new ArrayList<>());
    }
}
