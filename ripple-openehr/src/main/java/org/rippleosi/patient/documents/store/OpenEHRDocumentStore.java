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
package org.rippleosi.patient.documents.store;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.apache.camel.Consume;
import org.rippleosi.common.service.AbstractOpenEhrService;
import org.rippleosi.common.service.CreateStrategy;
import org.rippleosi.common.service.DefaultStoreStrategy;
import org.rippleosi.common.util.DateFormatter;
import static org.rippleosi.common.util.DateFormatter.*;
import org.rippleosi.patient.documents.model.DocumentDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

/**
 */
@Service
public class OpenEHRDocumentStore extends AbstractOpenEhrService implements DocumentStore {

    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    @Value("${c4hOpenEHR.referralDocumentTemplate}")
    private String referralDocumentTemplate;
    
    @Value("${c4hOpenEHR.dischargeDocumentTemplate}")
    private String dischargeDocumentTemplate;

    private static final Logger logger = LoggerFactory.getLogger(OpenEHRDocumentStore.class);
        
    @Override
    @Consume(uri = "activemq:Consumer.C4HOpenEHR.VirtualTopic.Ripple.Documents.Create")
    public void create(String patientId, DocumentDetails document) {

        Map<String,Object> content = null;
        String documentTemplate = null;
        
        if("hl7Referral".equalsIgnoreCase(document.getDocumentType())){
            content = createReferralFlatJsonContent(document);
            documentTemplate = referralDocumentTemplate;
        } else if ("hl7Discharge".equalsIgnoreCase(document.getDocumentType())){
            // Create and save the discharge data
        }
        
        if(documentTemplate != null && content != null){
            CreateStrategy createStrategy = new DefaultStoreStrategy(patientId, documentTemplate, content);
            createData(createStrategy);
        }
    }

