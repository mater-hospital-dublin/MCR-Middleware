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
public class AllergyDetails implements Serializable {

    private String sourceId;
    private String cause;
    private String causeCode;
    private String causeTerminology;
    private String terminologyCode;
    private String reaction;
    private String source;
    private String author;
    private Date dateCreated;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCauseCode() {
        return causeCode;
    }

    public void setCauseCode(String causeCode) {
        this.causeCode = causeCode;
    }

    public String getCauseTerminology() {
        return causeTerminology;
    }

    public void setCauseTerminology(String causeTerminology) {
        this.causeTerminology = causeTerminology;
    }

    public String getTerminologyCode() {
        return terminologyCode;
    }

    public void setTerminologyCode(String terminologyCode) {
        this.terminologyCode = terminologyCode;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.sourceId);
        hash = 31 * hash + Objects.hashCode(this.cause);
        hash = 31 * hash + Objects.hashCode(this.causeCode);
        hash = 31 * hash + Objects.hashCode(this.causeTerminology);
        hash = 31 * hash + Objects.hashCode(this.terminologyCode);
        hash = 31 * hash + Objects.hashCode(this.reaction);
        hash = 31 * hash + Objects.hashCode(this.source);
        hash = 31 * hash + Objects.hashCode(this.author);
        hash = 31 * hash + Objects.hashCode(this.dateCreated);
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
        final AllergyDetails other = (AllergyDetails) obj;
        if (!Objects.equals(this.sourceId, other.sourceId)) {
            return false;
        }
        if (!Objects.equals(this.cause, other.cause)) {
            return false;
        }
        if (!Objects.equals(this.causeCode, other.causeCode)) {
            return false;
        }
        if (!Objects.equals(this.causeTerminology, other.causeTerminology)) {
            return false;
        }
        if (!Objects.equals(this.terminologyCode, other.terminologyCode)) {
            return false;
        }
        if (!Objects.equals(this.reaction, other.reaction)) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
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
