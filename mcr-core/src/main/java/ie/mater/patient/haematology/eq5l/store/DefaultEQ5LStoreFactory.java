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
package ie.mater.patient.haematology.eq5l.store;

import org.rippleosi.common.repo.AbstractRepositoryFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultEQ5LStoreFactory
    extends AbstractRepositoryFactory<EQ5LStore> implements EQ5LStoreFactory {

    @Override
    protected EQ5LStore defaultRepository() {
        return new NotConfiguredEQ5LStore();
    }

    @Override
    protected Class<EQ5LStore> repositoryClass() {
        return EQ5LStore.class;
    }
}
