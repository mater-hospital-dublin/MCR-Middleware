/*
 *   Copyright 2015 Ripple OSI
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */
package org.icrripple.patient.haematology.eq5l.search;

import java.util.List;
import org.icrripple.patient.haematology.eq5l.model.EQ5LDetails;
import org.icrripple.patient.haematology.eq5l.model.EQ5LSummary;
import org.rippleosi.common.service.AbstractOpenEhrService;
import org.springframework.stereotype.Service;

@Service
public class OpenEHREQ5LSearch extends AbstractOpenEhrService implements EQ5LSearch {

    @Override
    public List<EQ5LSummary> findAllEQ5Ls(String patientId) {
        EQ5LSummaryQueryStrategy query = new EQ5LSummaryQueryStrategy(patientId);

        return findData(query);
    }

    @Override
    public EQ5LDetails findEQ5L(String patientId, String eq5lId) {
        EQ5LDetailsQueryStrategy query = new EQ5LDetailsQueryStrategy(patientId, eq5lId);

        return findData(query);
    }
}
