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
package ie.mater.patient.heightandweight.search;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.Transformer;
import ie.mater.patient.heightandweight.model.HeightAndWeightDetails;
import org.rippleosi.common.util.DateFormatter;

public class HeightAndWeightDetailsTransformer implements Transformer<Map<String, Object>, HeightAndWeightDetails> {

    @Override
    public HeightAndWeightDetails transform(Map<String, Object> input) {

        String uid = MapUtils.getString(input, "uid");
        Double height = MapUtils.getDouble(input, "height");
        Double weight = MapUtils.getDouble(input, "weight");
        String heightRecorded = MapUtils.getString(input, "height_recorded");
        String weightRecorded = MapUtils.getString(input, "weight_recorded");

        HeightAndWeightDetails heightAndWeight = new HeightAndWeightDetails();

        heightAndWeight.setSource("openehr");
        heightAndWeight.setSourceId(uid);

        heightAndWeight.setHeight(height);
        heightAndWeight.setWeight(weight);

        heightAndWeight.setHeightRecorded(DateFormatter.toDate(heightRecorded));
        heightAndWeight.setWeightRecorded(DateFormatter.toDate(weightRecorded));

        return heightAndWeight;
    }
}
