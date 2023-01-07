/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitiies.Prestation;
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
public class PrestationDao implements IDao<Prestation>{
    
    private final String SQL_SELECT = "SELECT * "
            + "FROM `prestation` "
            + "WHERE 1";
    
    private final String SQL_UPDATE_PRESTATION = "UPDATE `detail_prestation` SET `status`= ? WHERE `id`=?";
    
    private DataBase dataBase = new DataBase();

    @Override
    public int insert(Prestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Prestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestation> findAll() {
        
        List<Prestation> prestations = new ArrayList<>(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT);
        ResultSet rs = dataBase.executeSelect(SQL_SELECT);
        
        try {
            while (rs.next()) {
                try {
                    Prestation prestation = new Prestation(rs.getInt("id"), rs.getString("libelle"));
                    prestations.add(prestation);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(SpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataBase.closeConnexion();
        return prestations;
    }

    @Override
    public Prestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    public int updateValidatePrestation(int id) {
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_UPDATE_PRESTATION);
        try {
            dataBase.getPs().setString(1, "CONFIRME");
            dataBase.getPs().setInt(2, id);
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
         int rs = dataBase.executeUpdate(SQL_UPDATE_PRESTATION);
         dataBase.closeConnexion();
            return rs;        
       
    }
    
   
    
            
            

    public int updateAnnulerPrestation(int id) {
        System.out.println("dao.PrestationDao.updateAnnulerPrestation()");
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_UPDATE_PRESTATION);
        
        try {
            dataBase.getPs().setString(1, "ANNULE");
            dataBase.getPs().setInt(2, id);
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         int rs = dataBase.executeUpdate(SQL_UPDATE_PRESTATION);
         dataBase.closeConnexion();
            return rs;        
       
    }
    
}
