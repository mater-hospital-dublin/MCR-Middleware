/*
 * Copyright 2016 Mater Care Record
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
package ie.mater.patient.query.search;

import org.apache.commons.collections4.Transformer;
import org.rippleosi.patient.problems.model.ProblemHeadline;
import org.rippleosi.patient.summary.model.PatientHeadline;

public class ProblemHeadlineToPatientHeadlineTransformer implements Transformer<ProblemHeadline, PatientHeadline> {

    @Override
    public PatientHeadline transform(ProblemHeadline problemHeadline) {
        PatientHeadline patientHeadline = new PatientHeadline();

        patientHeadline.setSource(problemHeadline.getSource());
        patientHeadline.setSourceId(problemHeadline.getSourceId());
        patientHeadline.setText(problemHeadline.getProblem());

        return patientHeadline;
    }
}
