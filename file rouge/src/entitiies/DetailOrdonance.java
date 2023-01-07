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
public class DetailOrdonance {
    protected int id;
    protected String nomMedicament;
    protected int duree;
    protected String posologie;

    public DetailOrdonance() {
    }

    public DetailOrdonance(String nomMedicament, int duree, String posologie) {
        this.nomMedicament = nomMedicament;
        this.duree = duree;
        this.posologie = posologie;
    }

    public DetailOrdonance(int id, String nomMedicament, int duree, String posologie) {
        this.id = id;
        this.nomMedicament = nomMedicament;
        this.duree = duree;
        this.posologie = posologie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomMedicament() {
        return nomMedicament;
    }

    public void setNomMedicament(String nomMedicament) {
        this.nomMedicament = nomMedicament;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getPosologie() {
        return posologie;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }
    
    
}
