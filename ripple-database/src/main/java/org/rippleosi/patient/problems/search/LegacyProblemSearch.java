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
package org.rippleosi.patient.problems.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.hl7.fhir.instance.model.Condition;
import org.rippleosi.common.exception.ConfigurationException;
import org.rippleosi.patient.problems.model.ProblemDetails;
import org.rippleosi.patient.problems.model.ProblemEntity;
import org.rippleosi.patient.problems.model.ProblemHeadline;
import org.rippleosi.patient.problems.model.ProblemSummary;
import org.rippleosi.patient.problems.repo.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LegacyProblemSearch implements ProblemSearch {

    @Value("${legacy.datasource.priority:1100}")
    private int priority;

    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public String getSource() {
        return "ClinTech";
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public List<ProblemHeadline> findProblemHeadlines(String patientId) {
        List<ProblemEntity> problems = problemRepository.findAllByNhsNumber(patientId);
        return CollectionUtils.collect(problems, new ProblemEntityToHeadlineTransformer(), new ArrayList<>());
    }

    @Override
    public List<ProblemSummary> findAllProblems(String patientId) {
        List<ProblemEntity> problems = problemRepository.findAllByNhsNumber(patientId);
        return CollectionUtils.collect(problems, new ProblemEntityToSummaryTransformer(), new ArrayList<>());
    }

    @Override
    public ProblemDetails findProblem(String patientId, String problemId) {
        ProblemEntity problem = problemRepository.findOne(Long.valueOf(problemId));
        return new ProblemEntityToDetailsTransformer().transform(problem);
    }

    @Override
    public List<Condition> findAllFhirConditions(String patientId) {
        throw ConfigurationException.unimplementedTransaction(ProblemSearch.class);
    }

    @Override
    public Condition findFhirCondition(String patientId, String conditionId) {
        throw ConfigurationException.unimplementedTransaction(ProblemSearch.class);
    }
}
