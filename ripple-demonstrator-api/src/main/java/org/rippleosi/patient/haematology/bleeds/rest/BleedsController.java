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
package org.rippleosi.patient.haematology.bleeds.rest;

import java.util.List;

import org.rippleosi.patient.haematology.bleeds.model.BleedDetails;
import org.rippleosi.patient.haematology.bleeds.model.BleedSummary;
import org.rippleosi.patient.haematology.bleeds.search.BleedSearch;
import org.rippleosi.patient.haematology.bleeds.search.BleedSearchFactory;
import org.rippleosi.patient.haematology.bleeds.store.BleedStore;
import org.rippleosi.patient.haematology.bleeds.store.BleedStoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients/{patientId}/bleeds")
public class BleedsController {

    @Autowired
    private BleedSearchFactory bleedSearchFactory;

    @Autowired
    private BleedStoreFactory bleedStoreFactory;

    @RequestMapping(method = RequestMethod.GET)
    public List<BleedSummary> findAllBleeds(@PathVariable("patientId") String patientId,
                                            @RequestParam(required = false) String source) {
        BleedSearch search = bleedSearchFactory.select(source);

        return search.findAllBleeds(patientId);
    }

    @RequestMapping(value = "/{bleedId}", method = RequestMethod.GET)
    public BleedDetails findBleed(@PathVariable("patientId") String patientId,
                                  @PathVariable("bleedId") String bleedId,
                                  @RequestParam(required = false) String source) {
        BleedSearch search = bleedSearchFactory.select(source);

        return search.findBleed(patientId, bleedId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createBleed(@PathVariable("patientId") String patientId,
                            @RequestParam(required = false) String source,
                            @RequestBody BleedDetails bleed) {
        BleedStore store = bleedStoreFactory.select(source);

        store.create(patientId, bleed);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateBleed(@PathVariable("patientId") String patientId,
                            @RequestParam(required = false) String source,
                            @RequestBody BleedDetails bleed) {
        BleedStore store = bleedStoreFactory.select(source);

        store.update(patientId, bleed);
    }
}
