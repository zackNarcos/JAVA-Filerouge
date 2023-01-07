/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import entitiies.Patient;
import entitiies.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author HP
 */
public class UserDao implements IDao{

    private final DataBase dataBase= new DataBase();
    private final String SQL_LOGIN = "SELECT * FROM user "
            + "WHERE login =  ? AND password = ? ";
    
    private final String SQL_FIND_BY_ID = "SELECT * FROM user "
            + "WHERE id =  ?";
    private final String SQL_INSERT_USER = "INSERT INTO `user` "
            + "(`id`, `nom`, `prenom`, `role`, `login`, `password`) "
            + "VALUES (NULL, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE `user` "
            + "SET `nom`=?,`prenom`=?,`role`=?,`login`=?,`password`=? "
            + "WHERE id = ?";

    public int insertUser(User user) {
        int id = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT_USER);
            dataBase.getPs().setString(1, user.getNom());
            dataBase.getPs().setString(2, user.getPrenom());
            dataBase.getPs().setString(3, user.getRole());
            dataBase.getPs().setString(4, user.getLogin());
            dataBase.getPs().setString(5, user.getPassword());
            dataBase.executeUpdate(SQL_INSERT_USER);
            ResultSet rs = dataBase.getPs().getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dataBase.closeConnexion();
        }
        return id;
    }

    public int update(User user) {
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_UPDATE);
            dataBase.getPs().setString(1, user.getNom());
            dataBase.getPs().setString(2, user.getPrenom());
            dataBase.getPs().setString(3, user.getRole());
            dataBase.getPs().setString(4, user.getLogin());
            dataBase.getPs().setString(5, user.getPassword());
            dataBase.getPs().setInt(6, user.getId());
            dataBase.executeUpdate(SQL_UPDATE);


            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dataBase.closeConnexion();
        }
        return 1;
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
    public User findById(int id) {
        User user = new User();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_FIND_BY_ID);
        
        try {
            dataBase.getPs().setInt(1, id);
            ResultSet rs = dataBase.executeSelect(SQL_LOGIN);
        
            if(rs.next())
            {
                user = new User(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("role") 
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    
    public User findUserLoginAndPassword(String login,String password){
        User user = null;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_LOGIN);
        
        try {
            dataBase.getPs().setString(1, login);
            dataBase.getPs().setString(2, password);
            ResultSet rs = dataBase.executeSelect(SQL_LOGIN);
        
            if(rs.next())
            {
                user = new User(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("role") 
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public int insert(Object ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Object ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}
