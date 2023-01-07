/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.patient;

import entitiies.Consultation;
import entitiies.DetailOrdonance;
import entitiies.Detailprestation;
import entitiies.Prestation;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.text.Text;
import services.Service;
import utils.ViewService;

/**
 * FXML Controller class
 *
 * @author zackarieabessoloekouma
 */
public class ShowConsultationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Consultation> tblvConsultation;
    @FXML
    private TableColumn<Consultation,Date> tblcDate;
    @FXML
    private TableColumn<Consultation,String> tblcStatus;
    @FXML
    private TableColumn<Consultation,String> tblcSpecialite;
    @FXML
    private TableColumn<Consultation,Integer> tblcCountConcultation;
    
    @FXML
    private TableView<DetailOrdonance> tblvOrdonance;
    @FXML
    private TableColumn<DetailOrdonance,String> tblcMedicament;
    @FXML
    private TableColumn<DetailOrdonance,Integer> tblcPosologie;
    @FXML
    private TableColumn<DetailOrdonance,Integer> tblcDuree;
    
    @FXML
    private TableView<Detailprestation> tblvPrestation;
    @FXML
    private TableColumn<Detailprestation ,String> tblclibelle;
    @FXML
    private TableColumn<Detailprestation,Integer> tblcCountPrestation;
    @FXML
    private TableColumn<Detailprestation,Date> tblcDatePrestation;
    @FXML
    private TextField txtNomMedecin;
    @FXML
    private TextField txtPrenomMedecin;
    @FXML
    private DatePicker dtPickerDateConsu;
    @FXML
    private TextField txtTemperature;
    @FXML
    private TextField txtTension;
    @FXML
    private TextField txtPoids;
    @FXML
    private Text txtErr;
     
    Service service= new Service();
    ObservableList<Consultation> obvConsultatiion;
    private Consultation consultationSeleted;
    ObservableList<Detailprestation> obvPrestations;
    private Prestation prestationSeleted;
    ObservableList<DetailOrdonance> obvDetailOrdonances;

    
    List<Consultation> consultations = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableView();
        txtErr.setVisible(false);
    }    
    
    private void loadTableView(){
        consultations = service.findConsuById();
        obvConsultatiion = FXCollections.observableArrayList(consultations);
        tblcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
//        tblcSpecialite.setCellValueFactory(new PropertyValueFactory<>(""));
        tblvConsultation.setItems(obvConsultatiion);
    }
    
    @FXML
    private void handleSelectRdv(MouseEvent event) {
        consultationSeleted = tblvConsultation.getSelectionModel().getSelectedItem();
        txtNomMedecin.setText(consultationSeleted.getMedecin().getNom());
        txtPrenomMedecin.setText(consultationSeleted.getMedecin().getPrenom());
        txtTemperature.setText(""+consultationSeleted.getTemperature()+"");
        txtTension.setText(""+consultationSeleted.getTension()+"");
        txtPoids.setText(""+consultationSeleted.getPoids()+"");
        dtPickerDateConsu.setValue(ViewService.convertToLocalDateViaInstant(consultationSeleted.getDate()));
        loadTableOrdonance();
        loadTablePrestation();
    }
    
    private void loadTablePrestation(){
        List<Detailprestation> detailprestations = service.showDetailPrestations(consultationSeleted.getId());
        obvPrestations = FXCollections.observableArrayList(detailprestations);
        tblcDatePrestation.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblclibelle.setCellValueFactory(new PropertyValueFactory<>("prestation"));
//        tblcCountPrestation.setCellValueFactory(tblvPrestation);
        tblvPrestation.setItems(obvPrestations);
    }
    
    private void loadTableOrdonance(){
        List<DetailOrdonance> detailOrdonances = consultationSeleted.getOrdonance().getMedicaments();
        obvDetailOrdonances = FXCollections.observableArrayList(detailOrdonances);
        tblcMedicament.setCellValueFactory(new PropertyValueFactory<>("nomMedicament"));
        tblcPosologie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
        tblcDuree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        
        tblvOrdonance.setItems(obvDetailOrdonances);
    }
    
    @FXML
    private void handleToCancelConsultation(ActionEvent event){
                
        if (ViewService.diffHeureOfTwoDate(ViewService.getDateNow(),consultationSeleted.getDate())  <= 48 ) {
            service.cancelConsultation(consultationSeleted);
            txtNomMedecin.clear();
            txtPrenomMedecin.clear();
            txtTemperature.clear();
            txtTension.clear();
            txtPoids.clear();
            dtPickerDateConsu.setValue(null);
            loadTableView();
            
            tblvOrdonance.setItems(null);
            tblvPrestation.setItems(null);
        } else {
            txtErr.setVisible(false);
        }
    }
}
