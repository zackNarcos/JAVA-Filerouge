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
public class RendezVous {
    
    protected Integer id;
    protected Integer idPatient;
    protected String status;
    protected String typeSpecialisation;
    protected String typeRdv;
    protected String typePrestation;
    protected Date date;
    protected Patient patient;

    public RendezVous() {
    }

    public RendezVous(Integer idPatient, String status, String typeSpecialisation, String typeRdv, String typePrestation, Date date) {
        this.idPatient = idPatient;
        this.status = status;
        this.typeSpecialisation = typeSpecialisation;
        this.typeRdv = typeRdv;
        this.typePrestation = typePrestation;
        this.date = date;
    }

    public RendezVous(Integer id, Integer idPatient, String status, String typeSpecialisation, String typeRdv, String typePrestation, Date date) {
        this.id = id;
        this.idPatient = idPatient;
        this.status = status;
        this.typeSpecialisation = typeSpecialisation;
        this.typeRdv = typeRdv;
        this.typePrestation = typePrestation;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeSpecialisation() {
        return typeSpecialisation;
    }

    public void setTypeSpecialisation(String typeSpecialisation) {
        this.typeSpecialisation = typeSpecialisation;
    }

    public String getTypeRdv() {
        return typeRdv;
    }

    public void setTypeRdv(String typeRdv) {
        this.typeRdv = typeRdv;
    }

    public String getTypePrestation() {
        return typePrestation;
    }

    public void setTypePrestation(String typePrestation) {
        this.typePrestation = typePrestation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    

}
