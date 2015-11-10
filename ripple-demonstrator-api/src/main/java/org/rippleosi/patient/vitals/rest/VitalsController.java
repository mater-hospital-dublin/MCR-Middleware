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
package org.rippleosi.patient.vitals.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.rippleosi.patient.vitals.model.VitalsDetails;
import org.rippleosi.patient.vitals.search.VitalsSearch;
import org.rippleosi.patient.vitals.search.VitalsSearchFactory;
import org.rippleosi.patient.vitals.store.VitalsStore;
import org.rippleosi.patient.vitals.store.VitalsStoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients/{patientId}/vitals")
public class VitalsController {

    @Autowired
    private VitalsSearchFactory vitalsSearchFactory;

    @Autowired
    private VitalsStoreFactory vitalsStoreFactory;

    @RequestMapping(method = RequestMethod.GET)
    public List<VitalsDetails> findAllVitals(@PathVariable("patientId") String patientId,
                                             @RequestParam(required = false) String source) {
//        VitalsSearch search = vitalsSearchFactory.select(source);
//
//        return search.findAllVitals(patientId);
        VitalsDetails details1 = new VitalsDetails();
        details1.setSource("openehr");
        details1.setSourceId("12345");
        details1.setHeight(165.0);
        details1.setWeight(58.4);
        details1.setHeightRecorded(new Date());
        details1.setWeightRecorded(new Date());

        VitalsDetails details2 = new VitalsDetails();
        details2.setSource("openehr");
        details2.setSourceId("67891");
        details2.setHeight(165.0);
        details2.setWeight(62.8);
        details2.setHeightRecorded(new Date());
        details2.setWeightRecorded(new Date());

        List<VitalsDetails> details = new ArrayList<>();
        details.add(details1);
        details.add(details2);

        return details;
    }

    @RequestMapping(value = "/{vitalsId}", method = RequestMethod.GET)
    public VitalsDetails findVital(@PathVariable("patientId") String patientId,
                                   @PathVariable("vitalsId") String vitalsId,
                                   @RequestParam(required = false) String source) {
//        VitalsSearch search = vitalsSearchFactory.select(source);
//
//        return search.findVital(patientId, vitalsId);
        VitalsDetails details = new VitalsDetails();
        details.setSource("openehr");
        details.setSourceId("12345");
        details.setHeight(165.0);
        details.setWeight(58.4);
        details.setHeightRecorded(new Date());
        details.setWeightRecorded(new Date());
        return details;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createVital(@PathVariable("patientId") String patientId,
                            @RequestParam(required = false) String source,
                            @RequestBody VitalsDetails vitals) {
//        VitalsStore store = vitalsStoreFactory.select(source);
//
//        store.create(patientId, vitals);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateVital(@PathVariable("patientId") String patientId,
                            @RequestParam(required = false) String source,
                            @RequestBody VitalsDetails vitals) {
//        VitalsStore store = vitalsStoreFactory.select(source);
//
//        store.update(patientId, vitals);
    }
}
