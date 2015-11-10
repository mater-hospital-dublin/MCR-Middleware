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
package org.rippleosi.patient.haematology.eq5l.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.rippleosi.patient.haematology.eq5l.model.EQ5LDetails;
import org.rippleosi.patient.haematology.eq5l.model.EQ5LSummary;
import org.rippleosi.patient.haematology.eq5l.search.EQ5LSearchFactory;
import org.rippleosi.patient.haematology.eq5l.store.EQ5LStoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients/{patientId}/eq5l")
public class EQ5LController {

    @Autowired
    private EQ5LSearchFactory eq5lSearchFactory;

    @Autowired
    private EQ5LStoreFactory eq5lStoreFactory;

    @RequestMapping(method = RequestMethod.GET)
    public List<EQ5LSummary> findAllEQ5Ls(@PathVariable("patientId") String patientId,
                                          @RequestParam(required = false) String source) {
//        EQ5LSearch search = eq5lSearchFactory.select(source);
//
//        return search.findAllEQ5Ls(patientId);
        EQ5LSummary summary1 = new EQ5LSummary();
        summary1.setSource("openehr");
        summary1.setSourceId("9435174");
        summary1.setLifeScore(15);
        summary1.setDateRecorded(new Date());

        EQ5LSummary summary2 = new EQ5LSummary();
        summary2.setSource("openehr");
        summary2.setSourceId("62154");
        summary2.setLifeScore(3);
        summary2.setDateRecorded(new Date());

        List<EQ5LSummary> summaries = new ArrayList<>();
        summaries.add(summary1);
        summaries.add(summary2);

        return summaries;
    }

    @RequestMapping(value = "/{eq5lId}", method = RequestMethod.GET)
    public EQ5LDetails findEQ5L(@PathVariable("patientId") String patientId,
                                @PathVariable("eq5lId") String eq5lId,
                                @RequestParam(required = false) String source) {
//        EQ5LSearch search = eq5lSearchFactory.select(source);
//
//        return search.findEQ5L(patientId, eq5lId);
        EQ5LDetails details = new EQ5LDetails();
        details.setSource("openehr");
        details.setSourceId("9435174");
        details.setDateRecorded(new Date());
        details.setMobility("I have no problems");
        details.setSelfCare("I have no problems");
        details.setUsualActivities("I am unable");
        details.setPain("I have mild pain");
        details.setAnxiety("I have severe anxiety");
        details.setLifeScore(15);
        return details;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createEQ5L(@PathVariable("patientId") String patientId,
                           @RequestParam(required = false) String source,
                           @RequestBody EQ5LDetails eq5l) {
//        EQ5LStore store = eq5lStoreFactory.select(source);
//
//        store.create(patientId, eq5l);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateEQ5L(@PathVariable("patientId") String patientId,
                           @RequestParam(required = false) String source,
                           @RequestBody EQ5LDetails eq5l) {
//        EQ5LStore store = eq5lStoreFactory.select(source);
//
//        store.update(patientId, eq5l);
    }
}
