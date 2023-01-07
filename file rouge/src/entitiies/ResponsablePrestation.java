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
public class ResponsablePrestation extends User{
    private final String ROLE="ROLE_RESPONSABLE_PRESTATION";
    protected String libelle;
    

    public ResponsablePrestation() {
        this.role = ROLE;
    }

    public ResponsablePrestation(String nom,String prenom, String login, String password, String role,String libelle) {
        super(nom,prenom,login,password,role);
        this.libelle = libelle;
    }

    
    public ResponsablePrestation(Integer id,String nom,String prenom, String login, String password, String role,String libelle) {
        super(id,nom,prenom,login,password,role);
        this.libelle = libelle;
    }


    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
    
}
