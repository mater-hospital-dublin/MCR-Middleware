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

import javax.xml.ws.soap.SOAPFaultException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ie.mater.common.service.AbstractMaterService;
import ie.mater.patient.query.PatientListArray;
import ie.mater.patient.query.PatientMaster;
import ie.mater.patient.query.PatientServiceSoap;
import ie.mater.search.patient.ArrayOfPatientListArrayPatientListArray;
import ie.mater.search.patient.PatientSearchServiceSoap;
import org.apache.commons.collections4.CollectionUtils;
import org.rippleosi.common.exception.ConfigurationException;
import org.rippleosi.common.util.DateFormatter;
import org.rippleosi.patient.summary.model.PatientDetails;
import org.rippleosi.patient.summary.model.PatientQueryParams;
import org.rippleosi.patient.summary.model.PatientSummary;
import org.rippleosi.patient.summary.search.PatientSearch;
import org.rippleosi.search.patient.stats.model.PatientTableQuery;
import org.rippleosi.search.reports.table.model.ReportTableQuery;
import org.rippleosi.search.setting.table.model.SettingTableQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterPatientSearch extends AbstractMaterService implements PatientSearch {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaterPatientSearch.class);

    @Autowired
    private PatientServiceSoap patientService;

    @Autowired
    private PatientSearchServiceSoap patientSearchService;

    @Autowired
    private MaterPatientToPatientDetailsTransformer materPatientToPatientDetailsTransformer;

    @Override
    public List<PatientSummary> findAllPatients() {
        try {
            List<PatientListArray> patients = patientService.getPatientSummary().getPatientListArray();

            return CollectionUtils.collect(patients, new MaterPatientListArrayToPatientSummaryTransformer(), new ArrayList<>());
        }
        catch (SOAPFaultException sfe) {
            LOGGER.error("Could not parse patient list.");
        }
        catch (NullPointerException npe) {
            LOGGER.error("Patient list was null.");
        }

        return new ArrayList<>();
    }

    @Override
    public List<PatientSummary> findAllMatchingPatients(List<String> nhsNumbers, ReportTableQuery tableQuery) {
        throw ConfigurationException.unimplementedTransaction(PatientSearch.class);
    }

    @Override
    public PatientDetails findPatient(String patientId) {
        try {
            patientId = addTrailingSpacesToPatientId(patientId);

            PatientMaster materPatient = patientService.getPatient(patientId);

            return materPatientToPatientDetailsTransformer.transform(materPatient);
        }
        catch (SOAPFaultException sfe) {
            LOGGER.error("Could not parse details for patient " + patientId + ".");
        }
        catch (NullPointerException npe) {
            LOGGER.error("Patient " + patientId + " was null.");
        }

        return new PatientDetails();
    }

    @Override
    public List<PatientSummary> findPatientsBySearchString(PatientTableQuery tableQuery) {
        try {
            ie.mater.search.patient.ArrayOfPatientListArrayPatientListArray patientList =
                patientSearchService.byName(tableQuery.getSearchString());

            List<ie.mater.search.patient.PatientListArray> patients = patientList.getPatientListArray();

            return CollectionUtils.collect(patients, new MaterPatientSearchListArrayToPatientSummaryTransformer(), new ArrayList<>());
        }
        catch (SOAPFaultException sfe) {
            LOGGER.error("Could not parse patient summary list for search term '" + tableQuery.getSearchString() + "'.");
        }
        catch (NullPointerException npe) {
            LOGGER.warn("Patient summary list relating to the search term '" + tableQuery.getSearchString() + "' was null.");
        }

        return new ArrayList<>();
    }

    @Override
    public Long countPatientsBySearchString(PatientTableQuery tableQuery) {
        List<PatientSummary> patients = findPatientsBySearchString(tableQuery);

        return (long) patients.size();
    }

    @Override
    public PatientSummary findPatientSummary(String patientId) {
        try {
            patientId = addTrailingSpacesToPatientId(patientId);

            PatientMaster materPatient = patientService.getPatient(patientId);

            return new MaterPatientToPatientSummaryTransformer().transform(materPatient);
        }
        catch (SOAPFaultException sfe) {
            LOGGER.error("Could not parse summary for patient " + patientId + ".");
        }
        catch (NullPointerException npe) {
            LOGGER.error("Patient " + patientId + " was null.");
        }

        return new PatientSummary();
    }

    @Override
    public List<PatientSummary> findAllPatientsByDepartment(SettingTableQuery tableQuery) {
        try {
            ArrayOfPatientListArrayPatientListArray patientListArray = patientSearchService.byWard(tableQuery.getSearchString());

            List<ie.mater.search.patient.PatientListArray> patients = patientListArray.getPatientListArray();

            return CollectionUtils.collect(patients, new MaterPatientSearchListArrayToPatientSummaryTransformer(), new ArrayList<>());
        }
        catch (SOAPFaultException sfe) {
            LOGGER.error("Could not parse patient summary list for ward '" + tableQuery.getSearchString() + "'.");
        }
        catch (NullPointerException npe) {
            LOGGER.warn("Patient summary list relating to ward '" + tableQuery.getSearchString() + "' was null.");
        }

        return new ArrayList<>();
    }

    @Override
    public Long findPatientCountByDepartment(String department) {
        SettingTableQuery tableQuery = new SettingTableQuery();
        tableQuery.setSearchString(department);
        tableQuery.setOrderType("ASC");
        tableQuery.setPageNumber("1");

        return (long) findAllPatientsByDepartment(tableQuery).size();
    }

    @Override
    public List<PatientSummary> findPatientsByQueryObject(PatientQueryParams patientQueryParams) {
        List<PatientSummary> patients = new ArrayList<>();
        String patientId = patientQueryParams.getNhsNumber();

        if (patientId != null) {
            PatientSummary summary = findPatientSummary(patientId);

            patients.add(summary);
        }
        else {
            List<MaterPatientSummary> summaries = convertMaterPatientListArray(patientQueryParams);

            patients.addAll(summaries);
        }

        return patients;
    }

    private List<MaterPatientSummary> convertMaterPatientListArray(PatientQueryParams params) {
        String surname = params.getSurname();
        String forename = params.getForename();
        Date dateOfBirth = params.getDateOfBirth();
        String gender = params.getGender();

        try {
            ie.mater.patient.query.ArrayOfPatientListArrayPatientListArray search =
                patientService.getAdvancedSearch(surname, forename, DateFormatter.toSimpleDateString(dateOfBirth), gender);

            return CollectionUtils.collect(search.getPatientListArray(),
                                           new MaterPatientListArrayToPatientSummaryTransformer(), new ArrayList<>());
        }
        catch (SOAPFaultException sfe) {
            LOGGER.error("Could not parse summary list from advanced search.");
        }
        catch (NullPointerException npe) {
            LOGGER.error("No patients could be found.");
        }

        return new ArrayList<>();
    }
}
