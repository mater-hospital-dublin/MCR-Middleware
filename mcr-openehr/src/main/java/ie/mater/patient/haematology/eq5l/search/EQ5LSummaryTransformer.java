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

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.Transformer;
import ie.mater.patient.haematology.eq5l.model.EQ5LSummary;
import org.rippleosi.common.util.DateFormatter;

public class EQ5LSummaryTransformer implements Transformer<Map<String, Object>, EQ5LSummary> {

    @Override
    public EQ5LSummary transform(Map<String, Object> input) {

        String uid = MapUtils.getString(input, "uid");
        Integer lifeScore = MapUtils.getInteger(input, "life_score");
        String dateRecorded = MapUtils.getString(input, "date_recorded");

        EQ5LSummary eq5l = new EQ5LSummary();

        eq5l.setSource("openehr");
        eq5l.setSourceId(uid);
        eq5l.setLifeScore(lifeScore);
        eq5l.setDateRecorded(DateFormatter.toDate(dateRecorded));

        return eq5l;
    }
}
