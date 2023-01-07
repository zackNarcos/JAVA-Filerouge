/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitiies.Patient;
import entitiies.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zackarieabessoloekouma
 */
public class PatientDao implements IDao<Patient>{
    private final String SQL_INSERT = "INSERT INTO `patient` "
            + "(`id`, `code`, `id_antecedants`) "
            + "VALUES (?,?,?)";
    private final String SQL_FIND_BY_ID = "SELECT * FROM `patient` p "
            + "INNER JOIN user u "
            + "ON p.id = u.id "
            + "WHERE p.id = ?";
    
    private DataBase dataBase = new DataBase();
    private UserDao userDao = new UserDao();
    private AntecedantDao antecedantDao = new AntecedantDao();

    @Override
    public int insert(Patient patient) {
        int idPatient;

        idPatient = userDao.insertUser((User) patient);

        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setInt(1, idPatient);
            dataBase.getPs().setString(2, patient.getCode());
            dataBase.getPs().setInt(3, antecedantDao.createList());
            dataBase.executeUpdate(SQL_INSERT);
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dataBase.closeConnexion();
        }
        return idPatient;
    }

    @Override
    public int update(Patient ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patient> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Patient findById(int id) {
        Patient patient = new Patient();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_FIND_BY_ID);
        try {
            dataBase.getPs().setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = dataBase.executeSelect(SQL_FIND_BY_ID);
        try {
            while (rs.next()) {
                patient.setId(rs.getInt("id"));
                patient.setCode(rs.getString("code"));
                patient.setLogin(rs.getString("login"));
                patient.setPassword(rs.getString("password"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setRole(rs.getString("role"));
                //ajouter anteccednt
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return patient;
    }
    
    
    
}
