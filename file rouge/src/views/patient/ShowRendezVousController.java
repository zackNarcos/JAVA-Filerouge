/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.patient;

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
public class ShowRendezVousController implements Initializable {
    
    @FXML
    private TableView<RendezVous> tbblvRdv;
    @FXML
    private TableColumn<RendezVous,String>  tblcLibelle;
    @FXML
    private TableColumn<RendezVous,Date>  tblcDate;
    @FXML
    private TableColumn<RendezVous,String>  tblcStatus;
    @FXML
    private TextField txtTypeRdv;
    @FXML
    private DatePicker txtDate ;
    @FXML
    private TextField txtStatus;

    
    Service service= new Service();
    ObservableList<RendezVous> obvRdvs;
    private RendezVous rdvSelected;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableView();

    }    
    
    private void loadTableView(){
        List<RendezVous> rdvs= service.showRendezVousPatient();
        obvRdvs = FXCollections.observableArrayList(rdvs);
        tblcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("typeRdv"));       
        tbblvRdv.setItems(obvRdvs);
    }
    
    @FXML
    private void handleSelectRdv(MouseEvent event) {
        rdvSelected = tbblvRdv.getSelectionModel().getSelectedItem();
        txtStatus.setText(rdvSelected.getStatus());
        txtTypeRdv.setText(rdvSelected.getTypeRdv());
        txtDate.setValue(ViewService.convertToLocalDateViaInstant(rdvSelected.getDate()));        
    }
    
    @FXML
    private void handleToCancelRdv(ActionEvent event){
        int id = tbblvRdv.getSelectionModel().getSelectedItem().getId();
        service.updateRendezVousPatient(id);
        
//        effacer les champs & rechargement du tblv
        txtStatus.clear();
        txtTypeRdv.clear();
        txtDate.setValue(null);
        loadTableView();
    }
}
