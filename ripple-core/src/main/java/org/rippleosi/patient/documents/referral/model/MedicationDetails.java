/*
 * Copyright 2015 Ripple OSI
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
package org.rippleosi.patient.documents.referral.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 */
public class MedicationDetails implements Serializable {

    private String sourceId;
    private String source;
    private String name;
    private String doseAmount;
    private String doseDirections;
    private String doseTiming;
    private String route;
    private Date startDate;
    private Date startTime;
    private String medicationCode;
    private String medicationTerminology;
    private String author;
    private Date dateCreated;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoseAmount() {
        return doseAmount;
    }

    public void setDoseAmount(String doseAmount) {
        this.doseAmount = doseAmount;
    }

    public String getDoseDirections() {
        return doseDirections;
    }

    public void setDoseDirections(String doseDirections) {
        this.doseDirections = doseDirections;
    }

    public String getDoseTiming() {
        return doseTiming;
    }

    public void setDoseTiming(String doseTiming) {
        this.doseTiming = doseTiming;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(String medicationCode) {
        this.medicationCode = medicationCode;
    }

    public String getMedicationTerminology() {
        return medicationTerminology;
    }

    public void setMedicationTerminology(String medicationTerminology) {
        this.medicationTerminology = medicationTerminology;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.sourceId);
        hash = 11 * hash + Objects.hashCode(this.source);
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + Objects.hashCode(this.doseAmount);
        hash = 11 * hash + Objects.hashCode(this.doseDirections);
        hash = 11 * hash + Objects.hashCode(this.doseTiming);
        hash = 11 * hash + Objects.hashCode(this.route);
        hash = 11 * hash + Objects.hashCode(this.startDate);
        hash = 11 * hash + Objects.hashCode(this.startTime);
        hash = 11 * hash + Objects.hashCode(this.medicationCode);
        hash = 11 * hash + Objects.hashCode(this.medicationTerminology);
        hash = 11 * hash + Objects.hashCode(this.author);
        hash = 11 * hash + Objects.hashCode(this.dateCreated);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MedicationDetails other = (MedicationDetails) obj;
        if (!Objects.equals(this.sourceId, other.sourceId)) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.doseAmount, other.doseAmount)) {
            return false;
        }
        if (!Objects.equals(this.doseDirections, other.doseDirections)) {
            return false;
        }
        if (!Objects.equals(this.doseTiming, other.doseTiming)) {
            return false;
        }
        if (!Objects.equals(this.route, other.route)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.startTime, other.startTime)) {
            return false;
        }
        if (!Objects.equals(this.medicationCode, other.medicationCode)) {
            return false;
        }
        if (!Objects.equals(this.medicationTerminology, other.medicationTerminology)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.dateCreated, other.dateCreated)) {
            return false;
        }
        return true;
    }
    
}
