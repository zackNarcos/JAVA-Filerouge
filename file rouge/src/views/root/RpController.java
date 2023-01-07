/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.root;

import entitiies.Medecin;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import views.ConnectionController;

/**
 * FXML Controller class
 *
 * @author zackarieabessoloekouma
 */
public class RpController implements Initializable {
    
    @FXML
    private Text txtShowNameUserOnline;
    @FXML
    private AnchorPane anchorContainer;
    

    
    private static RpController ctrl;
    
    private User user;
    @FXML
    private Button btnShowRdv;
    @FXML
    private Button btnNewRdv;
    @FXML
    private Button btnShowConsultation;
    @FXML
    private Button btnShowPrestation;

    public RpController() {
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
        
    }
    
    
    
    @FXML
    private void HandleLoadViewMedecin(ActionEvent e){
        try {
            loadView("v_medecin");
        } catch (IOException ex) {
            Logger.getLogger(RpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void HandleLoadViewPatient(ActionEvent e){
        try {
            loadView("v_patient");
        } catch (IOException ex) {
            Logger.getLogger(RpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void HandleLoadViewResponsablePrestation(ActionEvent e){
        try {
            loadView("v_responsablePrestation");
        } catch (IOException ex) {
            Logger.getLogger(RpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void HandleLoadViewSecretaire(ActionEvent e){
        try {
            loadView("v_secretaire");
        } catch (IOException ex) {
            Logger.getLogger(RpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void HandleLogOut(ActionEvent e){
        ConnectionController.getCtrl().logOut();
    }
    
    public void loadView(String view) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/root/"+view+".fxml"));
        anchorContainer.getChildren().clear();
        anchorContainer.getChildren().add(root);
    }
    
    public static RpController getCtrl() {
        return ctrl;
    }

    public User getUser() {
        return this.user;
    }
}
