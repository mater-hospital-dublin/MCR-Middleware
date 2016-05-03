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
package org.icrripple.patient.haematology.jointscores.search;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.Transformer;
import org.icrripple.patient.haematology.jointscores.model.JointScoreDetails;
import org.rippleosi.common.util.DateFormatter;

public class JointScoreDetailsTransformer implements Transformer<List<Map<String, Object>>, JointScoreDetails> {

    @Override
    public JointScoreDetails transform(List<Map<String, Object>> input) {

        Map<String, Object> firstEntry = input.get(0);

        String uid = MapUtils.getString(firstEntry, "uid");
        Integer totalScore = MapUtils.getInteger(firstEntry, "total_score");
        String dateRecorded = MapUtils.getString(firstEntry, "date_recorded");

        JointScoreDetails details = new JointScoreDetails();

        for (Map<String, Object> entry : input) {
            String jointName = MapUtils.getString(entry, "joint_name");
            Integer jointScore = MapUtils.getInteger(entry, "joint_score");

            details = extractJointScoreFromInput(details, jointName, jointScore);
        }

        details.setSource("openehr");
        details.setSourceId(uid);
        details.setTotalScore(totalScore);
        details.setDateRecorded(DateFormatter.toDate(dateRecorded));

        return details;
    }

    private JointScoreDetails extractJointScoreFromInput(JointScoreDetails details, String jointName, Integer jointScore) {

        if (jointName.equalsIgnoreCase("Left elbow")) {
            details.setLeftElbow(jointScore);
        }
        if (jointName.equalsIgnoreCase("Right elbow")) {
            details.setRightElbow(jointScore);
        }
        if (jointName.equalsIgnoreCase("Left knee")) {
            details.setLeftKnee(jointScore);
        }
        if (jointName.equalsIgnoreCase("Right knee")) {
            details.setRightKnee(jointScore);
        }
        if (jointName.equalsIgnoreCase("Left ankle")) {
            details.setLeftAnkle(jointScore);
        }
        if (jointName.equalsIgnoreCase("Right ankle")) {
            details.setRightAnkle(jointScore);
        }

        return details;
    }
}
