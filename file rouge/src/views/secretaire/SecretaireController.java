/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.secretaire;

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
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author zackarieabessoloekouma
 */
public class SecretaireController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchorContainer;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadView("v_rendez_vous");
        } catch (IOException ex) {
            Logger.getLogger(SecretaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void loadView(String view) throws IOException{
        Pane root;
        root = FXMLLoader.load(getClass().getResource("/views/secretaire/"+view+".fxml"));
        anchorContainer.getChildren().clear();
        anchorContainer.getChildren().add(root);
        
    }
    
    @FXML
    public void HandleDisponibilite(ActionEvent event){
        try {
            loadView("v_disponibilite");
        } catch (IOException ex) {
            Logger.getLogger(SecretaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void HandleRdv(ActionEvent event){
        try {
            loadView("v_rendez_vous");
        } catch (IOException ex) {
            Logger.getLogger(SecretaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
