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
package ie.mater.patient.query.search;

import org.apache.commons.collections4.Transformer;
import org.rippleosi.patient.contacts.model.ContactHeadline;
import org.rippleosi.patient.summary.model.PatientHeadline;

public class ContactHeadlineToPatientHeadlineTransformer implements Transformer<ContactHeadline, PatientHeadline> {

    @Override
    public PatientHeadline transform(ContactHeadline contactHeadline) {
        PatientHeadline patientHeadline = new PatientHeadline();

        patientHeadline.setSource(contactHeadline.getSource());
        patientHeadline.setSourceId(contactHeadline.getSourceId());
        patientHeadline.setText(contactHeadline.getName());

        return patientHeadline;
    }
}
