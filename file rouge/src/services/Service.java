/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package services;
import dao.ConsultationDao;
import dao.DetailPrestationDao;
import dao.DetailSpecialisationDAO;
import dao.MedecinDao;
import dao.OrdonanceDao;
import dao.PrestationDao;
import dao.RendezVousDao;
import dao.SecretaireDao;
import dao.SpecialisationDAO;
import dao.UserDao;
import entitiies.Consultation;
import entitiies.DetailSpecialisation;
import entitiies.Detailprestation;
import entitiies.Medecin;
import entitiies.Ordonance;
import entitiies.Prestation;
import entitiies.RendezVous;
import entitiies.Secretaire;
import entitiies.Specialisation;
import entitiies.User;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import views.ConnectionController;

/**
 *
 * @author HP
 */
public class Service implements IService {
    //Dependances avec la couche DAO

    UserDao daoUser=new UserDao();
    OrdonanceDao daoOrdonance = new OrdonanceDao();
    RendezVousDao daoRdv = new RendezVousDao();
    SpecialisationDAO daoSpecialisation = new SpecialisationDAO();
    DetailSpecialisationDAO daoDetailSpecialisation = new DetailSpecialisationDAO();
    PrestationDao daoPrestation = new PrestationDao();
    ConsultationDao daoConsultation = new ConsultationDao();
    DetailPrestationDao daoDetailPrestation = new DetailPrestationDao();
    MedecinDao doaMedecin = new MedecinDao();
    SecretaireDao doaSecretaire = new SecretaireDao();



    @Override
    public User login(String login, String password) {
        return daoUser.findUserLoginAndPassword(login, password);    
    }
    
    @Override
    public List<RendezVous> showRendezVousPatient() {
        try {
            return daoRdv.findByIdPatient(ConnectionController.getCtrl().getUser().getId());
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int addRendezVous(RendezVous rdv) {
        return daoRdv.insert(rdv);
    }

    @Override
    public int updateRendezVousPatient(Integer id) {
        return daoRdv.cancelRdv(id);
    }

    @Override
    public List<Specialisation> showSpecialisations() {
        return daoSpecialisation.findAll();
    }

    @Override
    public int addSpecialisation(Specialisation specialisation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeSpecialisation(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateSpecialisation(Specialisation specialisation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestation> showPrestations() {
        return daoPrestation.findAll();
    }

    @Override
    public User findUseryId(int id) {
        return daoUser.findById(id);
    }

    @Override
    public Ordonance findOrdonanceById(int id) {
        return daoOrdonance.findById(id);
    }

    @Override
    public List<Consultation> findConsuById() {
        return daoConsultation.findConsuById();
    }

    @Override
    public List<Detailprestation> showDetailPrestations(int id) {
        return daoDetailPrestation.findAllById(id);
    }

    @Override
    public int cancelConsultation(Consultation consultation) {
        return daoConsultation.update(consultation);
    }

    @Override
    public List<Detailprestation> showDetailPrestationsByIdPatient(int id) {
        return daoDetailPrestation.findByIdPatient(id);
    }

    @Override
    public int cancelPrestattion(int id) {
        return daoDetailPrestation.cancelPrestation(id);
    }

    @Override
    public List<Medecin> findAllMedecins() {
        return doaMedecin.findAll();
    }

    @Override
    public List<Secretaire> findAllSecretaires() {
        return doaSecretaire.findAll();
    }

    @Override
    public Secretaire findSecretaireById(int id) {
        return doaSecretaire.findById(id);
    }

    @Override
    public List<Specialisation> showAllDetaillSpecialisations(int id) {
        return daoSpecialisation.findAllById(id);
    }

    @Override
    public int addDetailSpecialisation(DetailSpecialisation detailSpecialisation) {
        return daoDetailSpecialisation.insert(detailSpecialisation);
    }

    @Override
    public Specialisation findSpecialisationByLibelle(String libelle) {
        return daoSpecialisation.findByLibelle(libelle);
    }

    @Override
    public int removeDetailSpecialisation(int id) {
        return daoDetailSpecialisation.delete(id);
    }
  
    @Override
    public int insertMedecin(Medecin medecin) {
       return doaMedecin.insert(medecin);
    }
      

    @Override
    public List<Detailprestation> showPrestationRResponsable() {
      return daoDetailPrestation.findAllForResponsable();
    }

    @Override
    public int annulerPrestation(int id) {
        return daoPrestation.updateAnnulerPrestation(id);
    }
        
    

    @Override
    public int validerPrestation(int id) {
        return daoPrestation.updateValidatePrestation(id);
    }


    @Override
    public List<RendezVous> showRdvEnAttente() {
        return daoRdv.findAllRdvEnAttente();
    }

    @Override
    public int updateMedecin(Medecin medecin) {
        return doaMedecin.update(medecin);
    }

    @Override
    public int deleteMedecin(int id_medecin) {
        return doaMedecin.delete(id_medecin);
    }
        
    
     
    

}
