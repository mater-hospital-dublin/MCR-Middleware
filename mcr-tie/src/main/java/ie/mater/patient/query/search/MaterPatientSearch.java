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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ie.mater.common.service.AbstractMaterService;
import ie.mater.patient.query.PatientListArray;
import ie.mater.patient.query.PatientMaster;
import ie.mater.patient.query.PatientServiceSoap;
import org.apache.commons.collections4.CollectionUtils;
import org.rippleosi.common.exception.ConfigurationException;
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
    private PatientServiceSoap patientSearch;

    @Autowired
    private MaterPatientToPatientDetailsTransformer materPatientToPatientDetailsTransformer;

    @Override
    public List<PatientSummary> findAllPatients() {
        List<PatientListArray> patients = patientSearch.getPatientSummary().getPatientListArray();

        return CollectionUtils.collect(patients, new MaterPatientListArrayToPatientSummaryTransformer(), new ArrayList<>());
    }

    @Override
    public List<PatientSummary> findAllMatchingPatients(List<String> nhsNumbers, ReportTableQuery tableQuery) {
        throw ConfigurationException.unimplementedTransaction(PatientSearch.class);
    }

    @Override
    public PatientDetails findPatient(String patientId) {
        patientId = addTrailingSpacesToPatientId(patientId);

        PatientMaster materPatient = patientSearch.getPatient(patientId);

        return materPatientToPatientDetailsTransformer.transform(materPatient);
    }

    @Override
    public List<PatientSummary> findPatientsBySearchString(PatientTableQuery tableQuery) {
        throw ConfigurationException.unimplementedTransaction(PatientSearch.class);
    }

    @Override
    public Long countPatientsBySearchString(PatientTableQuery tableQuery) {
        throw ConfigurationException.unimplementedTransaction(PatientSearch.class);
    }

    @Override
    public PatientSummary findPatientSummary(String patientId) {
        PatientMaster materPatient = patientSearch.getPatient(patientId);

        return new MaterPatientToPatientSummaryTransformer().transform(materPatient);
    }

    @Override
    public List<PatientSummary> findAllPatientsByDepartment(SettingTableQuery tableQuery) {
        throw ConfigurationException.unimplementedTransaction(PatientSearch.class);
    }

    @Override
    public Long findPatientCountByDepartment(String department) {
        throw ConfigurationException.unimplementedTransaction(PatientSearch.class);
    }

    @Override
    public List<PatientSummary> findPatientsByQueryObject(PatientQueryParams patientQueryParams) {
        PatientMaster patient = patientSearch.getPatient(patientQueryParams.getNhsNumber());

        return CollectionUtils.collect(Arrays.asList(patient), new MaterPatientToPatientSummaryTransformer(), new ArrayList<>());
    }
}
