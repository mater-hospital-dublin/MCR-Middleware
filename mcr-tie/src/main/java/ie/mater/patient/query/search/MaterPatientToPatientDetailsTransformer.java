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
import java.util.Date;
import java.util.List;

import ie.mater.patient.query.PatientMaster;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.rippleosi.common.types.RepoSourceType;
import org.rippleosi.common.types.lookup.RepoSourceLookupFactory;
import org.rippleosi.common.util.DateFormatter;
import org.rippleosi.patient.allergies.model.AllergyHeadline;
import org.rippleosi.patient.allergies.search.AllergySearch;
import org.rippleosi.patient.allergies.search.AllergySearchFactory;
import org.rippleosi.patient.contacts.model.ContactHeadline;
import org.rippleosi.patient.contacts.search.ContactSearch;
import org.rippleosi.patient.contacts.search.ContactSearchFactory;
import org.rippleosi.patient.laborders.model.LabOrderSummary;
import org.rippleosi.patient.laborders.search.LabOrderSearch;
import org.rippleosi.patient.laborders.search.LabOrderSearchFactory;
import org.rippleosi.patient.labresults.model.LabResultSummary;
import org.rippleosi.patient.labresults.search.LabResultSearch;
import org.rippleosi.patient.labresults.search.LabResultSearchFactory;
import org.rippleosi.patient.medication.model.MedicationHeadline;
import org.rippleosi.patient.medication.search.MedicationSearch;
import org.rippleosi.patient.medication.search.MedicationSearchFactory;
import org.rippleosi.patient.problems.model.ProblemHeadline;
import org.rippleosi.patient.problems.search.ProblemSearch;
import org.rippleosi.patient.problems.search.ProblemSearchFactory;
import org.rippleosi.patient.summary.model.PatientHeadline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MaterPatientToPatientDetailsTransformer implements Transformer<PatientMaster, MaterPatientDetails> {

    @Autowired
    private RepoSourceLookupFactory repoSourceLookup;

    @Autowired
    private ProblemSearchFactory problemSearchFactory;

    @Autowired
    private AllergySearchFactory allergySearchFactory;

    @Autowired
    private MedicationSearchFactory medicationSearchFactory;

    @Autowired
    private ContactSearchFactory contactSearchFactory;

    @Autowired
    private LabOrderSearchFactory labOrderSearchFactory;

    @Autowired
    private LabResultSearchFactory labResultSearchFactory;

    @Override
    public MaterPatientDetails transform(PatientMaster materPatient) {
        MaterPatientDetails patientDetails = new MaterPatientDetails();

        patientDetails.setId(materPatient.getId());
        patientDetails.setPatientPin(materPatient.getPatientPIN());
        patientDetails.setNhsNumber(materPatient.getPatientPIN());
        patientDetails.setPasNumber(materPatient.getPasNumber());
        patientDetails.setIhiNumber(materPatient.getIHINumber());

        Date dateOfBirth = DateFormatter.toDate(materPatient.getDateOfBirth());
        patientDetails.setDateOfBirth(dateOfBirth);

        patientDetails.setName(materPatient.getName());
        patientDetails.setGender(materPatient.getGender());
        patientDetails.setAddress(materPatient.getAddress());
        patientDetails.setTelephone(materPatient.getTelephone());

        String gpDetails = StringUtils.substring(materPatient.getGpDetails(), 0, 16);
        patientDetails.setGpDetails(gpDetails + " ...");

        String ihiNumber = materPatient.getIHINumber();

        patientDetails.setProblems(findProblems(ihiNumber));
        patientDetails.setAllergies(findAllergies(ihiNumber));
        patientDetails.setMedications(findMedications(ihiNumber));
        patientDetails.setContacts(findContacts(ihiNumber));
        patientDetails.setOrders(findOrders(ihiNumber));
        patientDetails.setResults(findResults(ihiNumber));

        return patientDetails;
    }

    private List<PatientHeadline> findProblems(String ihiNumber) {
        RepoSourceType sourceType = repoSourceLookup.lookup(null);

        ProblemSearch problemSearch = problemSearchFactory.select(sourceType);

        List<ProblemHeadline> problems = problemSearch.findProblemHeadlines("9999999000");

        return CollectionUtils.collect(problems, new ProblemHeadlineToPatientHeadlineTransformer(), new ArrayList<>());
    }

    private List<PatientHeadline> findAllergies(String ihiNumber) {
        RepoSourceType sourceType = repoSourceLookup.lookup(null);

        AllergySearch allergySearch = allergySearchFactory.select(sourceType);

        List<AllergyHeadline> allergies = allergySearch.findAllergyHeadlines("9999999000");

        return CollectionUtils.collect(allergies, new AllergyHeadlineToPatientHeadlineTransformer(), new ArrayList<>());
    }

    private List<PatientHeadline> findMedications(String ihiNumber) {
        RepoSourceType sourceType = repoSourceLookup.lookup(null);

        MedicationSearch medicationSearch = medicationSearchFactory.select(sourceType);

        List<MedicationHeadline> medications = medicationSearch.findMedicationHeadlines("9999999000");

        return CollectionUtils.collect(medications, new MedicationHeadlineToPatientHeadlineTransformer(), new ArrayList<>());
    }

    private List<PatientHeadline> findContacts(String ihiNumber) {
        RepoSourceType sourceType = repoSourceLookup.lookup(null);

        ContactSearch contactSearch = contactSearchFactory.select(sourceType);

        List<ContactHeadline> contacts = contactSearch.findContactHeadlines("9999999000");

        return CollectionUtils.collect(contacts, new ContactHeadlineToPatientHeadlineTransformer(), new ArrayList<>());
    }

    private List<PatientHeadline> findOrders(String ihiNumber) {
        RepoSourceType sourceType = repoSourceLookup.lookup(null);

        LabOrderSearch labOrderSearch = labOrderSearchFactory.select(sourceType);

        List<LabOrderSummary> labOrders = labOrderSearch.findAllLabOrders(ihiNumber);

        return CollectionUtils.collect(labOrders, new LabOrderToPatientHeadlineTransformer(), new ArrayList<>());
    }

    private List<PatientHeadline> findResults(String ihiNumber) {
        RepoSourceType sourceType = repoSourceLookup.lookup(null);

        LabResultSearch labResultSearch = labResultSearchFactory.select(sourceType);

        List<LabResultSummary> labResults = labResultSearch.findAllLabResults(ihiNumber);

        return CollectionUtils.collect(labResults, new LabResultToPatientHeadlineTransformer(), new ArrayList<>());
    }
}
