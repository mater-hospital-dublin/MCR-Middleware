/*
 * Copyright 2015 Ripple OSI
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
package org.rippleosi.patient.documents.referral.search;

import java.util.List;
import java.util.Map;

import org.rippleosi.common.exception.DataNotFoundException;
import org.rippleosi.common.service.AbstractQueryStrategy;
import org.rippleosi.patient.documents.referral.model.ReferralDocumentDetails;

/**
 */
public class ReferralDocumentDetailsQueryStrategy extends AbstractQueryStrategy<ReferralDocumentDetails> {

    private final String documentId;

    ReferralDocumentDetailsQueryStrategy(String patientId, String documentId) {
        super(patientId);
        this.documentId = documentId;
    }

    @Override
    public String getQuery(String namespace, String patientId) {
        return "select a/uid/value as uid, a/context/start_time/value as referralDateTime, a/composer/name as authorName, a/context/health_care_facility/name as facility, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1]/items[openEHR-EHR-INSTRUCTION.request.v0]/activities[at0001]/description[at0009]/items[at0121, 'Referral type']/value/value as referralType, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1|Referral details|]/items[openEHR-EHR-INSTRUCTION.request.v0|Service request|]/activities[at0001|Request|]/description[at0009]/items[at0062|Reason for referral|]/value/value as reasonForReferral, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1|Referral details|]/items[openEHR-EHR-INSTRUCTION.request.v0|Service request|]/activities[at0001|Request|]/description[at0009]/items[at0135|Comments|]/value/value as referralComment, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1|Referral details|]/items[openEHR-EHR-INSTRUCTION.request.v0|Service request|]/activities[at0001|Request|]/description[at0009]/items[at0068|Priority|]/value/defining_code/code_string as priorityOfReferral, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1|Referral details|]/items[openEHR-EHR-INSTRUCTION.request.v0|Service request|]/protocol[at0008]/items[at0010|Referral Control Number|]/value/value as referralReferenceNumber, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1]/items[openEHR-EHR-INSTRUCTION.request.v0]/protocol[at0008]/items[openEHR-EHR-CLUSTER.organisation.v1, 'Referring Provider']/items[at0001]/value/value as referredFrom, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1]/items[openEHR-EHR-INSTRUCTION.request.v0]/protocol[at0008]/items[openEHR-EHR-CLUSTER.organisation.v1, 'Referred to Provider']/items[at0001]/value/value as referredTo, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1]/items[openEHR-EHR-INSTRUCTION.request.v0]/protocol[at0008]/items[openEHR-EHR-CLUSTER.distribution.v1]/items[at0011]/items[openEHR-EHR-CLUSTER.organisation.v1, 'GP']/items[at0001]/value/value as providerContactOrgName, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1]/items[openEHR-EHR-INSTRUCTION.request.v0]/protocol[at0008]/items[openEHR-EHR-CLUSTER.distribution.v1]/items[at0011]/items[openEHR-EHR-CLUSTER.organisation.v1, 'GP']/items[at0011]/value/value as providerContactId, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1]/items[openEHR-EHR-INSTRUCTION.request.v0]/protocol[at0008]/items[openEHR-EHR-CLUSTER.distribution.v1]/items[at0011]/items[openEHR-EHR-CLUSTER.organisation.v1, 'GP']/items[openEHR-EHR-CLUSTER.telecom_uk.v1, 'WPN']/items[at0002, 'Work number']/value/value as providerContactWorkNumber, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1]/items[openEHR-EHR-INSTRUCTION.request.v0]/protocol[at0008]/items[openEHR-EHR-CLUSTER.distribution.v1]/items[at0011]/items[openEHR-EHR-CLUSTER.organisation.v1, 'GP']/items[openEHR-EHR-CLUSTER.telecom_uk.v1, 'EMR']/items[at0002, 'Emergency number']/value/value as providerContactEmgNumber, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1]/items[openEHR-EHR-INSTRUCTION.request.v0]/protocol[at0008]/items[openEHR-EHR-CLUSTER.distribution.v1]/items[at0011]/items[openEHR-EHR-CLUSTER.organisation.v1, 'GP']/items[openEHR-EHR-CLUSTER.telecom_uk.v1, 'NET']/items[at0002, 'Internet']/value/value as providerContactEmail, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1|Referral details|]/items[openEHR-EHR-ACTION.referral.v1|Referral status|]/ism_transition/current_state/value as referralStatusValue, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1|Referral details|]/items[openEHR-EHR-ACTION.referral.v1|Referral status|]/ism_transition/current_state/defining_code/code_string as referralStatusCode, " +
                "a/content[openEHR-EHR-SECTION.referral_details_rcp.v1]/items[openEHR-EHR-INSTRUCTION.request.v0]/narrative/value as clinicalNarrative, " +
                "a/content[openEHR-EHR-SECTION.history_rcp.v1, 'History of present illness']/items[openEHR-EHR-OBSERVATION.story.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004, 'History of present illness']/value/value as presentIllness, " +
                "a/content[openEHR-EHR-SECTION.clinical_summary_rcp.v1, 'Comments']/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1]/data[at0001]/items[at0002, 'Comments']/value/value as clinicalSynopsis, " +
                "a/content[openEHR-EHR-SECTION.history_rcp.v1, 'History of present illness']/items[openEHR-EHR-ADMIN_ENTRY.hospital_attendances_summary.v0]/data[at0001]/items[at0002]/value/value as previousHospitalAttendance, " +
                "a/content[openEHR-EHR-SECTION.problems_issues_rcp.v1, 'History of past illness']/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]/data[at0001]/items[at0002, 'History of past illness']/value/value as pastIllness, " +
                "a/content[openEHR-EHR-SECTION.problems_issues_rcp.v1, 'History of past illness']/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]/data[at0001]/items[at0003]/value/value as pastIllnessDateTime, " +
                "a/content[openEHR-EHR-SECTION.procedures_rcp.v1, 'History of surgical procedures']/items[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0002, 'History of surgical procedure']/value/value as surgicalProcedure, " +
                "a/content[openEHR-EHR-SECTION.procedures_rcp.v1, 'History of surgical procedures']/items[openEHR-EHR-ACTION.procedure.v1]/time/value as surgicalProcedureTime,	" +
                "a/content[openEHR-EHR-SECTION.medication_medical_devices_rcp.v1]/items[openEHR-EHR-INSTRUCTION.medication_order.v0]/activities[at0001]/description[at0002]/items[at0070]/value/value as medication, " +
                "a/content[openEHR-EHR-SECTION.medication_medical_devices_rcp.v1]/items[openEHR-EHR-INSTRUCTION.medication_order.v0]/activities[at0001]/timing/value as medicationDateTime, " +
                "a/content[openEHR-EHR-SECTION.medication_medical_devices_rcp.v1]/items[openEHR-EHR-EVALUATION.anticoagulation_use.v0]/data[at0001]/items[at0002]/value/value as anticoagulationUse, " +
                "a/content[openEHR-EHR-SECTION.allergies_adverse_reactions_rcp.v1, 'History of allergies']/items[openEHR-EHR-EVALUATION.adverse_reaction_risk.v1]/data[at0001]/items[at0002, 'History of allergy']/value/value as allergy, " +
                "a/content[openEHR-EHR-SECTION.allergies_adverse_reactions_rcp.v1, 'History of allergies']/items[openEHR-EHR-EVALUATION.adverse_reaction_risk.v1]/protocol[at0042]/items[at0062]/value/value as allergyDateTime, " +
                "a/content[openEHR-EHR-SECTION.social_context_rcp.v1]/items[openEHR-EHR-EVALUATION.tobacco_use_summary.v1, 'History of tobacco use']/data[at0001]/items[at0029]/items[at0023, 'History of tobacco use']/value/value as tobaccoUse, " +
                "a/content[openEHR-EHR-SECTION.social_context_rcp.v1]/items[openEHR-EHR-EVALUATION.alcohol_use_summary.v1, 'History of alcohol use']/data[at0001]/items[at0024, 'History of alcohol use']/value/value as alcoholUse, " +
                "a/content[openEHR-EHR-SECTION.social_context_rcp.v1]/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1, 'Physical mobility impairment']/data[at0001]/items[at0002, 'Physical mobility impairment']/value/value as physicalMobility, " + 
                "a/content[openEHR-EHR-SECTION.examination_findings_rcp.v1]/items[openEHR-EHR-SECTION.vital_signs.v1]/items[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude as systolicBP, " +
                "a/content[openEHR-EHR-SECTION.examination_findings_rcp.v1]/items[openEHR-EHR-SECTION.vital_signs.v1]/items[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/units as systolicBPUnits, " +
                "a/content[openEHR-EHR-SECTION.examination_findings_rcp.v1]/items[openEHR-EHR-SECTION.vital_signs.v1]/items[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value/magnitude as diastolicBP, " +
                "a/content[openEHR-EHR-SECTION.examination_findings_rcp.v1]/items[openEHR-EHR-SECTION.vital_signs.v1]/items[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value/units as diastolicBPUnits,	" +
                "a/content[openEHR-EHR-SECTION.examination_findings_rcp.v1]/items[openEHR-EHR-SECTION.vital_signs.v1]/items[openEHR-EHR-OBSERVATION.pulse.v1]/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude as pulseRate, " +
                "a/content[openEHR-EHR-SECTION.examination_findings_rcp.v1]/items[openEHR-EHR-SECTION.vital_signs.v1]/items[openEHR-EHR-OBSERVATION.pulse.v1]/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/units as pulseRateUnits, " +
                "a/content[openEHR-EHR-SECTION.examination_findings_rcp.v1]/items[openEHR-EHR-OBSERVATION.height.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude as height, " +
                "a/content[openEHR-EHR-SECTION.examination_findings_rcp.v1]/items[openEHR-EHR-OBSERVATION.height.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/units as heightUnits, " +
                "a/content[openEHR-EHR-SECTION.examination_findings_rcp.v1]/items[openEHR-EHR-OBSERVATION.body_weight.v1]/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude as weight, " +
                "a/content[openEHR-EHR-SECTION.examination_findings_rcp.v1]/items[openEHR-EHR-OBSERVATION.body_weight.v1]/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/units as weightUnits, " +
                "a/content[openEHR-EHR-SECTION.examination_findings_rcp.v1]/items[openEHR-EHR-OBSERVATION.body_mass_index.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude as bodyMassIndex, " + 
                "a/content[openEHR-EHR-SECTION.examination_findings_rcp.v1]/items[openEHR-EHR-OBSERVATION.body_mass_index.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/units as bodyMassIndexUnits, " +
                "a/content[openEHR-EHR-SECTION.examination_findings_rcp.v1]/items[openEHR-EHR-OBSERVATION.exam.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value as OtherFindings " +
                "from EHR e " +
                "contains COMPOSITION a[openEHR-EHR-COMPOSITION.request.v1] " +
                "where a/name/value='Referral' " +
                "and a/uid/value='" + documentId + "' " +
                "and e/ehr_status/subject/external_ref/namespace = '" + namespace + "' " +
                "and e/ehr_status/subject/external_ref/id/value = '" + patientId + "'";
    }

    @Override
    public ReferralDocumentDetails transform(List<Map<String, Object>> resultSet) {

        if (resultSet.isEmpty()) {
            throw new DataNotFoundException("No results found");
        }
        return new ReferralDocumentDetailsTransformer().transformWithRepeatingGroups(resultSet);
    }
}
