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
package org.rippleosi.patient.documents.rest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.rippleosi.common.util.DateFormatter;
import org.rippleosi.patient.documents.discharge.search.DischargeDocumentSearch;
import org.rippleosi.patient.documents.discharge.search.DischargeDocumentSearchFactory;

import org.rippleosi.patient.documents.model.GenericDocument;
import org.rippleosi.patient.documents.model.GenericDocumentSummary;
import org.rippleosi.patient.documents.referral.search.ReferralDocumentSearch;
import org.rippleosi.patient.documents.referral.search.ReferralDocumentSearchFactory;
import org.rippleosi.patient.documents.store.DocumentStore;
import org.rippleosi.patient.documents.store.DocumentStoreFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@RequestMapping("/patients/{patientId}/documents")
public class DocumentsController {

    @Autowired
    private DocumentStoreFactory documentStoreFactory;

    @Autowired
    private DischargeDocumentSearchFactory dischargeDocumentSearchFactory;
    
    @Autowired
    private ReferralDocumentSearchFactory referralDocumentSearchFactory;
    
    @RequestMapping(value = "/referral", method = RequestMethod.POST, consumes = "application/xml")
    public void createReferral(@PathVariable("patientId") String patientId,
                              @RequestParam(required = false) String source,
                              @RequestBody String body) {
        
        GenericDocument document = new GenericDocument();
        document.setDocumentType("hl7Referral");
        document.setOrigionalSource(source);
        document.setDocumentContent(body);
        
        DocumentStore contactStore = documentStoreFactory.select(source);
        contactStore.create(patientId, document);
    }
    
    @RequestMapping(value = "/discharge", method = RequestMethod.POST, consumes = "application/xml")
    public void createDischarge(@PathVariable("patientId") String patientId,
                              @RequestParam(required = false) String source,
                              @RequestBody String body) {
        
        GenericDocument document = new GenericDocument();
        document.setDocumentType("hl7Discharge");
        document.setOrigionalSource(source);
        document.setDocumentContent(body);
        
        DocumentStore contactStore = documentStoreFactory.select(source);
        contactStore.create(patientId, document);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GenericDocumentSummary> findAllDocuments(@PathVariable("patientId") String patientId, @RequestParam(required = false) String source) {
        
        DischargeDocumentSearch dischargeDocumentSearch = dischargeDocumentSearchFactory.select(source);
        List<GenericDocumentSummary> dischargeDocuments = dischargeDocumentSearch.findAllDischargeDocuments(patientId);
        
        ReferralDocumentSearch referralDocumentSearch = referralDocumentSearchFactory.select(source);
        List<GenericDocumentSummary> referralDocuments = referralDocumentSearch.findAllReferralDocuments(patientId);
        
        List<GenericDocumentSummary> returnList = dischargeDocuments;
        returnList.addAll(referralDocuments);
        
        // Sort by date
        Collections.sort(returnList, new Comparator<GenericDocumentSummary>(){
            @Override
            public int compare(GenericDocumentSummary gds1, GenericDocumentSummary gds2){
                return DateFormatter.toDate(gds2.getDocumentDate()).compareTo(DateFormatter.toDate(gds1.getDocumentDate()));
            }
         });
        
        return returnList;
    }
}
