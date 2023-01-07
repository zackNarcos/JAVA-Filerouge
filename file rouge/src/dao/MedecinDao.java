/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitiies.DetailSpecialisation;
import entitiies.Medecin;
import entitiies.Secretaire;
import entitiies.User;
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
public class MedecinDao implements IDao<Medecin>{
    private final String SQL_INSERT = "INSERT INTO `medecin` "
            + "(`id`, `id_secretaire`) "
            + "VALUES (?,?)";
    private final String SQL_FIND_BY_ID = "SELECT * FROM `medecin` i "
            + "INNER JOIN user u "
            + "ON i.id = u.id "
            + "WHERE i.id = ?";
    private final String SQL_SELECT = "SELECT * FROM `medecin` m "
            + "INNER JOIN user u "
            + "ON m.id = u.id";
    private final String SQL_UPDATE= "UPDATE `medecin` "
            + "SET `id_secretaire` = ? "
            + "WHERE id = ?";
    
    private DataBase dataBase = new DataBase();
    private UserDao userDao = new UserDao();
    private SecretaireDao secretaireDao = new SecretaireDao();
    private DetailSpecialisationDAO detailSpecialisationDAO = new DetailSpecialisationDAO();

    @Override
    public int insert(Medecin medecin) {
        medecin.setROLE("ROLE_MEDECIN");
        int id = userDao.update((User)medecin);
        
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setInt(1, medecin.getSecretaire().getId());
            dataBase.getPs().setInt(2, medecin.getId());
            dataBase.executeUpdate(SQL_INSERT);
            

        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dataBase.closeConnexion();
        }
        return id;
    }

    @Override
    public int update(Medecin medecin) {

        int id = userDao.update((User)medecin);
        
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_UPDATE);
            dataBase.getPs().setInt(1, medecin.getSecretaire().getId());
            dataBase.getPs().setInt(2, medecin.getId());
            dataBase.executeUpdate(SQL_UPDATE);
            


        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dataBase.closeConnexion();
        }
        return id;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medecin> findAll() {
        List<Medecin> medecins = new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT);
        ResultSet rs = dataBase.executeSelect(SQL_SELECT);
        try {
            while(rs.next()){
                Medecin medecin = new Medecin();
                medecin.setId(rs.getInt("id"));
                medecin.setNom(rs.getString("nom"));
                medecin.setPrenom(rs.getString("prenom"));
                medecin.setLogin(rs.getString("login"));
                medecin.setPassword(rs.getString("password"));
                medecin.setRole(rs.getString("role"));
                medecin.setSpecialisations(detailSpecialisationDAO.findAllSpecilisationById(medecin.getId()));
                medecin.setSecretaire(secretaireDao.findById(rs.getInt("id_secretaire")));
                medecins.add(medecin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medecins;
    }

    @Override
    public Medecin findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
