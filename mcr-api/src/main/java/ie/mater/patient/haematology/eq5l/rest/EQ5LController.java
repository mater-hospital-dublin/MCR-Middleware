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
package ie.mater.patient.haematology.eq5l.rest;

import java.util.List;

import ie.mater.patient.haematology.eq5l.search.EQ5LSearch;
import ie.mater.patient.haematology.eq5l.store.EQ5LStore;
import ie.mater.patient.haematology.eq5l.model.EQ5LDetails;
import ie.mater.patient.haematology.eq5l.model.EQ5LSummary;
import ie.mater.patient.haematology.eq5l.search.EQ5LSearchFactory;
import ie.mater.patient.haematology.eq5l.store.EQ5LStoreFactory;
import org.rippleosi.common.types.RepoSourceType;
import org.rippleosi.common.types.lookup.RepoSourceLookupFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients/{patientId}/eq5l")
public class EQ5LController {

    @Autowired
    private RepoSourceLookupFactory repoSourceLookup;
    
    @Autowired
    private EQ5LSearchFactory eq5lSearchFactory;

    @Autowired
    private EQ5LStoreFactory eq5lStoreFactory;

    @RequestMapping(method = RequestMethod.GET)
    public List<EQ5LSummary> findAllEQ5Ls(@PathVariable("patientId") String patientId,
                                          @RequestParam(required = false) String source) {
        final RepoSourceType sourceType = repoSourceLookup.lookup(source);
        EQ5LSearch search = eq5lSearchFactory.select(sourceType);

        return search.findAllEQ5Ls(patientId);
    }

    @RequestMapping(value = "/{eq5lId}", method = RequestMethod.GET)
    public EQ5LDetails findEQ5L(@PathVariable("patientId") String patientId,
                                @PathVariable("eq5lId") String eq5lId,
                                @RequestParam(required = false) String source) {
        final RepoSourceType sourceType = repoSourceLookup.lookup(source);
        EQ5LSearch search = eq5lSearchFactory.select(sourceType);

        return search.findEQ5L(patientId, eq5lId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createEQ5L(@PathVariable("patientId") String patientId,
                           @RequestParam(required = false) String source,
                           @RequestBody EQ5LDetails eq5l) {
        final RepoSourceType sourceType = repoSourceLookup.lookup(source);
        EQ5LStore store = eq5lStoreFactory.select(sourceType);

        store.create(patientId, eq5l);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateEQ5L(@PathVariable("patientId") String patientId,
                           @RequestParam(required = false) String source,
                           @RequestBody EQ5LDetails eq5l) {
        final RepoSourceType sourceType = repoSourceLookup.lookup(source);
        EQ5LStore store = eq5lStoreFactory.select(sourceType);

        store.update(patientId, eq5l);
    }
}
