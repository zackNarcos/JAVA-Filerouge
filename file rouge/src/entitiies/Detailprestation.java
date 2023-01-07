/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiies;

import java.util.Date;

/**
 *
 * @author zackarieabessoloekouma
 */
public class Detailprestation {
    protected Integer id;
    protected ResponsablePrestation responsable;
    protected String prestation;
    protected String status;
    protected Patient patient;
    protected Date date;
    
    public Detailprestation() {
    }

    public Detailprestation(ResponsablePrestation responsable, String prestation,String status, Patient patient, Date date) {
        this.responsable = responsable;
        this.prestation = prestation;
        this.patient = patient;
        this.date = date;
        this.status = status;
    }

    public Detailprestation(Integer id, ResponsablePrestation responsable, String prestation,String status, Patient patient, Date date) {
        this.id = id;
        this.responsable = responsable;
        this.prestation = prestation;
        this.patient = patient;
        this.date = date;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ResponsablePrestation getResponsable() {
        return responsable;
    }

    public void setResponsable(ResponsablePrestation responsable) {
        this.responsable = responsable;
    }

    public String getPrestation() {
        return prestation;
    }

    public void setPrestation(String prestation) {
        this.prestation = prestation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
