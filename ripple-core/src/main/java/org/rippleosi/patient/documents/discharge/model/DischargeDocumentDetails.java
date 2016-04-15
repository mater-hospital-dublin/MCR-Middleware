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
package org.rippleosi.patient.documents.discharge.model;

import java.util.List;

/**
 */
public class DischargeDocumentDetails {
    
    private String sourceId;
    private String documentType;
    private String documentSource;
    private String documentDate;
    
    private String date_time;
    
    private String author_name;
    private String author_id;
    private String author_idScheme;
    
    private String facility;
    
    private String patientIdentifier_mrn;
    private String patientIdentifier_mrnType;
    private String patientIdentifier_oth;
    private String patientIdentifier_othType;
    private String patientIdentifier_gms;
    private String patientIdentifier_gmsType;
    
    private String dateOfAdmission;
    
    private String responseableProfessional_name;
    private String responseableProfessional_id;
    private String responseableProfessional_idType;
    
    private String dischargingOrganisation;
    
    private String dateTimeOfDischarge;
    
    List<ProblemDetails> diagnosisList;

    private String clinicalSynopsis;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentSource() {
        return documentSource;
    }

    public void setDocumentSource(String documentSource) {
        this.documentSource = documentSource;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_idScheme() {
        return author_idScheme;
    }

    public void setAuthor_idScheme(String author_idScheme) {
        this.author_idScheme = author_idScheme;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getPatientIdentifier_mrn() {
        return patientIdentifier_mrn;
    }

    public void setPatientIdentifier_mrn(String patientIdentifier_mrn) {
        this.patientIdentifier_mrn = patientIdentifier_mrn;
    }

    public String getPatientIdentifier_mrnType() {
        return patientIdentifier_mrnType;
    }

    public void setPatientIdentifier_mrnType(String patientIdentifier_mrnType) {
        this.patientIdentifier_mrnType = patientIdentifier_mrnType;
    }

    public String getPatientIdentifier_oth() {
        return patientIdentifier_oth;
    }

    public void setPatientIdentifier_oth(String patientIdentifier_oth) {
        this.patientIdentifier_oth = patientIdentifier_oth;
    }

    public String getPatientIdentifier_othType() {
        return patientIdentifier_othType;
    }

    public void setPatientIdentifier_othType(String patientIdentifier_othType) {
        this.patientIdentifier_othType = patientIdentifier_othType;
    }

    public String getPatientIdentifier_gms() {
        return patientIdentifier_gms;
    }

    public void setPatientIdentifier_gms(String patientIdentifier_gms) {
        this.patientIdentifier_gms = patientIdentifier_gms;
    }

    public String getPatientIdentifier_gmsType() {
        return patientIdentifier_gmsType;
    }

    public void setPatientIdentifier_gmsType(String patientIdentifier_gmsType) {
        this.patientIdentifier_gmsType = patientIdentifier_gmsType;
    }

    public String getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(String dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public String getResponseableProfessional_name() {
        return responseableProfessional_name;
    }

    public void setResponseableProfessional_name(String responseableProfessional_name) {
        this.responseableProfessional_name = responseableProfessional_name;
    }

    public String getResponseableProfessional_id() {
        return responseableProfessional_id;
    }

    public void setResponseableProfessional_id(String responseableProfessional_id) {
        this.responseableProfessional_id = responseableProfessional_id;
    }

    public String getResponseableProfessional_idType() {
        return responseableProfessional_idType;
    }

    public void setResponseableProfessional_idType(String responseableProfessional_idType) {
        this.responseableProfessional_idType = responseableProfessional_idType;
    }

    public String getDischargingOrganisation() {
        return dischargingOrganisation;
    }

    public void setDischargingOrganisation(String dischargingOrganisation) {
        this.dischargingOrganisation = dischargingOrganisation;
    }

    public String getDateTimeOfDischarge() {
        return dateTimeOfDischarge;
    }

    public void setDateTimeOfDischarge(String dateTimeOfDischarge) {
        this.dateTimeOfDischarge = dateTimeOfDischarge;
    }

    public List<ProblemDetails> getDiagnosisList() {
        return diagnosisList;
    }

    public void setDiagnosisList(List<ProblemDetails> diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

    public String getClinicalSynopsis() {
        return clinicalSynopsis;
    }

    public void setClinicalSynopsis(String clinicalSynopsis) {
        this.clinicalSynopsis = clinicalSynopsis;
    }

}
