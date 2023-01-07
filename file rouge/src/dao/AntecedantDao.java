/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zackarieabessoloekouma
 */
public class AntecedantDao implements IDao{
    private DataBase dataBase = new DataBase();
    private final String SQL_INSERT = "INSERT INTO `antecedant` "
            + "(`id`) "
            + "VALUES (null)";

    public int createList() {
        int idAntecedant = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.executeUpdate(SQL_INSERT);
            ResultSet rs =dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idAntecedant = rs.getInt(1);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AntecedantDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();   
        } 
        return idAntecedant;
    }

    @Override
    public int update(Object ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(Object ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
