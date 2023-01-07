/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.responsablePrestation;

import entitiies.Detailprestation;
import entitiies.Prestation;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import services.Service;
import utils.ViewService;

/**
 * FXML Controller class
 *
 * @author zackarieabessoloekouma
 */
public class ResponsableController implements Initializable {

    @FXML
    private Button btnShowPrestation;
    @FXML
    private AnchorPane anchorContainer;
    @FXML
    private TableView<Detailprestation> tblcPrestation;
    @FXML
    private TableColumn<Detailprestation, Date> tblcDate;
    @FXML
    private TableColumn<Detailprestation, String> tblcLibelle;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtLibelle;
    
   
    
    
    private Service service=new Service();
    private ObservableList<Detailprestation> ovbPrestation;
    @FXML
    private Text txtShowNameUserOnline;
    private Detailprestation prestationSelected= new Detailprestation();
    @FXML
    private TextField txtStatut;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableView();
        prestationSelected= null;
    }    

    private void loadTableView(){
        List<Detailprestation> prestations= service.showPrestationRResponsable();
        ovbPrestation = FXCollections.observableArrayList(prestations);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("prestation"));       
        tblcPrestation.setItems(ovbPrestation);
    } 
    
    @FXML
    private void handleValider(ActionEvent event) {
        if (prestationSelected!= null) {
            service.validerPrestation(prestationSelected.getId());
            loadTableView();
            prestationSelected=null;
        } else {}
        }
    
    

    @FXML
    private void handleAnnuler(ActionEvent event) {
        if (prestationSelected!= null) {
            service.annulerPrestation(prestationSelected.getId());
            loadTableView();
            prestationSelected=null;
        } else {}

    }

    @FXML
    private void handleSelectPrestation(MouseEvent event) {
        prestationSelected= tblcPrestation.getSelectionModel().getSelectedItem();
        txtDate.setValue(ViewService.convertToLocalDateViaInstant(prestationSelected.getDate()));
        txtLibelle.setText(prestationSelected.getPrestation());
        txtCode.setText(prestationSelected.getPatient().getCode());
        txtNom.setText(prestationSelected.getPatient().getNom());
        txtStatut.setText(prestationSelected.getStatus());
       
    }
    
}
