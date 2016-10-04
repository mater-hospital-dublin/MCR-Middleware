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
package ie.mater.patient.haematology.eq5l.search;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.Transformer;
import ie.mater.patient.haematology.eq5l.model.EQ5LDetails;
import org.rippleosi.common.util.DateFormatter;

public class EQ5LDetailsTransformer implements Transformer<List<Map<String, Object>>, EQ5LDetails> {

    @Override
    public EQ5LDetails transform(List<Map<String, Object>> input) {

        Map<String, Object> firstEntry = input.get(0);

        String uid = MapUtils.getString(firstEntry, "uid");
        String mobility = MapUtils.getString(firstEntry, "mobility");
        String selfCare = MapUtils.getString(firstEntry, "self_care");
        String usualActivities = MapUtils.getString(firstEntry, "usual_activities");
        String pain = MapUtils.getString(firstEntry, "pain");
        String anxiety = MapUtils.getString(firstEntry, "anxiety");
        Integer lifeScore = MapUtils.getInteger(firstEntry, "life_score");
        String dateRecorded = MapUtils.getString(firstEntry, "date_recorded");

        EQ5LDetails details = new EQ5LDetails();

        details.setSource("openehr");
        details.setSourceId(uid);
        details.setMobility(mobility);
        details.setSelfCare(selfCare);
        details.setUsualActivities(usualActivities);
        details.setPain(pain);
        details.setAnxiety(anxiety);
        details.setLifeScore(lifeScore != null ? lifeScore : 0);
        details.setDateRecorded(DateFormatter.toDate(dateRecorded));

        return details;
    }
}
