/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitiies.ResponsablePrestation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zackarieabessoloekouma
 */
public class ResponsablePrestationDao implements IDao<ResponsablePrestation>{
    private final String SQL_FIND_BY_ID = "SELECT * FROM `responsable_prestation` r "
            + "INNER JOIN user u "
            + "ON r.id = u.id "
            + "WHERE r.id = ?";
    private DataBase dataBase = new DataBase();

    @Override
    public int insert(ResponsablePrestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(ResponsablePrestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ResponsablePrestation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponsablePrestation findById(int id) {
        ResponsablePrestation responsablePrestation = new ResponsablePrestation();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_FIND_BY_ID);
        try {
            dataBase.getPs().setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(ResponsablePrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ResultSet rs = dataBase.executeSelect(SQL_FIND_BY_ID);
        try {
            while (rs.next()) {
                responsablePrestation = new ResponsablePrestation(rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("role"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("libelle"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResponsablePrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return responsablePrestation;
        
    }
    
}
