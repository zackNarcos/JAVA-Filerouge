/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitiies.Consultation;
import entitiies.Ordonance;
import entitiies.Prestation;
import entitiies.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.Service;
import views.ConnectionController;

/**
 *
 * @author zackarieabessoloekouma
 */
public class ConsultationDao implements IDao<Consultation>{
    
    private String SQL_SELECT = "SELECT c.id, c.date, c.temperature, c.tension, c.poids, c.status, c.id_ordonance, c.id_prestations, c.id_medecin "
            + "FROM consultation c "
            + "INNER JOIN medecin m "
            + "ON c.id_medecin = m.id "
            + "INNER JOIN user u "
            + "ON u.id = m.id "
            + "INNER JOIN prestation p "
            + "ON c.id_prestations = p.id "
            + "WHERE c.id_patient = ? ";
    private String SQL_SELECT_ALL = "SELECT c.id, c.date, c.temperature, c.tension, c.poids, c.status, c.id_ordonance, c.id_prestations, c.id_medecin "
            + "FROM consultation c "
            + "INNER JOIN medecin m "
            + "ON c.id_medecin = m.id "
            + "INNER JOIN user u "
            + "ON u.id = m.id "
            + "INNER JOIN prestation p "
            + "ON c.id_prestations = p.id "
            + "WHERE c.id_patient = ? ";
    
    private String SQL_UPDATE = "UPDATE `consultation` "
            + "SET `status`= ? "
            + "WHERE `id`= ? ";
    
    private DataBase dataBase = new DataBase();
//    Service service = new Service();
    UserDao daoUser=new UserDao();
    OrdonanceDao daoOrdonance = new OrdonanceDao();

    @Override
    public int insert(Consultation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Consultation ogj) {
        int nbrLine = 0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_UPDATE);
        try {
            dataBase.getPs().setString(1, "ANNULER");
            dataBase.getPs().setInt(2, ogj.getId());
            nbrLine = dataBase.executeUpdate(SQL_UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return nbrLine;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public List<Consultation> findConsuById() {
        List<Consultation> consultations = new ArrayList<>();
        
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT);
        try {
            dataBase.getPs().setInt(1, ConnectionController.getCtrl().getUser().getId());
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ResultSet rs = dataBase.executeSelect(SQL_SELECT);
        try {
            while (rs.next()) {
                User patient = ConnectionController.getCtrl().getUser();
//                
//                a faire, remplacer User.medecin par Medecin.medecin
//                
                User medecin = daoUser.findById(rs.getInt("id_medecin"));
                Ordonance ordonance = daoOrdonance.findById(rs.getInt("id_ordonance"));
                List<Prestation> prestations;
                
                        
                                                        
                Consultation consultation = new Consultation();
                
                consultation.setId(rs.getInt("id"));
                consultation.setDate(rs.getDate("date"));
                consultation.setTemperature(rs.getInt("temperature"));
                consultation.setTension(rs.getInt("tension"));
                consultation.setPoids(rs.getInt("poids"));
                consultation.setPatient(patient);
                // liste prestation
                consultation.setMedecin(medecin);
                consultation.setOrdonance(ordonance);
                consultation.setStatus(rs.getString("status"));
                
                consultations.add(consultation);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return consultations;
    }

    @Override
    public Consultation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultation> findAll() {
        List<Consultation> consultations = new ArrayList<>();
        
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT);
        try {
            dataBase.getPs().setInt(1, ConnectionController.getCtrl().getUser().getId());
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ResultSet rs = dataBase.executeSelect(SQL_SELECT);
        try {
            while (rs.next()) {
                User patient = ConnectionController.getCtrl().getUser();
//                
//                a faire, remplacer User.medecin par Medecin.medecin
//                
                User medecin = daoUser.findById(rs.getInt("id_medecin"));
                Ordonance ordonance = daoOrdonance.findById(rs.getInt("id_ordonance"));
                List<Prestation> prestations;
                
                        
                                                        
                Consultation consultation = new Consultation();
                
                consultation.setId(rs.getInt("id"));
                consultation.setDate(rs.getDate("date"));
                consultation.setTemperature(rs.getInt("temperature"));
                consultation.setTension(rs.getInt("tension"));
                consultation.setPoids(rs.getInt("poids"));
                consultation.setPatient(patient);
                // liste prestation
                consultation.setMedecin(medecin);
                consultation.setOrdonance(ordonance);
                consultation.setStatus(rs.getString("status"));
                
                consultations.add(consultation);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return consultations;
    }
    
}
