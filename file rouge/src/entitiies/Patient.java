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
public class Patient extends User{
    private final String ROLE="ROLE_Patient";
    private String code;
    
    public Patient(){
        this.role = ROLE;
    }

    //Insert 
    public Patient(String nom,String prenom, String login, String password, String role,String code) {
        super(nom,prenom,login,password,role);
        this.code = code;
    }
    //Update
    public Patient(int id, String nom,String prenom, String login, String password, String role,String code) {
        super(id,nom,prenom,login,password,role);
        this.code = code;
    }

    public String getROLE() {
        return ROLE;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
