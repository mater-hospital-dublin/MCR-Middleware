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
import ie.mater.patient.orders.OCRROrderInfo;
import org.apache.commons.collections4.Transformer;
import org.rippleosi.patient.laborders.model.LabOrderDetails;

public class MaterOrderToOrderDetailsTransformer implements Transformer<OCRROrderInfo, LabOrderDetails> {

    @Override
    public LabOrderDetails transform(OCRROrderInfo materOrder) {
        LabOrderDetails labOrder = new LabOrderDetails();

        labOrder.setSource(materOrder.getSource());
        labOrder.setSourceId(materOrder.getSourceId());
        labOrder.setName(materOrder.getName());

        Date dateOrdered = MaterDateFormatter.toDate(materOrder.getOrderDate());
        Date dateCreated = MaterDateFormatter.toDate(materOrder.getDateCreated());

        labOrder.setOrderDate(dateOrdered);
        labOrder.setDateCreated(dateCreated);

        labOrder.setCode(materOrder.getCode());
        labOrder.setTerminology(materOrder.getTerminology());
        labOrder.setAuthor(materOrder.getAuthor());

        return labOrder;
    }
}
