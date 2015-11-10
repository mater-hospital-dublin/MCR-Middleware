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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.rippleosi.patient.haematology.jointscores.model.JointScoreDetails;
import org.rippleosi.patient.haematology.jointscores.model.JointScoreSummary;
import org.rippleosi.patient.haematology.jointscores.search.JointScoresSearchFactory;
import org.rippleosi.patient.haematology.jointscores.store.JointScoresStoreFactory;
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
    private JointScoresSearchFactory jointScoresSearchFactory;

    @Autowired
    private JointScoresStoreFactory jointScoresStoreFactory;

    @RequestMapping(method = RequestMethod.GET)
    public List<JointScoreSummary> findAllJointScores(@PathVariable("patientId") String patientId,
                                                      @RequestParam(required = false) String source) {
//        JointScoresSearch search = jointScoresSearchFactory.select(source);
//
//        return search.findAllJointScores(patientId);
        JointScoreSummary summary1 = new JointScoreSummary();
        summary1.setSource("openehr");
        summary1.setSourceId("25841351");
        summary1.setTotalScore(15);
        summary1.setDateRecorded(new Date());

        JointScoreSummary summary2 = new JointScoreSummary();
        summary2.setSource("openehr");
        summary2.setSourceId("784151");
        summary2.setTotalScore(10);
        summary2.setDateRecorded(new Date());

        List<JointScoreSummary> summaries = new ArrayList<>();
        summaries.add(summary1);
        summaries.add(summary2);

        return summaries;
    }

    @RequestMapping(value = "/{jointScoreId}", method = RequestMethod.GET)
    public JointScoreDetails findJointScore(@PathVariable("patientId") String patientId,
                                            @PathVariable("jointScoreId") String jointScoreId,
                                            @RequestParam(required = false) String source) {
//        JointScoresSearch search = jointScoresSearchFactory.select(source);
//
//        return search.findJointScore(patientId, jointScoreId);
        JointScoreDetails details = new JointScoreDetails();
        details.setSource("openehr");
        details.setSourceId("25841351");
        details.setDateRecorded(new Date());
        details.setLeftKnee(3);
        details.setRightKnee(2);
        details.setLeftElbow(0);
        details.setRightElbow(0);
        details.setLeftAnkle(7);
        details.setRightAnkle(3);
        details.setTotalScore(15);
        return details;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createJointScore(@PathVariable("patientId") String patientId,
                                 @RequestParam(required = false) String source,
                                 @RequestBody JointScoreDetails jointScore) {
//        BleedsStore store = bleedsStoreFactory.select(source);
//
//        store.create(patientId, bleed);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateJointScore(@PathVariable("patientId") String patientId,
                                 @RequestParam(required = false) String source,
                                 @RequestBody JointScoreDetails jointScore) {
//        BleedsStore store = bleedsStoreFactory.select(source);
//
//        store.update(patientId, bleed);
    }
}
