/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiies;

import javax.management.relation.Role;

/**
 *
 * @author zackarieabessoloekouma
 */
public class Secretaire extends User{
    protected String ROLE = "SECRETAIRE" ;

    public Secretaire() {
        this.role = ROLE;
    }

    public Secretaire(String nom, String prenom, String login, String password, String role) {
        super(nom, prenom, login, password, role);
    }

    public Secretaire(int id, String nom, String prenom, String login, String password, String role) {
        super(id, nom, prenom, login, password, role);
    }
    
}
