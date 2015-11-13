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

import org.apache.commons.collections4.Transformer;
import org.rippleosi.patient.problems.model.ProblemDetails;
import org.rippleosi.patient.problems.model.ProblemEntity;

public class ProblemEntityToDetailsTransformer implements Transformer<ProblemEntity, ProblemDetails> {

    @Override
    public ProblemDetails transform(ProblemEntity entity) {
        ProblemDetails details = new ProblemDetails();

        details.setSourceId(String.valueOf(entity.getId()));
        details.setSource("ClinTech");
        details.setProblem(entity.getProblem());
        details.setDescription(entity.getDescription());
        details.setTerminology(entity.getTerminology());
        details.setTerminologyCode(entity.getCode());
        details.setDateOfOnset(entity.getDateOfOnset());
        details.setAuthor(entity.getAuthor());
        details.setDateCreated(entity.getDateCreated());

        return details;
    }
}
