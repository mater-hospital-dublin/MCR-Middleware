/*
 * Copyright 2015 Ripple OSI
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.rippleosi.patient.problems.rest;

import java.util.List;

import org.rippleosi.patient.problems.model.ProblemDetails;
import org.rippleosi.patient.problems.model.ProblemHeadline;
import org.rippleosi.patient.problems.model.ProblemSummary;
import org.rippleosi.patient.problems.search.ProblemSearch;
import org.rippleosi.patient.problems.search.ProblemSearchFactory;
import org.rippleosi.patient.problems.store.ProblemStore;
import org.rippleosi.patient.problems.store.ProblemStoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@RequestMapping("/patients/{patientId}/diagnoses")
public class ProblemsController {

    @Autowired
    private ProblemSearchFactory problemSearchFactory;

    @Autowired
    private ProblemStoreFactory problemStoreFactory;

    @RequestMapping(method = RequestMethod.GET)
    public List<ProblemSummary> findAllProblems(@PathVariable("patientId") String patientId,
                                                @RequestParam(required = false) String source) {
        ProblemSearch problemSearch = problemSearchFactory.select(source);
        List<ProblemSummary> problems = problemSearch.findAllProblems(patientId);

        ProblemSearch vistaSearch = problemSearchFactory.select("vista");
        problems.addAll(vistaSearch.findAllProblems("17"));

        ProblemSearch legacySearch = problemSearchFactory.select("ClinTech");
        problems.addAll(legacySearch.findAllProblems(patientId));

        return problems;
    }

    @RequestMapping(value = "/headlines", method = RequestMethod.GET)
    public List<ProblemHeadline> findProblemHeadlines(@PathVariable("patientId") String patientId,
                                                      @RequestParam(required = false) String source) {
        ProblemSearch problemSearch = problemSearchFactory.select(source);
        List<ProblemHeadline> problemHeadlines = problemSearch.findProblemHeadlines(patientId);

        ProblemSearch vistaSearch = problemSearchFactory.select("vista");
        problemHeadlines.addAll(vistaSearch.findProblemHeadlines("17"));

        ProblemSearch legacySearch = problemSearchFactory.select("ClinTech");
        problemHeadlines.addAll(legacySearch.findProblemHeadlines(patientId));

        return problemHeadlines;
    }

    @RequestMapping(value = "/{problemId}", method = RequestMethod.GET)
    public ProblemDetails findProblem(@PathVariable("patientId") String patientId,
                                      @PathVariable("problemId") String problemId,
                                      @RequestParam(required = false) String source) {
        ProblemSearch problemSearch = problemSearchFactory.select(source);

        if (source != null && source.equalsIgnoreCase("vista")) {
            patientId = "17";
        }

        return problemSearch.findProblem(patientId, problemId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createProblem(@PathVariable("patientId") String patientId,
                              @RequestParam(required = false) String source,
                              @RequestBody ProblemDetails problem) {
        ProblemStore problemStore = problemStoreFactory.select(source);

        problemStore.create(patientId, problem);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateProblem(@PathVariable("patientId") String patientId,
                              @RequestParam(required = false) String source,
                              @RequestBody ProblemDetails problem) {
        ProblemStore problemStore = problemStoreFactory.select(source);

        problemStore.update(patientId, problem);
    }
}
