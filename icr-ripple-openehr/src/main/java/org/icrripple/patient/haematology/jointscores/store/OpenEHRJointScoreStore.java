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
package org.icrripple.patient.haematology.jointscores.store;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Consume;
import org.icrripple.patient.haematology.jointscores.model.JointScoreDetails;
import org.rippleosi.common.service.AbstractOpenEhrService;
import org.rippleosi.common.service.CreateStrategy;
import org.rippleosi.common.service.DefaultStoreStrategy;
import org.rippleosi.common.service.UpdateStrategy;
import org.rippleosi.common.util.DateFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenEHRJointScoreStore extends AbstractOpenEhrService implements JointScoreStore {

    @Value("${c4hOpenEHR.jointScoresTemplate}")
    private String jointScoresTemplate;

    private static final String JOINT_SCORE_PREFIX =
        "patient_visit_-_scores/assessment_scales:0/haemophilia_joint_assessment_score:0/";

    @Override
    @Consume(uri = "activemq:Consumer.C4HOpenEHR.VirtualTopic.Ripple.JointScores.Create")
    public void create(String patientId, JointScoreDetails jointScore) {
        Map<String, Object> content = createFlatJsonContent(jointScore);

        CreateStrategy createStrategy = new DefaultStoreStrategy(patientId, jointScoresTemplate, content);

        createData(createStrategy);
    }

    @Override
    @Consume(uri = "activemq:Consumer.C4HOpenEHR.VirtualTopic.Ripple.JointScores.Update")
    public void update(String patientId, JointScoreDetails jointScore) {
        Map<String, Object> content = createFlatJsonContent(jointScore);

        UpdateStrategy updateStrategy = new DefaultStoreStrategy(jointScore.getSourceId(), patientId,
                                                                 jointScoresTemplate, content);

        updateData(updateStrategy);
    }

    private Map<String, Object> createFlatJsonContent(JointScoreDetails jointScore) {

        Map<String, Object> content = new HashMap<>();

        content.put("ctx/language", "en");
        content.put("ctx/territory", "GB");
        content.put("ctx/composer_name", "Dr Tony Shannon");

        Integer leftKnee = jointScore.getLeftKnee();
        Integer rightKnee = jointScore.getRightKnee();
        Integer leftElbow = jointScore.getLeftElbow();
        Integer rightElbow = jointScore.getRightElbow();
        Integer leftAnkle = jointScore.getLeftAnkle();
        Integer rightAnkle = jointScore.getRightAnkle();

        int i = 0;

        if (leftKnee != null && leftKnee > 0) {
            content = populateJointData(content, i, "Left knee", "at0009", leftKnee);
            i++;
        }
        if (rightKnee != null && rightKnee > 0) {
            content = populateJointData(content, i, "Right knee", "at0010", rightKnee);
            i++;
        }
        if (leftElbow != null && leftElbow > 0) {
            content = populateJointData(content, i, "Left elbow", "at0007", leftElbow);
            i++;
        }
        if (rightElbow != null && rightElbow > 0) {
            content = populateJointData(content, i, "Right elbow", "at0008", rightElbow);
            i++;
        }
        if (leftAnkle != null && leftAnkle > 0) {
            content = populateJointData(content, i, "Left ankle", "at0011", leftAnkle);
            i++;
        }
        if (rightAnkle != null && rightAnkle > 0) {
            content = populateJointData(content, i, "Right ankle", "at0012", rightAnkle);
        }

        content.put(JOINT_SCORE_PREFIX + "overall_score", jointScore.getTotalScore());
        content.put(JOINT_SCORE_PREFIX + "time", DateFormatter.toString(jointScore.getDateRecorded()));

        return content;
    }

    private Map<String, Object> populateJointData(Map<String, Object> content, int index,
                                                  String jointName, String jointCode, Integer jointScore) {
        content.put(JOINT_SCORE_PREFIX + "per-joint_assessment:" + index + "/joint_name|code", jointCode);
        content.put(JOINT_SCORE_PREFIX + "per-joint_assessment:" + index + "/joint_name|value", jointName);
        content.put(JOINT_SCORE_PREFIX + "per-joint_assessment:" + index + "/joint_score", jointScore);

        return content;
    }
}
