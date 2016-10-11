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

import java.util.Date;

import ie.mater.common.util.MaterDateFormatter;
import ie.mater.patient.orders.OCRROrdersArray;
import org.apache.commons.collections4.Transformer;
import org.rippleosi.patient.laborders.model.LabOrderSummary;

public class MaterOrderToOrderSummaryTransformer implements Transformer<OCRROrdersArray, LabOrderSummary> {

    @Override
    public LabOrderSummary transform(OCRROrdersArray materOrder) {
        LabOrderSummary labOrder = new LabOrderSummary();

        labOrder.setSource(materOrder.getSource());
        labOrder.setSourceId(materOrder.getSourceId());
        labOrder.setName(materOrder.getName());

        Date labOrderDate = MaterDateFormatter.toDate(materOrder.getOrderDate());
        labOrder.setOrderDate(labOrderDate);

        return labOrder;
    }
}
