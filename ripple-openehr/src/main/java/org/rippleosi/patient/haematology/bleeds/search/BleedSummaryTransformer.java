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
package org.rippleosi.patient.haematology.bleeds.search;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.Transformer;
import org.rippleosi.common.util.DateFormatter;
import org.rippleosi.patient.haematology.bleeds.model.BleedSummary;

public class BleedSummaryTransformer implements Transformer<Map<String, Object>, BleedSummary> {

    @Override
    public BleedSummary transform(Map<String, Object> input) {

        String uid = MapUtils.getString(input, "uid");
        String cause = MapUtils.getString(input, "cause");
        String dateRecorded = MapUtils.getString(input, "date_recorded");
        String site = MapUtils.getString(input, "site");

        BleedSummary bleed = new BleedSummary();

        bleed.setSource("openehr");
        bleed.setSourceId(uid);
        bleed.setCause(cause);
        bleed.setDateRecorded(DateFormatter.toDate(dateRecorded));
        bleed.setSite(site);

        return bleed;
    }
}