    private Map<String,Object> createReferralFlatJsonContent(DocumentDetails document) {
    
        final String REFERRAL_REQUEST_PREFIX = "referral/referral_details/service_request/";
        final String REFERRAL_STATUS_PREFIX = "referral/referral_details/referral_status/";
    
        String hl7DocumentXML = (String)document.getDocumentContent();
    
        Map<String, Object> content = null;
        
        try {
            DocumentBuilder xmlDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document hl7Document = xmlDocumentBuilder.parse(new ByteArrayInputStream(hl7DocumentXML.getBytes(StandardCharsets.UTF_8)));
            XPath xPath = XPathFactory.newInstance().newXPath();
            
            content = new HashMap<>();
            
            content.put("ctx/language", "en");
            content.put("ctx/territory", "GB");
            content.put("ctx/composer_name", (String)xPath.compile("//*:MSH/*:MSH.4/*:HD.1").evaluate(hl7Document));
            content.put("ctx/health_care_facility|id", (String)xPath.compile("//*:MSH.4/*:HD.2").evaluate(hl7Document));
            content.put("ctx/time", (String)xPath.compile("//*:MSH.7/*:TS.1").evaluate(hl7Document));
            
            content.put(REFERRAL_REQUEST_PREFIX + "referred_to_provider/identifier", (String)xPath.compile("//*:MSH.6/*:HD.1").evaluate(hl7Document));
            content.put(REFERRAL_REQUEST_PREFIX + "referral_control_number", (String)xPath.compile("//*:MSH.10").evaluate(hl7Document));
            
            String referalStatus = (String)xPath.compile("//*:RF1.1/*:CE.1").evaluate(hl7Document);
            if ("P".equalsIgnoreCase(referalStatus)){
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/current_state|code", "526");
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/current_state|value", "planned");
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/careflow_step|code", "at0002");
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/careflow_step|value", "Referral planned");
            } else if ("A".equalsIgnoreCase(referalStatus)){
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/current_state|code", "529");
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/current_state|value", "scheduled");
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/careflow_step|code", "at0003");
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/careflow_step|value", "Appoinment scheduled");
            } else if ("R".equalsIgnoreCase(referalStatus)){
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/current_state|code", "528");
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/current_state|value", "cancelled");
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/careflow_step|code", "at009");
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/careflow_step|value", "Referral cancelled");
            } else if ("E".equalsIgnoreCase(referalStatus)){
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/current_state|code", "531");
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/current_state|value", "aborted");
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/careflow_step|code", "at023");
                content.put(REFERRAL_STATUS_PREFIX + "ism_transition/careflow_step|value", "Referral expired");
            }
            
            String referralType = (String)xPath.compile("//*:RF1.3/*:CE.1").evaluate(hl7Document);
            content.put(REFERRAL_STATUS_PREFIX + "referral_type", referralType);
            content.put(REFERRAL_REQUEST_PREFIX + "request:0/referral_type", referralType);
            content.put(REFERRAL_REQUEST_PREFIX + "request:0/comments", (String)xPath.compile("//*:RF1.3/*:CE.2").evaluate(hl7Document));
            
            content.put(REFERRAL_REQUEST_PREFIX + "referring_provider/identifier", (String)xPath.compile("//*:RF1.6/*:EI.1").evaluate(hl7Document));
            
            String time = (String)xPath.compile("//*:RF1.7/*:TS.1").evaluate(hl7Document);
            if(time != null && !time.isEmpty()){
                if(time.length() == 8){
                    time = toSimpleDateString(DATE_FORMAT.parse(time));
                } else if (time.length() == 14){
                    time = toSimpleDateString(DATE_TIME_FORMAT.parse(time));
                }
                content.put(REFERRAL_STATUS_PREFIX + "time", time);
            }
    
            String organisationName = (String)xPath.compile("//*:REF_I12.PROVIDER_CONTACT[1]/*:PRD/*:PRD.4/PL.1").evaluate(hl7Document);
            content.put(REFERRAL_REQUEST_PREFIX + "distribution:0/individual_recipient:0/gp/name_of_organisation", organisationName);
            content.put("ctx/health_care_facility|name", organisationName);
            content.put(REFERRAL_REQUEST_PREFIX + "referring_provider/name_of_organisation", organisationName);
            content.put(REFERRAL_REQUEST_PREFIX + "distribution:0/individual_recipient:0/gp/identifier", (String)xPath.compile("//*:REF_I12.PROVIDER_CONTACT[1]/*:PRD/*:PRD.5/XTN.1").evaluate(hl7Document));
            content.put(REFERRAL_REQUEST_PREFIX + "referring_provider/identifier", (String)xPath.compile("//*:REF_I12.PROVIDER_CONTACT[1]/*:PRD/*:PRD.7/PI.1").evaluate(hl7Document));
            
            content.put(REFERRAL_REQUEST_PREFIX + "referred_to_provider/name_of_organisation", (String)xPath.compile("//*:REF_I12.PROVIDER_CONTACT[2]/*:PRD/*:PRD.3/XAD.2").evaluate(hl7Document));
            
            
            int numberOfObservationSections = ((Double)xPath.compile("count(//*:REF_I12.OBSERVATION)").evaluate(hl7Document, XPathConstants.NUMBER)).intValue();
            
            for(int obsSect = 1; obsSect <= numberOfObservationSections; obsSect++){
                
                int numberOfResultNotes = ((Double)xPath.compile("count(//*:REF_I12.OBSERVATION["+obsSect+"]/*:REF_I12.RESULTS_NOTES)").evaluate(hl7Document, XPathConstants.NUMBER)).intValue();

                int medicationIndex = 0;

                for(int resNoteIndex = 1; resNoteIndex <= numberOfResultNotes; resNoteIndex++){

                    //Loop through all the result notes and add the nodes for the results.
                    String type = (String)xPath.compile("//*:REF_I12.OBSERVATION["+obsSect+"]/*:REF_I12.RESULTS_NOTES[" + resNoteIndex + "]/*:OBX/*:OBX.3/*:CE.2").evaluate(hl7Document).toLowerCase();
                    String value = (String)xPath.compile("//*:REF_I12.OBSERVATION["+obsSect+"]/*:REF_I12.RESULTS_NOTES[" + resNoteIndex + "]/*:OBX/*:OBX.5").evaluate(hl7Document);
                    String units = (String)xPath.compile("//*:REF_I12.OBSERVATION["+obsSect+"]/*:REF_I12.RESULTS_NOTES[" + resNoteIndex + "]/*:OBX/*:OBX.6/*:CE.1").evaluate(hl7Document);
                    String timeValue = (String)xPath.compile("//*:REF_I12.OBSERVATION["+obsSect+"]/*:REF_I12.RESULTS_NOTES[" + resNoteIndex + "]/*:OBX/*:OBX.14/*:TS.1").evaluate(hl7Document);

                    switch(type){

                        case "reason for referral" :
                            content.put(REFERRAL_REQUEST_PREFIX + "request:0/reason_for_referral", value);
                            break;

                        case "previous hospital attendance" :
                            if("No".equalsIgnoreCase(value)){
                                content.put("referral/history/hospital_attendances_summary/previous_attendances", "true");
                            } else {
                                content.put("referral/history/hospital_attendances_summary/previous_attendances", "false");
                            }
                            break;

                        case "history of present illness" :
                            content.put("referral/history/story_history/history_of_present_illness", value);
                            break;

                        case "history of surgical procedures" :
                            if("NIL".equalsIgnoreCase(value)){
                                // TO BE DONE
                            } else {
                                content.put("referral/procedures/procedure:0/ism_transition/current_state|code", "532");
                                content.put("referral/procedures/procedure:0/ism_transition/current_state|value", "completed");
                                content.put("referral/procedures/procedure:0/history_of_surgical_procedure", value);

                                if(timeValue.length() == 8){
                                    timeValue = toSimpleDateString(DATE_FORMAT.parse(timeValue));
                                } else if (timeValue.length() == 14){
                                    timeValue = DateFormatter.toString(DATE_TIME_FORMAT.parse(timeValue));
                                }
                                content.put("referral/procedures/procedure:0/time", DateFormatter.toString(DATE_TIME_FORMAT.parse(timeValue)));
                            }
                            break;

                        case "history of allergies" :
                            if(!"NIL".equalsIgnoreCase(value)){
                                content.put("referral/allergies_and_adverse_reactions/adverse_reaction_risk/history_of_allergy", value);
                            }
                            break;

                        case "pulse" :
                            content.put("referral/examination_findings/vital_signs/pulse_heart_beat/any_event/pulse_rate|magnitude", value);
                            content.put("referral/examination_findings/vital_signs/pulse_heart_beat/any_event/pulse_rate|unit", units);
                            break;

                        case "systolic blood pressure" :
                            content.put("referral/examination_findings/vital_signs/blood_pressure/any_event/systolic|magnitude", value);
                            content.put("referral/examination_findings/vital_signs/blood_pressure/any_event/systolic|unit", units);
                            break;

                        case "diastolic blood pressure" :
                            content.put("referral/examination_findings/vital_signs/blood_pressure/any_event/diastolic|magnitude", value);
                            content.put("referral/examination_findings/vital_signs/blood_pressure/any_event/diastolic|unit", units);
                            break;

                        case "body height" :
                            content.put("referral/examination_findings/height_length/any_event/height_length|magnitude", value);
                            content.put("referral/examination_findings/height_length/any_event/height_length|unit", units);
                            break;

                        case "weight" :
                            content.put("referral/examination_findings/body_weight/any_event/weight|magnitude", value);
                            content.put("referral/examination_findings/body_weight/any_event/weight|unit", units);
                            break;

                        case "anticoagulant use" :
                            if("Yes".equalsIgnoreCase(value)){
                                content.put("referral/medication_and_medical_devices/anticoagulation_use/anticoagulation_use", "true");
                            } else {
                                content.put("referral/medication_and_medical_devices/anticoagulation_use/anticoagulation_use", "false");
                            }
                            break;

                        case "current medication" :
                            content.put("referral/medication_and_medical_devices/medication_order:"+medicationIndex+"/order/medication_item", value);
                            medicationIndex++;
                            break;
                    }

                }
            }
            
        } catch (Exception e){
            content = null;
            logger.error(e.getMessage());
        }
        return content;
    }

}
