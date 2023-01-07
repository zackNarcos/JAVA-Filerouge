/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package services;

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
import java.util.List;

/**
 *
 * @author HP
 */
public interface IService {
    /*
    Ajout,modification,suppression boolean
        true ajout correct
        false Erreur
    */
//RP    
 
      
      
/*Se connecter */
    public User login(String login,String password);
    
    
//    User
    public User findUseryId(int id);
      
    
// gerer Rendez Vous
    public List<RendezVous> showRendezVousPatient();
    public int addRendezVous(RendezVous rdv);
    public int updateRendezVousPatient(Integer id);
    public List<RendezVous> showRdvEnAttente();
//gerer specialisation 
    public List<Specialisation> showAllDetaillSpecialisations(int id);
    public List<Specialisation> showSpecialisations();
    public int addSpecialisation(Specialisation specialisation);
    public int addDetailSpecialisation(DetailSpecialisation detailSpecialisation);
    public int removeSpecialisation(int id);
    public int removeDetailSpecialisation(int id);
    public int updateSpecialisation(Specialisation specialisation);
    public Specialisation findSpecialisationByLibelle(String libelle);

// gere prestation
    public int cancelPrestattion(int id);
    public List<Prestation> showPrestations();
    public List<Detailprestation> showDetailPrestations(int id);
    public List<Detailprestation> showDetailPrestationsByIdPatient(int id);
    public List<Detailprestation> showPrestationRResponsable();
    public int annulerPrestation(int id);
    public int validerPrestation(int id);
    
//    ordonance
    public Ordonance findOrdonanceById(int id);
    
//    Consultation
    public List<Consultation> findConsuById();
    public int cancelConsultation(Consultation consultation);

//  Medecin
    public List<Medecin> findAllMedecins();
    public int insertMedecin(Medecin medecin);
    public int updateMedecin(Medecin medecin);
    public int deleteMedecin(int id_medecin);
    
    
//    Secretaire
    public List<Secretaire> findAllSecretaires();
    public Secretaire findSecretaireById(int id);
    
    
}
