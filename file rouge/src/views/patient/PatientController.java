/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.patient;

import entitiies.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import views.ConnectionController;

/**
 * FXML Controller class
 *
 * @author zackarieabessoloekouma
 */
public class PatientController implements Initializable {
    
    @FXML
    private Text txtShowNameUserOnline;

    @FXML
    private AnchorPane anchorContainer;
    
    private static PatientController ctrl;
    
    private User user;

    public PatientController() {
        this.user = ConnectionController.getCtrl().getUser();
    }


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtShowNameUserOnline.setText(ConnectionController.getCtrl().getUser().getPrenom());
        ctrl = this;
        try {
            loadView("v_showRdv");
        } catch (IOException ex) {
            Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       @FXML
    private void HandleLoadViewShowRdv(ActionEvent e){
        try {
            loadView("v_showRdv");
        } catch (IOException ex) {
            Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void HandleLoadViewShowPrestation(ActionEvent e){
        try {
            loadView("v_showPrestation");
        } catch (IOException ex) {
            Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void HandleLogOut(ActionEvent e){
        ConnectionController.getCtrl().logOut();
    }
    
    @FXML
    private void HandleLoadViewShowConsultation(ActionEvent e){
        try {
            loadView("v_showConsultation");
        } catch (IOException ex) {
            Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void HandleLoadViewNewRdv(ActionEvent e){
        try {
            loadView("v_newRdv");
        } catch (IOException ex) {
            Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void loadView(String view) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/patient/"+view+".fxml"));
        anchorContainer.getChildren().clear();
        anchorContainer.getChildren().add(root);
    }
    
    public static PatientController getCtrl() {
        return ctrl;
    }

    public User getUser() {
        return this.user;
    }
}
