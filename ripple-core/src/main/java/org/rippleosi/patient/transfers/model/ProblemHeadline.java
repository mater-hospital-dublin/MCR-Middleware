package org.rippleosi.patient.transfers.model;

import java.io.Serializable;

public class ProblemHeadline implements Serializable {

    private String sourceId;
    private String problem;
    private String source;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}