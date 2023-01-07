/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

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
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Service;

/**
 * FXML Controller class
 *
 * @author zackarieabessoloekouma
 */
public class ConnectionController implements Initializable {

    @FXML
    private Text txtError;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtPassword;
    
    private final Service service = new Service();
    
    private static ConnectionController ctrl;
    
    private User user;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtError.setVisible(false);
        ctrl = this;
    }    
    
    @FXML
    private void HandleConnexion(ActionEvent event){
        String login = txtLogin.getText().trim();
        String password = txtPassword.getText().trim();
        if(login.isEmpty() || password.isEmpty())
        {
          txtError.setText("login ou le mot de passe Obligatoire");
          txtError.setVisible(true);
        }
        else{
          user = service.login(login, password);
          if(user == null)
          {
               txtError.setText("login ou le mot de passe Incorrect");
               txtError.setVisible(true);
          }
          else
          {
              
              //Cache la fénétre de connexion
              this.txtError.getScene().getWindow().hide();
              AnchorPane root = null;
              
              try {
                  switch (user.getRole()) {
                      case "ROOT":
                          root = FXMLLoader.load(getClass().getResource("/views/root/v_Rp.fxml"));
                          break;
                      case "ROLE_PATIENT":
                          root = FXMLLoader.load(getClass().getResource("/views/patient/v_patient.fxml"));
                          break;
                      case "ROLE_MEDECIN":
                          root = FXMLLoader.load(getClass().getResource("/views/medecin/v_showConsultation.fxml"));
                          break;
                      case "ROLE_SECRETAIRE":
                          root = FXMLLoader.load(getClass().getResource("/views/secretaire/v_secretaire.fxml"));
                          break;
                      case "ROLE_RESPONSABLE":
                          root = FXMLLoader.load(getClass().getResource("/views/responsablePrestation/v_responsable.fxml"));
                          break;
                  
                      default:
                          root = FXMLLoader.load(getClass().getResource("/views/v_connection.fxml"));

                  }
                  Scene scene = new Scene(root);
                  Stage stage = new Stage();
                  stage.setScene(scene);
                  stage.show();
              } catch (IOException ex) {
                  Logger.getLogger(ConnectionController.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }
    }
    
    public void logOut(){
        ctrl.user = null;
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/v_connection.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static ConnectionController getCtrl() {
        return ctrl;
    }

    public User getUser() {
        return user;
    }
    
}
