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
package ie.mater.patient.results.search;

import ie.mater.patient.results.TestResults;
import org.apache.commons.collections4.Transformer;
import org.rippleosi.patient.labresults.model.LabResultDetails.TestResult;

public class MaterTestResultToTestResultTransformer implements Transformer<TestResults, TestResult> {

    @Override
    public TestResult transform(TestResults materResult) {
        TestResult labResult = new TestResult();

        labResult.setResult(materResult.getResult());
        labResult.setValue(materResult.getValue());
        labResult.setUnit(materResult.getUnit());
        labResult.setNormalRange(materResult.getNormalRange());
        labResult.setComment(materResult.getComment());

        return labResult;
    }
}
