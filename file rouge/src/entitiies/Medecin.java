/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiies;

import java.util.List;

/**
 *
 * @author zackarieabessoloekouma
 */
public class Medecin extends User{
    protected List<Specialisation> specialisations;
    protected Secretaire secretaire;
    protected String ROLE = "SECRETAIRE" ;

    public Medecin() {
        this.role = ROLE;
    }

    public Medecin(int id) {
        super(id);
    }

    
    public Medecin(String nom, String prenom, String login, String password, String role, Secretaire secretaire, List<Specialisation> specialisations) {
        super(nom, prenom, login, password, role);
        this.secretaire = secretaire;
        this.specialisations = specialisations;
    }

    public Medecin(int id, String nom, String prenom, String login, String password, String role, Secretaire secretaire, List<Specialisation> specialisations) {
        super(id, nom, prenom, login, password, role);
        this.secretaire = secretaire;
        this.specialisations = specialisations;
    }

    
    
    public List<Specialisation> getSpecialisations() {
        return specialisations;
    }

    public void setSpecialisations(List<Specialisation> specialisations) {
        this.specialisations = specialisations;
    }

    public Secretaire getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(Secretaire secretaire) {
        this.secretaire = secretaire;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }
    
}
