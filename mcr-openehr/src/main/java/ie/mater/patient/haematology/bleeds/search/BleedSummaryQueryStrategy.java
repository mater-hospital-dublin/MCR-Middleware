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
package ie.mater.patient.haematology.bleeds.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import ie.mater.patient.haematology.bleeds.model.BleedSummary;
import org.rippleosi.common.service.AbstractListQueryStrategy;

public class BleedSummaryQueryStrategy extends AbstractListQueryStrategy<BleedSummary> {

    BleedSummaryQueryStrategy(String patientId) {
        super(patientId);
    }

    @Override
    public String getQuery(String namespace, String patientId) {
        return "select a/uid/value as uid, " +
            "a_a/data[at0001|Event Series|]/origin/value as date_recorded, " +
            "b_a/items[at0151|Body site|]/value/value as site, " +
            "c_a/items[at0002|Cause of bleed|]/value/value as cause " +
            "from EHR e " +
            "contains COMPOSITION a[openEHR-EHR-COMPOSITION.encounter.v1] " +
            "contains OBSERVATION a_a[openEHR-EHR-OBSERVATION.story.v1] " +
            "contains CLUSTER b_a[openEHR-EHR-CLUSTER.symptom_sign.v1] " +
            "contains CLUSTER c_a[openEHR-EHR-CLUSTER.bleed_self_assessment.v0] " +
            "where a/name/value='Patient event report' " +
            "and e/ehr_status/subject/external_ref/namespace = '" + namespace + "' " +
            "and e/ehr_status/subject/external_ref/id/value = '" + patientId + "'";
    }

    @Override
    public List<BleedSummary> transform(List<Map<String, Object>> resultSet) {
        return CollectionUtils.collect(resultSet, new BleedSummaryTransformer(), new ArrayList<>());
    }
}
