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
package org.icrripple.patient.haematology.bleeds.store;

import org.apache.camel.Produce;
import org.icrripple.patient.haematology.bleeds.model.BleedDetails;
import org.rippleosi.common.types.RepoSource;
import org.rippleosi.common.types.RepoSourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQProxyBleedStore implements BleedStore {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQProxyBleedStore.class);

    @Value("${repository.config.jms:500}")
    private int priority;

    @Produce(uri = "activemq:topic:VirtualTopic.Ripple.Bleeds.Create")
    private BleedStore createTopic;

    @Produce(uri = "activemq:topic:VirtualTopic.Ripple.Bleeds.Update")
    private BleedStore updateTopic;

    @Override
    public RepoSource getSource() {
        return RepoSourceType.ACTIVEMQ;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void create(String patientId, BleedDetails bleed) {
        try {
            createTopic.create(patientId, bleed);
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void update(String patientId, BleedDetails bleed) {
        try {
            updateTopic.update(patientId, bleed);
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
