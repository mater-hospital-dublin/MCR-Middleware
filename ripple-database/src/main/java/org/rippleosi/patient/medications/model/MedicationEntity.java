package org.rippleosi.patient.medications.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medications")
public class MedicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id")
    private String nhsNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "dose_amount")
    private String doseAmount;

    @Column(name = "dose_directions")
    private String doseDirections;

    @Column(name = "dose_timing")
    private String doseTiming;

    @Column(name = "route")
    private String route;

    @Column(name = "start_date")
    private Date startDatetime;

    @Column(name = "terminology")
    private String terminology;

    @Column(name = "terminology_code")
    private String terminologyCode;

    @Column(name = "author")
    private String author;

    @Column(name = "date_created")
    private Date dateCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public void setNhsNumber(String nhsNumber) {
        this.nhsNumber = nhsNumber;
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

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getTerminology() {
        return terminology;
    }

    public void setTerminology(String terminology) {
        this.terminology = terminology;
    }

    public String getTerminologyCode() {
        return terminologyCode;
    }

    public void setTerminologyCode(String terminologyCode) {
        this.terminologyCode = terminologyCode;
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
}
