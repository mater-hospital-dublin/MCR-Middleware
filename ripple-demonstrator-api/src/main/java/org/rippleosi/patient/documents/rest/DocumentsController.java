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

import org.rippleosi.patient.documents.model.DocumentDetails;
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

    @RequestMapping(value = "/referral", method = RequestMethod.POST, consumes = "application/xml")
    public void createContact(@PathVariable("patientId") String patientId,
                              @RequestParam(required = false) String source,
                              @RequestBody String body) {
        
        DocumentDetails document = new DocumentDetails();
        document.setDocumentType("hl7Referral");
        document.setOrigionalSource(source);
        document.setDocumentContent(body);
        
        DocumentStore contactStore = documentStoreFactory.select(source);
        contactStore.create(patientId, document);
    }

}
