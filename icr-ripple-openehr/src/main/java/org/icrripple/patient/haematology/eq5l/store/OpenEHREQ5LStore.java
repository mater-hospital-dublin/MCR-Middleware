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
package org.icrripple.patient.haematology.eq5l.store;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Consume;
import org.icrripple.patient.haematology.eq5l.model.EQ5LDetails;
import org.rippleosi.common.service.AbstractOpenEhrService;
import org.rippleosi.common.service.CreateStrategy;
import org.rippleosi.common.service.DefaultStoreStrategy;
import org.rippleosi.common.service.UpdateStrategy;
import org.rippleosi.common.util.DateFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("Duplicates")
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

        String mobility = eq5l.getMobility();
        String selfCare = eq5l.getSelfCare();
        String usualActivities = eq5l.getUsualActivities();
        String pain = eq5l.getPain();
        String anxiety = eq5l.getAnxiety();
        Integer lifeScore = eq5l.getLifeScore();
        Date dateRecorded = eq5l.getDateRecorded();

        Map<String, Object> content = new HashMap<>();

        content.put("ctx/language", "en");
        content.put("ctx/territory", "GB");
        content.put("ctx/composer_name", "Dr Tony Shannon");

        content.put(EQ5L_PREFIX + "mobility|code", getMobilityCode(mobility));
        content.put(EQ5L_PREFIX + "mobility|value", mobility);
        content.put(EQ5L_PREFIX + "self-care|code", getSelfCareCode(selfCare));
        content.put(EQ5L_PREFIX + "self-care|value", selfCare);
        content.put(EQ5L_PREFIX + "usual_activities|code", getUsualActivitiesCode(usualActivities));
        content.put(EQ5L_PREFIX + "usual_activities|value", usualActivities);
        content.put(EQ5L_PREFIX + "pain_discomfort|code", getPainCode(pain));
        content.put(EQ5L_PREFIX + "pain_discomfort|value", pain);
        content.put(EQ5L_PREFIX + "anxiety_depression|code", getAnxietyCode(anxiety));
        content.put(EQ5L_PREFIX + "anxiety_depression|value", anxiety);
        content.put(EQ5L_PREFIX + "overall_health", lifeScore);
        content.put(EQ5L_PREFIX + "time", DateFormatter.toString(dateRecorded));

        return content;
    }

    private String getMobilityCode(String mobility) {
        Map<String, String> codes = new HashMap<>();

        codes.put("No problems", "at0005");
        codes.put("Slight problems", "at0012");
        codes.put("Moderate problems", "at0013");
        codes.put("Severe problems", "at0014");
        codes.put("Unable to walk about.", "at0015");

        return codes.get(mobility);
    }

    private String getSelfCareCode(String selfCare) {
        Map<String, String> codes = new HashMap<>();

        codes.put("No problems", "at0016");
        codes.put("Slight problems", "at0017");
        codes.put("Moderate problems", "at0018");
        codes.put("Severe problems", "at0019");
        codes.put("Unable to wash or dress", "at0020");

        return codes.get(selfCare);
    }

    private String getUsualActivitiesCode(String usualActivities) {
        Map<String, String> codes = new HashMap<>();

        codes.put("No problems", "at0021");
        codes.put("Slight problems", "at0022");
        codes.put("Moderate problems", "at0023");
        codes.put("Severe problems", "at0024");
        codes.put("Unable to do my usual activities", "at0025");

        return codes.get(usualActivities);
    }

    private String getPainCode(String pain) {
        Map<String, String> codes = new HashMap<>();

        codes.put("No pain or discomfort", "at0026");
        codes.put("Slight pain or discomfort", "at0027");
        codes.put("Moderate pain or discomfort", "at0028");
        codes.put("Severe pain or discomfort", "at0029");
        codes.put("Extreme pain or discomfort", "at0030");

        return codes.get(pain);
    }

    private String getAnxietyCode(String anxiety) {
        Map<String, String> codes = new HashMap<>();

        codes.put("No anxiety or depression", "at0031");
        codes.put("Slight anxiety or depression", "at0032");
        codes.put("Moderate anxiety or depression", "at0033");
        codes.put("Severe anxiety or depression", "at0034");
        codes.put("Extreme anxiety or depression", "at0035");

        return codes.get(anxiety);
    }
}
