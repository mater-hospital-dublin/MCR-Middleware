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
package org.rippleosi.patient.haematology.eq5l.store;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Consume;
import org.rippleosi.common.service.AbstractOpenEhrService;
import org.rippleosi.common.service.CreateStrategy;
import org.rippleosi.common.service.DefaultStoreStrategy;
import org.rippleosi.common.service.UpdateStrategy;
import org.rippleosi.common.util.DateFormatter;
import org.rippleosi.patient.haematology.eq5l.model.EQ5LDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenEHREQ5LStore extends AbstractOpenEhrService implements EQ5LStore {

    @Value("${c4hOpenEHR.eq5lTemplate}")
    private String eq5lTemplate;

    private static final String EQ5L_PREFIX = "patient_visit_-_scores/assessment_scales:0/eq-5d-5l_health_status:0/";

    @Override
    @Consume(uri = "activemq:Consumer.C4HOpenEHR.VirtualTopic.Ripple.EQ5L.Create")
    public void create(String patientId, EQ5LDetails eq5l) {
        Map<String, Object> content = createFlatJsonContent(eq5l);

        CreateStrategy createStrategy = new DefaultStoreStrategy(patientId, eq5lTemplate, content);

        createData(createStrategy);
    }

    @Override
    @Consume(uri = "activemq:Consumer.C4HOpenEHR.VirtualTopic.Ripple.EQ5L.Update")
    public void update(String patientId, EQ5LDetails eq5l) {
        Map<String, Object> content = createFlatJsonContent(eq5l);

        UpdateStrategy updateStrategy = new DefaultStoreStrategy(eq5l.getSourceId(), patientId,
                                                                 eq5lTemplate, content);

        updateData(updateStrategy);
    }

    private Map<String, Object> createFlatJsonContent(EQ5LDetails eq5l) {

        Map<String, Object> content = new HashMap<>();

        content.put("ctx/language", "en");
        content.put("ctx/territory", "GB");
        content.put("ctx/composer_name", "Dr Tony Shannon");

        content.put(EQ5L_PREFIX + "mobility|code", "at0015");
        content.put(EQ5L_PREFIX + "mobility|value", eq5l.getMobility());
        content.put(EQ5L_PREFIX + "self-care|code", "at0017");
        content.put(EQ5L_PREFIX + "self-care|value", eq5l.getSelfCare());
        content.put(EQ5L_PREFIX + "usual_activities|code", "at0022");
        content.put(EQ5L_PREFIX + "usual_activities|value", eq5l.getUsualActivities());
        content.put(EQ5L_PREFIX + "pain_discomfort|code", "at0030");
        content.put(EQ5L_PREFIX + "pain_discomfort|value", eq5l.getPain());
        content.put(EQ5L_PREFIX + "anxiety_depression|code", "at0031");
        content.put(EQ5L_PREFIX + "anxiety_depression|value", eq5l.getAnxiety());
        content.put(EQ5L_PREFIX + "overall_health", eq5l.getLifeScore());
        content.put(EQ5L_PREFIX + "time", DateFormatter.toString(eq5l.getDateRecorded()));

        return content;
    }
}
