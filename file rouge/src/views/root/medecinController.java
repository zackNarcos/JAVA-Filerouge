/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.root;

import entitiies.DetailSpecialisation;
import entitiies.Medecin;
import entitiies.Secretaire;
import entitiies.Specialisation;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import services.Service;

/**
 * FXML Controller class
 *
 * @author zackarieabessoloekouma
 */
public class medecinController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Specialisation> tblvSpecialisation;
    @FXML
    private TableColumn<Specialisation,String> tblcLibelle;
    @FXML
    private TableView<Medecin> tblvMedecin;
    @FXML
    private TableColumn<Medecin,String> tblcNom;
    @FXML
    private TableColumn<Medecin,String> tblcPrenom;
    
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtSecretaire;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnRemoveSpe;
    @FXML
    private ComboBox<String> cboSpecialisations;
    @FXML
    private ComboBox<String> cboSecretaire;
    
    Service service= new Service();
    
    ObservableList<Medecin> ovbmedecins;
    ObservableList<Secretaire> ovbSecretaires;
    ObservableList<Specialisation> obvSpecialisation;
    ObservableList<DetailSpecialisation> obvDetailSpecialisation;
    
    private List<Medecin> medecins;

    private List<Specialisation> tbListspecialisations;
    private Medecin medecinSelected = null;
    private Specialisation specialisationSelected;
    private Specialisation specialisaTblvtionSelected;
    @FXML
    private Pane specialityPane;
    @FXML
    private TableColumn<?, ?> tblcCode;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableView();
        loadCboBoxSecretaire(cboSecretaire);
        loadCboBoxSpecialiisation(cboSpecialisations); //ajouter le code stream 
        specialityPane.setVisible(false);
    }    
    
    private void loadTableView(){
        medecins = service.findAllMedecins();
        ovbmedecins = FXCollections.observableArrayList(medecins);
        tblcNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tblcPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tblvMedecin.setItems(ovbmedecins);
    }
    
    private void loadTableViewSpecialisation(int id){
        tbListspecialisations  = service.showAllDetaillSpecialisations(id);
        obvSpecialisation = FXCollections.observableArrayList(tbListspecialisations);
//        tblc.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblvSpecialisation.setItems(obvSpecialisation);
    }
    
    @FXML
    private void handleSelectRdv(MouseEvent event) {
        medecinSelected = tblvMedecin.getSelectionModel().getSelectedItem();
        txtNom.setText(medecinSelected.getNom());
        txtPrenom.setText(medecinSelected.getPrenom());
        txtSecretaire.setText(medecinSelected.getSecretaire().getNom()+" "+medecinSelected.getSecretaire().getPrenom());
        txtLogin.setText(medecinSelected.getLogin());
        txtPassword.setText(medecinSelected.getPassword());
        loadTableViewSpecialisation(medecinSelected.getId());
        specialityPane.setVisible(true);
    }

    @FXML
    private void handleSelectSpe(MouseEvent event) {
        btnRemoveSpe.setVisible(true);
        specialisaTblvtionSelected = tblvSpecialisation.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    private void handleAddSpecialisation(ActionEvent event) {
        if (medecinSelected != null) {
            specialisationSelected = service.findSpecialisationByLibelle(cboSpecialisations.getSelectionModel().getSelectedItem());
            DetailSpecialisation detailSpecialisation = new DetailSpecialisation(medecinSelected,specialisationSelected);
            service.addDetailSpecialisation(detailSpecialisation);
            loadTableViewSpecialisation(medecinSelected.getId());
        } else {
            
        }
    }
    
    @FXML
    private void handleAddSecretaire(ActionEvent event) {
        txtSecretaire.setText(cboSecretaire.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private void handleremoveSecretaire(ActionEvent event) {
        txtSecretaire.clear();
    }
    
    @FXML
    private void handleAddMedecin(ActionEvent event) {
        Medecin medecin = new Medecin(
                txtNom.getText(),
                txtPrenom.getText(),
                txtLogin.getText(),
                txtPassword.getText(),
                "ROLE_MEDECIN",
                ovbSecretaires.get(cboSecretaire.getSelectionModel().getSelectedIndex()),
                tbListspecialisations
        );
        System.out.println("views.root.medecinController.handleAddMedecin()");
        service.insertMedecin(medecin);
        clear();
        loadTableView();
        
    }
    
    
    @FXML
    private void handleRemoveSpecialisation(ActionEvent event) {
        
        service.removeDetailSpecialisation(specialisaTblvtionSelected.getId());
        System.out.println(specialisaTblvtionSelected.getId());
        loadTableViewSpecialisation(medecinSelected.getId());
    }
    
    public void loadCboBoxSecretaire(ComboBox<String> cbo){
     ovbSecretaires = FXCollections.observableArrayList(service.findAllSecretaires());
        for (int i = 0; i < ovbSecretaires.size(); i++) {
            cbo.getItems().add(ovbSecretaires.get(i).getNom() +" "+ovbSecretaires.get(i).getPrenom());
        }
        cbo.getSelectionModel().selectFirst();
    }
    
    public void loadCboBoxSpecialiisation(ComboBox<String> cbo){
     obvSpecialisation = FXCollections.observableArrayList(service.showSpecialisations());
        for (int i = 0; i < obvSpecialisation.size(); i++) {
            cbo.getItems().add(obvSpecialisation.get(i).getLibelle());
            cbo.getSelectionModel().selectFirst();
        }
    }
    
    public void clear (){
        medecinSelected = null;
        txtNom.clear();
        txtPrenom.clear();
        txtSecretaire.clear();
        txtLogin.clear();
        txtPassword.clear();
        tbListspecialisations.clear();
        
    }

    @FXML
    private void handleUpdateMedecin(ActionEvent event) {
        Medecin medecin = new Medecin(
                txtNom.getText(),
                txtPrenom.getText(),
                txtLogin.getText(),
                txtPassword.getText(),
                "ROLE_MEDECIN",
                ovbSecretaires.get(cboSecretaire.getSelectionModel().getSelectedIndex()),
                tbListspecialisations
        );
        service.updateMedecin(medecin);
        loadTableView();
        clear();
    }

    @FXML
    private void handleDeleteMedecin(ActionEvent event) {
        Medecin medecin = new Medecin(
                txtNom.getText(),
                txtPrenom.getText(),
                txtLogin.getText(),
                txtPassword.getText(),
                "ROLE_MEDECIN",
                ovbSecretaires.get(cboSecretaire.getSelectionModel().getSelectedIndex()),
                tbListspecialisations
        );
        service.deleteMedecin(medecin.getId());
        loadTableView();
        clear();
    }
    
    
}
