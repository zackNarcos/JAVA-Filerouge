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
public class Prestation {
    protected Integer id;
    protected String libelle;

    public Prestation() {
    }

    public Prestation(String libelle) {
        this.libelle = libelle;
    }

    public Prestation(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
}
