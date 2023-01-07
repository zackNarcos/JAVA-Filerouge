/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entitiies;

/**
 *
 * @author HP
 */
public class User {
    protected int id;
    protected String nom;
    protected String prenom;
    protected String login;
    protected String password;
    protected String role;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }
    
    //Update Etu,Prof
            public User(int id, String nom, String prenom) {
                this.id = id;
                this.nom = nom;
                this.prenom = prenom;
            }
            //Insert Etu,Prof
            public User(String nom, String prenom) {
                this.nom = nom;
                this.prenom = prenom;
            }
    
    
//Insert User(RP,AC)
    public User(String nom,String prenom, String login, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.role = role;
    }
//Update User(RP,AC)
    public User(int id, String nom,String prenom, String login, String password, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.role = role;
    }


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
    

}
