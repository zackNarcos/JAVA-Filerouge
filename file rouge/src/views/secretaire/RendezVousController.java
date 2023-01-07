/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.secretaire;

import entitiies.Detailprestation;
import entitiies.RendezVous;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.Service;
import utils.ViewService;

/**
 * FXML Controller class
 *
 * @author zackarieabessoloekouma
 */
public class RendezVousController implements Initializable {

    @FXML
    private TableView<RendezVous> tbleRdv;
    @FXML
    private TableColumn<RendezVous ,Date> tblcDate;
    @FXML
    private TableColumn<RendezVous, String> tblcType;
    @FXML
    private TableColumn<RendezVous, Integer> tblcId;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtStatut;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField txtType;
    private ObservableList<RendezVous> obvRdv;
    Service service= new Service();
    private RendezVous rdvSelected= new RendezVous();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableView();
    }    
    private void loadTableView(){
        List<RendezVous> rdvs= service.showRdvEnAttente();
        obvRdv = FXCollections.observableArrayList(rdvs);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("typeRdv"));  
        tblcId.setCellValueFactory(new PropertyValueFactory<>("idPatient")); 
        tbleRdv.setItems(obvRdv);
        
    } 

    

    @FXML
    private void handleValiderRdv(ActionEvent event) {
    }

    @FXML
    private void handleSelectRdv(MouseEvent event) {
        rdvSelected= tbleRdv.getSelectionModel().getSelectedItem();
        txtDate.setValue(ViewService.convertToLocalDateViaInstant(rdvSelected.getDate()));
        txtType.setText(rdvSelected.getTypeRdv());
        txtCode.setText(rdvSelected.getPatient().getCode());
        txtNom.setText(rdvSelected.getPatient().getNom());
        txtStatut.setText(rdvSelected.getStatus());
    }
    
}
