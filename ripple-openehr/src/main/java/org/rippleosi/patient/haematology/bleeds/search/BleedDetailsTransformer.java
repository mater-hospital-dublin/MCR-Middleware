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
import org.rippleosi.patient.haematology.bleeds.model.BleedDetails;

public class BleedDetailsTransformer implements Transformer<Map<String, Object>, BleedDetails> {

    @Override
    public BleedDetails transform(Map<String, Object> input) {

        String uid = MapUtils.getString(input, "uid");
        String dateRecorded = MapUtils.getString(input, "date_recorded");
        String cause = MapUtils.getString(input, "cause");
        String type = MapUtils.getString(input, "type");
        String site = MapUtils.getString(input, "site");
        String pain = MapUtils.getString(input, "pain");
        String severity = MapUtils.getString(input, "severity");

        BleedDetails bleed = new BleedDetails();

        bleed.setSource("openehr");
        bleed.setSourceId(uid);
        bleed.setDateRecorded(DateFormatter.toDate(dateRecorded));
        bleed.setCause(cause);
        bleed.setType(type);
        bleed.setSite(site);
        bleed.setPain(Double.valueOf(pain));
        bleed.setSeverity(severity);

        return bleed;
    }
}
