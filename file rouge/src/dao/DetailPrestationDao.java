/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitiies.Detailprestation;
import entitiies.Patient;
import entitiies.Prestation;
import entitiies.ResponsablePrestation;
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
public class DetailPrestationDao implements IDao<Detailprestation>{
    private final String SQL_SELECT_BY_ID = "SELECT * FROM `detail_prestation` dp "
            + "INNER JOIN prestation pr "
            + "ON pr.id = dp.id_prestation "
            + "WHERE dp.id = ?";
    private final String SQL_SELECT_BY_ID_PATIENT = "SELECT * FROM `detail_prestation` dp "
            + "INNER JOIN prestation p "
            + "ON dp.id_prestation = p.id "
            + "WHERE dp.id_patient = ?";
    private final String SQL_CANCEL_PRESTATION= "UPDATE `detail_prestation` "
            + "SET `status`= ? "
            + "WHERE id = ?";
    private final String SQL_SELECT_PRESTATION_FOR_RESPONSABLE = "SELECT d.id, d.id_responsable, d.id_prestation, d.id_patient, d.status, d.date, u.nom, u.prenom, p.code , pp.libelle FROM `detail_prestation` d "
            + "INNER JOIN user u "
            + "ON d.id_patient = u.id "
            + "INNER JOIN patient p "
            + "ON u.id = p.id INNER "
            + "JOIN prestation pp "
            + "ON pp.id = d.id_prestation "
            + "WHERE d.status = 'EN ATTENTE'";
    DataBase dataBase = new DataBase();
    PatientDao patientDao = new PatientDao();
    ResponsablePrestationDao responsablePrestationDao = new ResponsablePrestationDao();


    @Override
    public int insert(Detailprestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Detailprestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Detailprestation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Detailprestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Detailprestation> findAllById(int id) {
        List<Detailprestation> detailprestations = new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_BY_ID);
        try {
            dataBase.getPs().setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(DetailPrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs =dataBase.executeSelect(SQL_SELECT_BY_ID);
        try {
            while (rs.next()) {
                ResponsablePrestation responsablePrestation = responsablePrestationDao.findById(rs.getInt("id_responsable"));
                Patient patient = patientDao.findById(rs.getInt("id_patient"));
                
                Detailprestation detailprestation = new Detailprestation(rs.getInt("id"),responsablePrestation,rs.getString("libelle"),rs.getString("status"),patient,rs.getDate("date"));
                detailprestations.add(detailprestation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailPrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detailprestations;
    }
    
    public List<Detailprestation> findByIdPatient(int id) {
        List<Detailprestation> detailprestations = new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_BY_ID_PATIENT);
        try {
            dataBase.getPs().setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(DetailPrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs =dataBase.executeSelect(SQL_SELECT_BY_ID_PATIENT);
        try {
            while (rs.next()) {
                ResponsablePrestation responsablePrestation = responsablePrestationDao.findById(rs.getInt("id_responsable"));
                Patient patient = patientDao.findById(rs.getInt("id_patient"));
                
                Detailprestation detailprestation = new Detailprestation(rs.getInt("id"),responsablePrestation,rs.getString("libelle"),rs.getString("status"),patient,rs.getDate("date"));
                detailprestations.add(detailprestation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailPrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detailprestations;
    }

    public int cancelPrestation(int id){
        int nbrLigne = 0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_CANCEL_PRESTATION);
        try {
            dataBase.getPs().setString(1, "ANNULER");
            dataBase.getPs().setInt(2, id);
            
            nbrLigne =dataBase.executeUpdate(SQL_CANCEL_PRESTATION);
        } catch (SQLException ex) {
            Logger.getLogger(DetailSpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbrLigne;
    }

    public List<Detailprestation> findAllForResponsable() {
        List<Detailprestation> detailprestations = new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_PRESTATION_FOR_RESPONSABLE);
        ResultSet rs =dataBase.executeSelect(SQL_SELECT_PRESTATION_FOR_RESPONSABLE);
        try {
            while (rs.next()) {
                System.out.println(rs.getInt("id_responsable"));
                ResponsablePrestation responsablePrestation = responsablePrestationDao.findById(rs.getInt("id_responsable"));
                Patient patient = patientDao.findById(rs.getInt("id_patient"));
                
                Detailprestation detailprestation = new Detailprestation(rs.getInt("id"),responsablePrestation,rs.getString("libelle"),rs.getString("status"),patient,rs.getDate("date"));
                detailprestations.add(detailprestation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailPrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detailprestations;
    }
    
}
