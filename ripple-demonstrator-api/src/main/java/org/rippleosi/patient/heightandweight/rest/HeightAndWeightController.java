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
package org.rippleosi.patient.heightandweight.rest;

import java.util.List;

import org.rippleosi.patient.heightandweight.model.HeightAndWeightDetails;
import org.rippleosi.patient.heightandweight.search.HeightAndWeightSearch;
import org.rippleosi.patient.heightandweight.search.HeightAndWeightSearchFactory;
import org.rippleosi.patient.heightandweight.store.HeightAndWeightStore;
import org.rippleosi.patient.heightandweight.store.HeightAndWeightStoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients/{patientId}/heightAndWeight")
public class HeightAndWeightController {

    @Autowired
    private HeightAndWeightSearchFactory heightAndWeightSearchFactory;

    @Autowired
    private HeightAndWeightStoreFactory heightAndWeightStoreFactory;

    @RequestMapping(method = RequestMethod.GET)
    public List<HeightAndWeightDetails> findAllHeightsAndWeights(@PathVariable("patientId") String patientId,
                                                                 @RequestParam(required = false) String source) {
        HeightAndWeightSearch search = heightAndWeightSearchFactory.select(source);

        return search.findAllHeightsAndWeights(patientId);
    }

    @RequestMapping(value = "/{heightAndWeightId}", method = RequestMethod.GET)
    public HeightAndWeightDetails findHeightAndWeight(@PathVariable("patientId") String patientId,
                                                      @PathVariable("heightAndWeightId") String heightAndWeightId,
                                                      @RequestParam(required = false) String source) {
        HeightAndWeightSearch search = heightAndWeightSearchFactory.select(source);

        return search.findHeightAndWeight(patientId, heightAndWeightId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createHeightAndWeight(@PathVariable("patientId") String patientId,
                                      @RequestParam(required = false) String source,
                                      @RequestBody HeightAndWeightDetails heightAndWeight) {
        HeightAndWeightStore store = heightAndWeightStoreFactory.select(source);

        store.create(patientId, heightAndWeight);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateHeightAndWeight(@PathVariable("patientId") String patientId,
                                      @RequestParam(required = false) String source,
                                      @RequestBody HeightAndWeightDetails heightAndWeight) {
        HeightAndWeightStore store = heightAndWeightStoreFactory.select(source);

        store.update(patientId, heightAndWeight);
    }
}
