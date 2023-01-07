/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.patient;

import entitiies.Prestation;
import entitiies.RendezVous;
import entitiies.Specialisation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import services.Service;
import utils.ViewService;
import views.ConnectionController;

/**
 * FXML Controller class
 *
 * @author zackarieabessoloekouma
 */
public class NewRendezVousController implements Initializable {
    
    ObservableList<Specialisation> obvSpecialisation;
    ObservableList<Prestation> ovbPrestation;
    Service service = new Service();
    @FXML
    private ComboBox<String> cboSpecialisation;
    @FXML
    private ComboBox<String> cboPrestation;
    @FXML
    private ComboBox<String> cboTypeRdv;
    @FXML
    private Label txtTypePrestation;
    @FXML 
    private Button btnAdd;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        cboSpecialisation.setVisible(false);
        txtTypePrestation.setVisible(false);
        cboPrestation.setVisible(false);
        loadCboBoxSpecialiisation(cboSpecialisation);
        loadCboBoxPrestation(cboPrestation);
        ViewService.loadComboBoxTypeRdv(cboTypeRdv);
        btnAdd.setDisable(true);
    }    
    
    
    public void loadCboBoxSpecialiisation(ComboBox<String> cbo){
     obvSpecialisation = FXCollections.observableArrayList(service.showSpecialisations());
        for (int i = 0; i < obvSpecialisation.size(); i++) {
            cbo.getItems().add(obvSpecialisation.get(i).getLibelle());
            cbo.getSelectionModel().selectFirst();
        }
    }
    
    public void loadCboBoxPrestation(ComboBox<String> cbo){
     ovbPrestation = FXCollections.observableArrayList(service.showPrestations());
        for (int i = 0; i < ovbPrestation.size(); i++) {
            cbo.getItems().add(ovbPrestation.get(i).getLibelle());
            cbo.getSelectionModel().selectFirst();
        }
    }
    
    @FXML
    private int handleCheckRdvType(ActionEvent event) {
        
        String typeSelected;
        typeSelected = cboTypeRdv.getSelectionModel().getSelectedItem();
        
        if (typeSelected.compareTo("Consultation") == 0) {
            cboSpecialisation.setVisible(true);
            txtTypePrestation.setVisible(false);
            cboPrestation.setVisible(false);
            btnAdd.setDisable(false);
        } else if (typeSelected.compareTo("Prestation") == 0) {
            cboSpecialisation.setVisible(false);
            txtTypePrestation.setVisible(true);
            cboPrestation.setVisible(true);
            btnAdd.setDisable(false);
        } else {
            cboSpecialisation.setVisible(false);
            txtTypePrestation.setVisible(false);
            cboPrestation.setVisible(false);
            btnAdd.setDisable(true);
        }
        
        return 1;
    }
            
    @FXML
    private int handAddRdv(ActionEvent event){ 
        RendezVous rdv = new RendezVous();
        
        String typeSelected;
        typeSelected = cboTypeRdv.getSelectionModel().getSelectedItem();
        
        if (typeSelected.compareTo("Consultation") == 0) {
            rdv.setTypeSpecialisation(cboSpecialisation.getSelectionModel().getSelectedItem());
        } else if (typeSelected.compareTo("Prestation") == 0) {
            rdv.setTypePrestation(cboPrestation.getSelectionModel().getSelectedItem());
        }
        
                
        rdv.setTypeRdv(cboTypeRdv.getSelectionModel().getSelectedItem());
        rdv.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));    
        rdv.setStatus("ATTENTE");
        rdv.setIdPatient(ConnectionController.getCtrl().getUser().getId());
        service.addRendezVous(rdv);
        
        try {
            PatientController.getCtrl().loadView("v_showRdv");
        } catch (IOException ex) {
            Logger.getLogger(NewRendezVousController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        
    }
}
