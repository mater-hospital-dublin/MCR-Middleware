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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.Transformer;
import org.rippleosi.common.util.DateFormatter;
import org.rippleosi.patient.documents.referral.model.ReferralDocumentDetails;

/**
 */
public class ReferralDocumentDetailsTransformer implements Transformer<Map<String, Object>, ReferralDocumentDetails> {

    @Override
    public ReferralDocumentDetails transform(Map<String, Object> input) {
        ReferralDocumentDetails referralDocument = new ReferralDocumentDetails();
        return referralDocument;
    }
    
    public ReferralDocumentDetails transformWithRepeatingGroups(List<Map<String, Object>> resultSet) {

        ReferralDocumentDetails referralDocument = new ReferralDocumentDetails();
        
        // From first row/map set all the none repeating groups as they will be the same on all rows.
        Map<String, Object> input = resultSet.get(0);

        referralDocument.setReferralDateTime(DateFormatter.toDate(MapUtils.getString(input, "referralDateTime")));
        referralDocument.setComposerName(MapUtils.getString(input, "authorName"));
        referralDocument.setFacility(MapUtils.getString(input, "facility"));
        referralDocument.setReferralType(MapUtils.getString(input, "referralType"));
        referralDocument.setReferralComments(MapUtils.getString(input, "referralComment"));
        referralDocument.setPriorityOfReferral(MapUtils.getString(input, "priorityOfReferral"));
        referralDocument.setReferralReferenceNumber(MapUtils.getString(input, "referralReferenceNumber"));
        referralDocument.setReferredFrom(MapUtils.getString(input, "referredFrom"));
        referralDocument.setReferredTo(MapUtils.getString(input, "referredTo"));
        
        referralDocument.setProviderContact_organisationName(MapUtils.getString(input, "providerContactOrgName"));
        referralDocument.setProviderContact_id(MapUtils.getString(input, "providerContactId"));
        referralDocument.setProviderContact_workNumber(MapUtils.getString(input, "providerContactWorkNumber"));
        referralDocument.setProviderContact_emergencyNumber(MapUtils.getString(input, "providerContactEmgNumber"));
        referralDocument.setProviderContact_email(MapUtils.getString(input, "providerContactEmail"));
        
        referralDocument.setReferralStatus_code(MapUtils.getString(input, "referralStatusCode"));
        referralDocument.setReferralStatus_value(MapUtils.getString(input, "referralStatusValue"));
        switch(MapUtils.getString(input, "referralStatusCode")){
            case "526": referralDocument.setReferralStatus_mapped("Pending"); break;
            case "529": referralDocument.setReferralStatus_mapped("Accepted"); break;
            case "528": referralDocument.setReferralStatus_mapped("Rejected"); break;
            case "531": referralDocument.setReferralStatus_mapped("Expired"); break;
        }

        /*
                clinicalNarrative, " +
                presentIllness, " +
                clinicalSynopsis, " +
                previousHospitalAttendance, " +
                anticoagulationUse, " +
                tobaccoUse, " +
                alcoholUse, " +
                physicalMobility, " + 
                systolicBP, " +
                systolicBPUnits, " +
                diastolicBP, " +
                diastolicBPUnits,	" +
                 pulseRate, " +
                pulseRateUnits, " +
                height, " +
                heightUnits, " +
                weight, " +
                weightUnits, " +
                bodyMassIndex, " + 
                bodyMassIndexUnits, " +
                OtherFindings " +
        */
        
        // referralDocument.setReasonForReferral(MapUtils.getString(input, "reasonForReferral"));   //Repeating element
        /*
        pastIllness, " +
                pastIllnessDateTime, " +
                surgicalProcedure, " +
                surgicalProcedureTime,	" +
                medication, " +
                medicationDateTime, " +
                allergy, " +
                allergyDateTime, " +
        */
        
        return referralDocument;
    }
}
