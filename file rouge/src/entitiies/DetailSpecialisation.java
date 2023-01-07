/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiies;

/**
 *
 * @author zackarieabessoloekouma
 */

public class DetailSpecialisation {
    protected int id;
    protected Medecin medecin;
    protected Specialisation specialisation;

    public DetailSpecialisation() {
    }

    public DetailSpecialisation(Medecin medecin, Specialisation specialisation) {
        this.medecin = medecin;
        this.specialisation = specialisation;
    }

    public DetailSpecialisation(int id, Medecin medecin, Specialisation specialisation) {
        this.id = id;
        this.medecin = medecin;
        this.specialisation = specialisation;
    }
    
    public DetailSpecialisation(int id, int medecinId,int SpecialisationId,String specialisationLibelle) {
        this.id = id;

        this.medecin = new Medecin(medecinId);
        this.specialisation = new Specialisation(SpecialisationId,specialisationLibelle);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Specialisation getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(Specialisation specialisation) {
        this.specialisation = specialisation;
    }

    
    
    
            
    
}
