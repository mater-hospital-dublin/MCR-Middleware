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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ie.mater.common.util.MaterDateFormatter;
import ie.mater.patient.results.ArrayOfTestResultsTestResults;
import ie.mater.patient.results.OCRRResultInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.rippleosi.patient.labresults.model.LabResultDetails;
import org.rippleosi.patient.labresults.model.LabResultDetails.TestResult;

public class MaterResultToResultDetailsTransformer implements Transformer<OCRRResultInfo, LabResultDetails> {

    @Override
    public LabResultDetails transform(OCRRResultInfo materResult) {
        LabResultDetails labResult = new LabResultDetails();

        labResult.setSourceId(materResult.getSourceId());
        labResult.setSource(materResult.getSource());
        labResult.setTestName(materResult.getTestName());

        Date sampleTaken = MaterDateFormatter.toDate(materResult.getDateProcessed());
        Date dateCreated = MaterDateFormatter.toDate(materResult.getDateCreated());

        labResult.setSampleTaken(sampleTaken);
        labResult.setDateCreated(dateCreated);

        labResult.setStatus(materResult.getStatus());
        labResult.setConclusion(materResult.getConclusion());
        labResult.setAuthor(materResult.getAuthor());

        List<TestResult> results = parseTestResults(materResult);
        labResult.setTestResults(results);

        return labResult;
    }

    private ArrayList<TestResult> parseTestResults(OCRRResultInfo materResult) {
        ArrayOfTestResultsTestResults testResults = materResult.getTestResults();

        if (testResults == null) {
            return new ArrayList<>();
        }

        return CollectionUtils.collect(testResults.getTestResults(), new MaterTestResultToTestResultTransformer(), new ArrayList<>());
    }
}
