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
package ie.mater.patient.haematology.bleeds.store;

import org.apache.camel.Body;
import org.apache.camel.Header;
import org.rippleosi.common.exception.ConfigurationException;
import ie.mater.patient.haematology.bleeds.model.BleedDetails;
import org.rippleosi.common.types.RepoSourceType;
import org.rippleosi.common.types.RepoSourceTypes;

public class NotConfiguredBleedStore implements BleedStore {

    @Override
    public RepoSourceType getSource() {
        return RepoSourceTypes.NONE;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void update(@Header("patientId") String patientId, @Body BleedDetails bleed) {
        throw ConfigurationException.unimplementedTransaction(BleedStore.class);
    }

    @Override
    public void create(@Header("patientId") String patientId, @Body BleedDetails bleed) {
        throw ConfigurationException.unimplementedTransaction(BleedStore.class);
    }
}
