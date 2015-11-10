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
import org.rippleosi.patient.vitals.model.VitalsSummary;
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
    public List<VitalsSummary> findAllAllergies(@PathVariable("patientId") String patientId,
                                                @RequestParam(required = false) String source) {
//        VitalsSearch search = vitalsSearchFactory.select(source);
//
//        return search.findAllAllergies(patientId);
        VitalsSummary summary1 = new VitalsSummary();
        summary1.setSource("openehr");
        summary1.setSourceId("12345");
        summary1.setHeight("165.0");
        summary1.setWeight("58.4");

        VitalsSummary summary2 = new VitalsSummary();
        summary1.setSource("openehr");
        summary1.setSourceId("67891");
        summary1.setHeight("165.0");
        summary1.setWeight("62.8");

        List<VitalsSummary> summaries = new ArrayList<>();
        summaries.add(summary1);
        summaries.add(summary2);

        return summaries;
    }

    @RequestMapping(value = "/{vitalsId}", method = RequestMethod.GET)
    public VitalsDetails findAllergy(@PathVariable("patientId") String patientId,
                                     @PathVariable("vitalsId") String vitalsId,
                                     @RequestParam(required = false) String source) {
//        VitalsSearch search = vitalsSearchFactory.select(source);
//
//        return search.findAllergy(patientId, vitalsId);
        VitalsDetails details = new VitalsDetails();
        details.setSource("openehr");
        details.setSourceId("12345");
        details.setHeight("165.0");
        details.setWeight("58.4");
        details.setHeightRecorded(new Date());
        details.setWeightRecorded(new Date());
        return details;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createAllergy(@PathVariable("patientId") String patientId,
                              @RequestParam(required = false) String source,
                              @RequestBody VitalsDetails vitals) {
        VitalsStore store = vitalsStoreFactory.select(source);

        store.create(patientId, vitals);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateAllergy(@PathVariable("patientId") String patientId,
                              @RequestParam(required = false) String source,
                              @RequestBody VitalsDetails vitals) {
        VitalsStore store = vitalsStoreFactory.select(source);

        store.update(patientId, vitals);
    }
}
