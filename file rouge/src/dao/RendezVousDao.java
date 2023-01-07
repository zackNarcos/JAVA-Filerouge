/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitiies.RendezVous;
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
public class RendezVousDao implements IDao<RendezVous>{
    
//    
//    
//              remplacer l'idSecretaire dans le insert
//    
//    
    private final String SQL_INSERT = "INSERT "
            + "INTO `rendez_vous`(`date`, `id_patient`, `status`, `typeSpecialisation`, `typeRdv`, `typePrestation`) "
            + "VALUES (?,?,?,?,?,?)";
    
    private final String SQL_FIND_BY_ID_PATIENT = "SELECT * FROM `rendez_vous`"
            + "WHERE `id_patient`= ?";
    
    private final String SQL_CANCEL_RDV="UPDATE `rendez_vous` "
            + "SET `status`=? "
            + "WHERE `id`= ?";
    private String SQL_SELECT_RDV= "SELECT * FROM `rendez_vous` r "
            +"INNER JOIN user u "
            + "ON u.id=r.id_patient "
            +"INNER JOIN patient p "
            + "ON p.id=u.id "
            + " WHERE status='ATTENTE' ";
           
    
    private DataBase dataBase = new DataBase();
    private PatientDao patientDao=new PatientDao();
    @Override
    public int insert(RendezVous rdv) {
        int idRdv = 0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_INSERT);
        try {
            dataBase.getPs().setDate(1, new java.sql.Date(rdv.getDate().getTime()));
            dataBase.getPs().setInt(2, rdv.getIdPatient());
            dataBase.getPs().setString(3, rdv.getStatus());
            dataBase.getPs().setString(4, rdv.getTypeSpecialisation());
            dataBase.getPs().setString(5, rdv.getTypeRdv());
            dataBase.getPs().setString(6, rdv.getTypePrestation());
            dataBase.executeUpdate(SQL_INSERT);
            ResultSet rs = dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idRdv = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();
        }
        return idRdv;
    }

    public int cancelRdv(int id) {
         int nbrLigne=0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_CANCEL_RDV);
        try {
            dataBase.getPs().setString(1, "ANNULER");
            dataBase.getPs().setInt(2, id);
            nbrLigne=dataBase.executeUpdate(SQL_CANCEL_RDV);         
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataBase.closeConnexion();
        return nbrLigne;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RendezVous> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RendezVous findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<RendezVous> findByIdPatient(int id) throws SQLException {
        List<RendezVous> rdvs=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_FIND_BY_ID_PATIENT);
        try {
            dataBase.getPs().setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ResultSet rs =dataBase.executeSelect(SQL_FIND_BY_ID_PATIENT);
        
        while(rs.next()){
            try {        
                RendezVous rdv =new RendezVous(rs.getInt("id"), 
                        rs.getInt("id_patient"), 
                        rs.getString("status"),
                        rs.getString("typeSpecialisation"),
                        rs.getString("typeRdv"),
                        rs.getString("typePrestation"), 
                        rs.getDate("date"));
                rdvs.add(rdv);
            } catch (SQLException ex) {
                Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        dataBase.closeConnexion();
        return rdvs;

    }

    @Override
    public int update(RendezVous ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<RendezVous> findAllRdvEnAttente(){
        List<RendezVous> rdvs =new ArrayList();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_RDV);
        ResultSet rs= dataBase.executeSelect(SQL_SELECT_RDV);
        try {
            while (rs.next()){
                RendezVous rdv= new RendezVous();
                rdv.setId(rs.getInt("id"));
                rdv.setIdPatient(rs.getInt("id_patient"));
                rdv.setStatus(rs.getString("status"));
                rdv.setTypeSpecialisation(rs.getString("typeSpecialisation"));
                rdv.setTypeRdv(rs.getString("typeRdv"));
                rdv.setTypePrestation(rs.getString("typePrestation"));
                rdv.setDate(rs.getDate("date"));
                rdv.setPatient(patientDao.findById(rs.getInt("id_patient")));
                rdvs.add(rdv);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return rdvs;
    }
}  
        
        
    
    

