/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiies;

import java.util.Date;
import java.util.List;

/**
 *
 * @author zackarieabessoloekouma
 */
public class Consultation {
    protected Integer id;
    protected Date date;
    protected Integer temperature;
    protected Integer tension;
    protected Integer poids;
    protected User patient;
    protected List<Prestation> prestations;
    protected User medecin;
    protected Ordonance ordonance;
    protected String Status;

    public Consultation() {
    }

    public Consultation(Date date, Integer temperature, Integer tension, Integer poids, User patient, List<Prestation> prestations, User medecin, Ordonance ordonance, String Status) {
        this.date = date;
        this.temperature = temperature;
        this.tension = tension;
        this.poids = poids;
        this.patient = patient;
        this.prestations = prestations;
        this.medecin = medecin;
        this.ordonance = ordonance;
        this.Status = Status;
    }

    public Consultation(Integer id, Date date, Integer temperature, Integer tension, Integer poids, User patient, List<Prestation> prestations, User medecin, Ordonance ordonance, String Status) {
        this.id = id;
        this.date = date;
        this.temperature = temperature;
        this.tension = tension;
        this.poids = poids;
        this.patient = patient;
        this.prestations = prestations;
        this.medecin = medecin;
        this.ordonance = ordonance;
        this.Status = Status;
    }

    public Consultation(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getTension() {
        return tension;
    }

    public void setTension(Integer tension) {
        this.tension = tension;
    }

    public Integer getPoids() {
        return poids;
    }

    public void setPoids(Integer poids) {
        this.poids = poids;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public List<Prestation> getPrestations() {
        return prestations;
    }

    public void setPrestations(List<Prestation> prestations) {
        this.prestations = prestations;
    }

    public User getMedecin() {
        return medecin;
    }

    public void setMedecin(User medecin) {
        this.medecin = medecin;
    }

    public Ordonance getOrdonance() {
        return ordonance;
    }

    public void setOrdonance(Ordonance ordonance) {
        this.ordonance = ordonance;
    }

       

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
    
}
