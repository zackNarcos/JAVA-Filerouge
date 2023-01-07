/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitiies.DetailSpecialisation;
import entitiies.Specialisation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zackarieabessoloekouma
 */
public class SpecialisationDAO implements IDao<Specialisation>{
    
    private final String SQL_SELECT = "SELECT * "
            + "FROM `specialisation` "
            + "WHERE 1";
    private final String SQL_FIND_ALL_BY_ID = "SELECT ds.id,s.libelle "
            + "FROM `detail_specialisation` ds "
            + "INNER JOIN specialisation s "
            + "ON s.id=ds.id_specialisation "
            + "WHERE ds.id_medecin = ?";
    
    private final String SQL_FIND_BY_LIBELLE = "SELECT * "
            + "FROM `specialisation` "
            + "WHERE `libelle`= ?";
    
//    private final String SQL_FIND_BY_LIBELLE = "SELECT * "
//            + "FROM `specialisation` "
//            + "WHERE `libelle`=?";
    
    private DataBase dataBase = new DataBase();

    @Override
    public int insert(Specialisation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Specialisation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Specialisation> findAll() {
        List<Specialisation> specialisations = new ArrayList<>(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT);
        ResultSet rs = dataBase.executeSelect(SQL_SELECT);
        
        try {
            while (rs.next()) {
                try {
                    Specialisation specialisation = new Specialisation(rs.getInt("id"), rs.getString("libelle"));
                    specialisations.add(specialisation);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(SpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataBase.closeConnexion();
        return specialisations;
        
        
    }
    
    public List<Specialisation> findAllById(int id) {
        List<Specialisation> specialisations = new ArrayList<>(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_FIND_ALL_BY_ID);
        try {
            dataBase.getPs().setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(SpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = dataBase.executeSelect(SQL_FIND_ALL_BY_ID);
        
        try {
            while (rs.next()) {
                try {
                    Specialisation specialisation = new Specialisation(rs.getInt("id"),rs.getString("libelle"));
                    specialisations.add(specialisation);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(SpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataBase.closeConnexion();
        return specialisations;
    }

    @Override
    public Specialisation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Specialisation findByLibelle(String libelle) {
        Specialisation specialisation = new Specialisation();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_FIND_BY_LIBELLE);
        try {
            dataBase.getPs().setString(1,libelle);
        } catch (SQLException ex) {
            Logger.getLogger(SpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = dataBase.executeSelect(SQL_FIND_BY_LIBELLE);
        
        try {
            while (rs.next()) {
                specialisation = new Specialisation(rs.getInt("id"), rs.getString("libelle"));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return specialisation;
    }
    
}
