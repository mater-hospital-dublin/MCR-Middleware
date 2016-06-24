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
package org.icrripple.patient.haematology.bleeds.store;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Consume;
import org.icrripple.patient.haematology.bleeds.model.BleedDetails;
import org.rippleosi.common.service.AbstractOpenEhrService;
import org.rippleosi.common.service.CreateStrategy;
import org.rippleosi.common.service.DefaultStoreStrategy;
import org.rippleosi.common.service.UpdateStrategy;
import org.rippleosi.common.util.DateFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenEHRBleedStore extends AbstractOpenEhrService implements BleedStore {

    @Value("${c4hOpenEHR.bleedTemplate}")
    private String bleedTemplate;

    private static final String BLEED_PREFIX = "patient_event_report/history:0/story_history:0/";

    @Override
    @Consume(uri = "activemq:Consumer.C4HOpenEHR.VirtualTopic.Marand.Bleeds.Create")
    public void create(String patientId, BleedDetails bleed) {
        Map<String, Object> content = createFlatJsonContent(bleed);

        CreateStrategy createStrategy = new DefaultStoreStrategy(patientId, bleedTemplate, content);

        createData(createStrategy);
    }

    @Override
    @Consume(uri = "activemq:Consumer.C4HOpenEHR.VirtualTopic.Marand.Bleeds.Update")
    public void update(String patientId, BleedDetails bleed) {
        Map<String, Object> content = createFlatJsonContent(bleed);

        UpdateStrategy updateStrategy = new DefaultStoreStrategy(bleed.getSourceId(), patientId,
                                                                 bleedTemplate, content);

        updateData(updateStrategy);
    }

    private Map<String, Object> createFlatJsonContent(BleedDetails bleed) {

        Map<String, Object> content = new HashMap<>();

        content.put("ctx/language", "en");
        content.put("ctx/territory", "GB");
        content.put("ctx/composer_name", "Dr Tony Shannon");

        String dateRecorded = DateFormatter.toString(bleed.getDateRecorded());
        String site = bleed.getSite();
        String severity = bleed.getSeverity();
        Double pain = bleed.getPain();
        String cause = bleed.getCause();
        String type = bleed.getType();

        content.put(BLEED_PREFIX + "time", dateRecorded);
        content.put(BLEED_PREFIX + "symptom_sign:0/symptom_sign_name", "Bleed event");
        content.put(BLEED_PREFIX + "symptom_sign:0/body_site:0", site);
        content.put(BLEED_PREFIX + "symptom_sign:0/severity_category|code", "at0024");
        content.put(BLEED_PREFIX + "symptom_sign:0/severity_category|value", severity);
        content.put(BLEED_PREFIX + "symptom_sign:0/severity_rating:0|magnitude", pain);
        content.put(BLEED_PREFIX + "symptom_sign:0/severity_rating:0|unit", 1);
        content.put(BLEED_PREFIX + "symptom_sign:0/joint_bleed_self_assessment:0/cause_of_bleed|code", "at0007");
        content.put(BLEED_PREFIX + "symptom_sign:0/joint_bleed_self_assessment:0/cause_of_bleed|value", cause);
        content.put(BLEED_PREFIX + "symptom_sign:0/joint_bleed_self_assessment:0/type_of_bleed|code", "at0012");
        content.put(BLEED_PREFIX + "symptom_sign:0/joint_bleed_self_assessment:0/type_of_bleed|value", type);

        return content;
    }
}
