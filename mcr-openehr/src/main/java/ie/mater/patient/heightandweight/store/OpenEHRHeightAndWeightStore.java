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
package ie.mater.patient.heightandweight.store;

import java.util.HashMap;
import java.util.Map;

import ie.mater.patient.heightandweight.model.HeightAndWeightDetails;
import org.apache.camel.Consume;
import org.rippleosi.common.service.AbstractOpenEhrService;
import org.rippleosi.common.service.CreateStrategy;
import org.rippleosi.common.service.DefaultStoreStrategy;
import org.rippleosi.common.service.UpdateStrategy;
import org.rippleosi.common.util.DateFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenEHRHeightAndWeightStore extends AbstractOpenEhrService implements HeightAndWeightStore {

    @Value("${c4hOpenEHR.heightAndWeightTemplate}")
    private String heightAndWeightTemplate;

    private static final String HEIGHT_AND_WEIGHT_PREFIX = "patient_visit_-_height_weight:0/examination_findings:0/";

    @Override
    @Consume(uri = "activemq:Consumer.C4HOpenEHR.VirtualTopic.Ripple.HeightAndWeight.Create")
    public void create(String patientId, HeightAndWeightDetails heightAndWeight) {
        Map<String, Object> content = createFlatJsonContent(heightAndWeight);

        CreateStrategy createStrategy = new DefaultStoreStrategy(patientId, heightAndWeightTemplate, content);

        createData(createStrategy);
    }

    @Override
    @Consume(uri = "activemq:Consumer.C4HOpenEHR.VirtualTopic.Ripple.HeightAndWeight.Update")
    public void update(String patientId, HeightAndWeightDetails heightAndWeight) {
        Map<String, Object> content = createFlatJsonContent(heightAndWeight);

        UpdateStrategy updateStrategy = new DefaultStoreStrategy(heightAndWeight.getSourceId(), patientId,
                                                                 heightAndWeightTemplate, content);

        updateData(updateStrategy);
    }

    private Map<String, Object> createFlatJsonContent(HeightAndWeightDetails heightAndWeight) {

        Map<String, Object> content = new HashMap<>();

        content.put("ctx/language", "en");
        content.put("ctx/territory", "GB");
        content.put("ctx/composer_name", "Dr Tony Shannon");

        String heightRecorded = DateFormatter.toString(heightAndWeight.getHeightRecorded());
        String weightRecorded = DateFormatter.toString(heightAndWeight.getWeightRecorded());

        content.put(HEIGHT_AND_WEIGHT_PREFIX + "height_length:0/height|magnitude", heightAndWeight.getHeight());
        content.put(HEIGHT_AND_WEIGHT_PREFIX + "height_length:0/height|unit", "cm");
        content.put(HEIGHT_AND_WEIGHT_PREFIX + "height_length:0/time", heightRecorded);

        content.put(HEIGHT_AND_WEIGHT_PREFIX + "body_weight:0/weight|magnitude", heightAndWeight.getWeight());
        content.put(HEIGHT_AND_WEIGHT_PREFIX + "body_weight:0/weight|unit", "kg");
        content.put(HEIGHT_AND_WEIGHT_PREFIX + "body_weight:0/time", weightRecorded);

        return content;
    }
}
