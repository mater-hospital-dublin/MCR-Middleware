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
package ie.mater.patient.haematology.bleeds.search;

import java.util.List;

import ie.mater.patient.haematology.bleeds.model.BleedDetails;
import ie.mater.patient.haematology.bleeds.model.BleedSummary;
import org.rippleosi.common.service.AbstractOpenEhrService;
import org.springframework.stereotype.Service;

@Service
public class OpenEHRBleedSearch extends AbstractOpenEhrService implements BleedSearch {

    @Override
    public List<BleedSummary> findAllBleeds(String patientId) {
        BleedSummaryQueryStrategy query = new BleedSummaryQueryStrategy(patientId);

        return findData(query);
    }

    @Override
    public BleedDetails findBleed(String patientId, String bleedId) {
        BleedDetailsQueryStrategy query = new BleedDetailsQueryStrategy(patientId, bleedId);

        return findData(query);
    }
}
