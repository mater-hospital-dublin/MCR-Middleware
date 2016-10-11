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

import javax.xml.ws.soap.SOAPFaultException;

import java.util.ArrayList;
import java.util.List;

import ie.mater.common.service.AbstractMaterService;
import ie.mater.patient.results.OCRRResultInfo;
import ie.mater.patient.results.OCRRResultsArray;
import ie.mater.patient.results.ResultServiceSoap;
import org.apache.commons.collections4.CollectionUtils;
import org.rippleosi.patient.labresults.model.LabResultDetails;
import org.rippleosi.patient.labresults.model.LabResultSummary;
import org.rippleosi.patient.labresults.search.LabResultSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterResultSearch extends AbstractMaterService implements LabResultSearch {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaterResultSearch.class);

    @Autowired
    private ResultServiceSoap resultService;

    @Override
    public List<LabResultSummary> findAllLabResults(String patientId) {
        patientId = addTrailingSpacesToPatientId(patientId);

        try {
            List<OCRRResultsArray> labResults = resultService.getResultSummary(patientId).getOCRRResultsArray();

            return CollectionUtils.collect(labResults, new MaterResultToResultSummaryTransformer(), new ArrayList<>());
        }
        catch (SOAPFaultException sfe) {
            LOGGER.error("Could not parse result summary list for patient " + patientId + ".");
        }
        catch (NullPointerException npe) {
            LOGGER.error("Result summary list for " + patientId + " was null.");
        }

        return new ArrayList<>();
    }

    @Override
    public LabResultDetails findLabResult(String patientId, String labResultId) {
        try {
            OCRRResultInfo labResult = resultService.getResultDetails(labResultId);

            return new MaterResultToResultDetailsTransformer().transform(labResult);
        }
        catch (SOAPFaultException sfe) {
            LOGGER.error("Could not parse lab result " + labResultId + " for patient " + patientId + ".");
        }
        catch (NullPointerException npe) {
            LOGGER.error("Lab result " + labResultId + " for patient " + patientId + " was null.");
        }

        return new LabResultDetails();
    }
}
