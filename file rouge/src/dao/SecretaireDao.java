/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitiies.Secretaire;
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
public class SecretaireDao implements IDao<Secretaire>{
    private final String SQL_FIND_BY_ID = "SELECT * "
            + "FROM `secretaire` s "
            + "INNER JOIN user u "
            + "ON s.id = u.id "
            + "WHERE s.id =  ?";
    private final String SQL_SELECT_ALL = "SELECT * "
            + "FROM `secretaire` s "
            + "INNER JOIN user u "
            + "ON u.id = s.id "
            + "WHERE 1";
    private DataBase dataBase = new DataBase();

    @Override
    public int insert(Secretaire ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Secretaire ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Secretaire> findAll() {
        List<Secretaire> secretaires = new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_ALL);
        ResultSet rs = dataBase.executeSelect(SQL_SELECT_ALL);
        try {
            while (rs.next()) {
                try {
                    Secretaire secretaire = new Secretaire(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("login"),rs.getString("password"),rs.getString("role"));
                    secretaires.add(secretaire);
                } catch (SQLException ex) {
                    Logger.getLogger(SecretaireDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SecretaireDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return secretaires;
    }

    @Override
    public Secretaire findById(int id) {
        Secretaire secretaire = new Secretaire();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_FIND_BY_ID);
        try {
            dataBase.getPs().setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(SecretaireDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = dataBase.executeSelect(SQL_FIND_BY_ID);
        try {
            while (rs.next()) {
                try {
                    secretaire = new Secretaire(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("login"),rs.getString("password"),rs.getString("role"));
                } catch (SQLException ex) {
                    Logger.getLogger(SecretaireDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SecretaireDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return secretaire;
    }
    
    
}
