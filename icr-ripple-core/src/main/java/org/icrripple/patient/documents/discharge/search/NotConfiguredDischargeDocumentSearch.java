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
package org.icrripple.patient.documents.discharge.search;

import java.util.List;

import org.rippleosi.common.exception.ConfigurationException;
import org.icrripple.patient.documents.discharge.model.DischargeDocumentDetails;
import org.icrripple.patient.documents.common.model.AbstractDocumentSummary;
import org.rippleosi.common.types.RepoSourceType;
import org.rippleosi.common.types.RepoSourceTypes;

/**
 */
public class NotConfiguredDischargeDocumentSearch implements DischargeDocumentSearch {

    @Override
    public RepoSourceType getSource() {
        return RepoSourceTypes.NONE;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public List<AbstractDocumentSummary> findAllDischargeDocuments(String patientId) {
        throw ConfigurationException.unimplementedTransaction(DischargeDocumentSearch.class);
    }

    @Override
    public DischargeDocumentDetails findDischargeDocument(String patientId, String documentId) {
        throw ConfigurationException.unimplementedTransaction(DischargeDocumentSearch.class);
    }
}
