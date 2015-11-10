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
package org.rippleosi.patient.haematology.eq5l.search;

import org.rippleosi.common.repo.AbstractRepositoryFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultEQ5LSearchFactory
    extends AbstractRepositoryFactory<EQ5LSearch> implements EQ5LSearchFactory {

    @Override
    protected EQ5LSearch defaultRepository() {
        return new NotConfiguredEQ5LSearch();
    }

    @Override
    protected Class<EQ5LSearch> repositoryClass() {
        return EQ5LSearch.class;
    }
}
