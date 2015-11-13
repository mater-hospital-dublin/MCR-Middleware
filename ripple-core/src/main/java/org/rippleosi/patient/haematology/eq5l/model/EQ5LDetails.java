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
package org.rippleosi.patient.haematology.eq5l.model;

import java.io.Serializable;
import java.util.Date;

public class EQ5LDetails implements Serializable {

    private String source;
    private String sourceId;
    private String mobility;
    private String selfCare;
    private String usualActivities;
    private String pain;
    private String anxiety;
    private Integer lifeScore;
    private Date dateRecorded;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getMobility() {
        return mobility;
    }

    public void setMobility(String mobility) {
        this.mobility = mobility;
    }

    public String getSelfCare() {
        return selfCare;
    }

    public void setSelfCare(String selfCare) {
        this.selfCare = selfCare;
    }

    public String getUsualActivities() {
        return usualActivities;
    }

    public void setUsualActivities(String usualActivities) {
        this.usualActivities = usualActivities;
    }

    public String getPain() {
        return pain;
    }

    public void setPain(String pain) {
        this.pain = pain;
    }

    public String getAnxiety() {
        return anxiety;
    }

    public void setAnxiety(String anxiety) {
        this.anxiety = anxiety;
    }

    public Integer getLifeScore() {
        return lifeScore;
    }

    public void setLifeScore(Integer lifeScore) {
        this.lifeScore = lifeScore;
    }

    public Date getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(Date dateRecorded) {
        this.dateRecorded = dateRecorded;
    }
}
