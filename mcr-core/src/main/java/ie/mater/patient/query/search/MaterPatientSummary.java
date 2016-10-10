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

import org.rippleosi.patient.summary.model.PatientSummary;

public class MaterPatientSummary extends PatientSummary {

    private String ihiNumber;
    private String patientPin;
    private String mrnNumber;
    private String specialty;
    private String consultant;

    public String getIhiNumber() {
        return ihiNumber;
    }

    public void setIhiNumber(String ihiNumber) {
        this.ihiNumber = ihiNumber;
    }

    public String getPatientPin() {
        return patientPin;
    }

    public void setPatientPin(String patientPin) {
        this.patientPin = patientPin;
    }

    public String getMrnNumber() {
        return mrnNumber;
    }

    public void setMrnNumber(String mrnNumber) {
        this.mrnNumber = mrnNumber;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getConsultant() {
        return consultant;
    }

    public void setConsultant(String consultant) {
        this.consultant = consultant;
    }
}
