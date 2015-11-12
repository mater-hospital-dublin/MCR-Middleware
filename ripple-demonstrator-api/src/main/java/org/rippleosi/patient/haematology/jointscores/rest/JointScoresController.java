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
package org.rippleosi.patient.haematology.jointscores.rest;

import java.util.List;

import org.rippleosi.patient.haematology.jointscores.model.JointScoreDetails;
import org.rippleosi.patient.haematology.jointscores.model.JointScoreSummary;
import org.rippleosi.patient.haematology.jointscores.search.JointScoreSearch;
import org.rippleosi.patient.haematology.jointscores.search.JointScoreSearchFactory;
import org.rippleosi.patient.haematology.jointscores.store.JointScoreStore;
import org.rippleosi.patient.haematology.jointscores.store.JointScoreStoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients/{patientId}/jointScores")
public class JointScoresController {

    @Autowired
    private JointScoreSearchFactory jointScoreSearchFactory;

    @Autowired
    private JointScoreStoreFactory jointScoreStoreFactory;

    @RequestMapping(method = RequestMethod.GET)
    public List<JointScoreSummary> findAllJointScores(@PathVariable("patientId") String patientId,
                                                      @RequestParam(required = false) String source) {
        JointScoreSearch search = jointScoreSearchFactory.select(source);

        return search.findAllJointScores(patientId);
    }

    @RequestMapping(value = "/{jointScoreId}", method = RequestMethod.GET)
    public JointScoreDetails findJointScore(@PathVariable("patientId") String patientId,
                                            @PathVariable("jointScoreId") String jointScoreId,
                                            @RequestParam(required = false) String source) {
        JointScoreSearch search = jointScoreSearchFactory.select(source);

        return search.findJointScore(patientId, jointScoreId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createJointScore(@PathVariable("patientId") String patientId,
                                 @RequestParam(required = false) String source,
                                 @RequestBody JointScoreDetails jointScore) {
        JointScoreStore store = jointScoreStoreFactory.select(source);

        store.create(patientId, jointScore);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateJointScore(@PathVariable("patientId") String patientId,
                                 @RequestParam(required = false) String source,
                                 @RequestBody JointScoreDetails jointScore) {
        JointScoreStore store = jointScoreStoreFactory.select(source);

        store.update(patientId, jointScore);
    }
}
