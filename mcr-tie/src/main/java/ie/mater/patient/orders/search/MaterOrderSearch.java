/*
 * Copyright 2016 Mater Care Record
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
package ie.mater.patient.orders.search;

import javax.xml.ws.soap.SOAPFaultException;

import java.util.ArrayList;
import java.util.List;

import ie.mater.common.service.AbstractMaterService;
import ie.mater.patient.orders.OCRROrderInfo;
import ie.mater.patient.orders.OCRROrdersArray;
import ie.mater.patient.orders.OrderServiceSoap;
import org.apache.commons.collections4.CollectionUtils;
import org.rippleosi.patient.laborders.model.LabOrderDetails;
import org.rippleosi.patient.laborders.model.LabOrderSummary;
import org.rippleosi.patient.laborders.search.LabOrderSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterOrderSearch extends AbstractMaterService implements LabOrderSearch {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaterOrderSearch.class);

    @Autowired
    private OrderServiceSoap orderService;

    @Override
    public List<LabOrderSummary> findAllLabOrders(String patientId) {
        try {
            patientId = addTrailingSpacesToPatientId(patientId);

            List<OCRROrdersArray> orders = orderService.getOrderSummary(patientId).getOCRROrdersArray();

            return CollectionUtils.collect(orders, new MaterOrderToOrderSummaryTransformer(), new ArrayList<>());
        }
        catch (SOAPFaultException sfe) {
            LOGGER.error("Could not parse order summary list for patient " + patientId + ".");
        }
        catch (NullPointerException npe) {
            LOGGER.error("Order summary list for " + patientId + " was null.");
        }

        return new ArrayList<>();
    }

    @Override
    public LabOrderDetails findLabOrder(String patientId, String labOrderId) {
        try {
            patientId = addTrailingSpacesToPatientId(patientId);

            OCRROrderInfo order = orderService.getOrderDetails(labOrderId);

            return new MaterOrderToOrderDetailsTransformer().transform(order);
        }
        catch (SOAPFaultException sfe) {
            LOGGER.error("Could not parse order " + labOrderId + " for patient " + patientId + ".");
        }
        catch (NullPointerException npe) {
            LOGGER.error("Order " + labOrderId + " for patient " + patientId + " was null.");
        }

        return new LabOrderDetails();
    }
}
