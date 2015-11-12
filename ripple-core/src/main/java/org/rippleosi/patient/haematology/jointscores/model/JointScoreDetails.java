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
package org.rippleosi.patient.haematology.jointscores.model;

import java.io.Serializable;
import java.util.Date;

public class JointScoreDetails implements Serializable {

    private String source;
    private String sourceId;
    private Date dateRecorded;
    private Integer leftKnee;
    private Integer rightKnee;
    private Integer leftElbow;
    private Integer rightElbow;
    private Integer leftAnkle;
    private Integer rightAnkle;
    private Integer totalScore;

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

    public Date getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(Date dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public Integer getLeftKnee() {
        return leftKnee;
    }

    public void setLeftKnee(Integer leftKnee) {
        this.leftKnee = leftKnee;
    }

    public Integer getRightKnee() {
        return rightKnee;
    }

    public void setRightKnee(Integer rightKnee) {
        this.rightKnee = rightKnee;
    }

    public Integer getLeftElbow() {
        return leftElbow;
    }

    public void setLeftElbow(Integer leftElbow) {
        this.leftElbow = leftElbow;
    }

    public Integer getRightElbow() {
        return rightElbow;
    }

    public void setRightElbow(Integer rightElbow) {
        this.rightElbow = rightElbow;
    }

    public Integer getLeftAnkle() {
        return leftAnkle;
    }

    public void setLeftAnkle(Integer leftAnkle) {
        this.leftAnkle = leftAnkle;
    }

    public Integer getRightAnkle() {
        return rightAnkle;
    }

    public void setRightAnkle(Integer rightAnkle) {
        this.rightAnkle = rightAnkle;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}
