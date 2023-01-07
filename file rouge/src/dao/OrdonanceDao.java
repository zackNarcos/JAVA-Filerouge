/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitiies.DetailOrdonance;
import entitiies.Ordonance;
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
public class OrdonanceDao implements IDao<Ordonance>{

    private final String SQL_SELECT_BY_ID = "SELECT * "
            + "FROM `detail_ordonnace` "
            + "WHERE `id_ordonnance` = ?";
    DataBase dataBase = new DataBase();
    
    @Override
    public int insert(Ordonance ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Ordonance ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ordonance> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ordonance findById(int id) {
        List<DetailOrdonance> ordonances = new ArrayList<>();
        Ordonance ordonance = new Ordonance();
        ordonance.setId(id);
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_BY_ID);
        try {
            dataBase.getPs().setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(OrdonanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = dataBase.executeSelect(SQL_SELECT_BY_ID);
        try {
            while (rs.next()) {
                try {
                    DetailOrdonance detailOrdonance = new DetailOrdonance(
                            rs.getString("medicament"),
                            rs.getInt("duree"),
                            rs.getString("posologie"));
                    ordonances.add(detailOrdonance);
                } catch (SQLException ex) {
                    Logger.getLogger(OrdonanceDao.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdonanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                    ordonance.setMedicaments(ordonances);
        dataBase.closeConnexion();
        return ordonance;
    }
    
}
