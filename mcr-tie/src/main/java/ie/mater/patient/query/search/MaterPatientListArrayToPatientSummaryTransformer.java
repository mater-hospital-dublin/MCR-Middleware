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

import java.util.Date;

import ie.mater.patient.query.PatientListArray;
import org.apache.commons.collections4.Transformer;
import org.rippleosi.common.util.DateFormatter;

public class MaterPatientListArrayToPatientSummaryTransformer implements Transformer<PatientListArray, MaterPatientSummary> {

    @Override
    public MaterPatientSummary transform(PatientListArray materPatient) {
        MaterPatientSummary patientSummary = new MaterPatientSummary();

        patientSummary.setId(materPatient.getMRNNumber());
        patientSummary.setMrnNumber(materPatient.getMRNNumber());
        patientSummary.setNhsNumber(materPatient.getMRNNumber());
        patientSummary.setPatientPin(materPatient.getPatientPIN());
        patientSummary.setIhiNumber(materPatient.getIHINumber());

        Date dateOfBirth = DateFormatter.toDate(materPatient.getDateOfBirth());
        patientSummary.setDateOfBirth(dateOfBirth);

        patientSummary.setName(materPatient.getName());
        patientSummary.setGender(materPatient.getGender());
        patientSummary.setAddress(materPatient.getAddress());

        patientSummary.setDepartment(materPatient.getLocation());
        patientSummary.setSpecialty(materPatient.getSpecialty());
        patientSummary.setConsultant(materPatient.getConsultant());

        return patientSummary;
    }
}
