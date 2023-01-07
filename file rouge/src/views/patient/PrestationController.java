/*


 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.patient;

import entitiies.Detailprestation;
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
import javafx.scene.text.Text;
import services.Service;
import utils.ViewService;
import views.ConnectionController;

/**
 * FXML Controller class
 *
 * @author zackarieabessoloekouma
 */
public class PrestationController implements Initializable {

    @FXML
    private TableView<Detailprestation> tblvPrestation;
    @FXML
    private TableColumn<Detailprestation,String> tblcLibelle;
    @FXML
    private TableColumn<Detailprestation,String> tblcStatus;
    @FXML
    private TableColumn<Detailprestation,Date> tblcDate;
     @FXML
    private DatePicker dtPickerDate;
    @FXML
    private TextField txtLibelle;
    @FXML
    private TextField txtStatus;
    @FXML
    private Text txtErr;
    
    Service service = new Service();
    ObservableList<Detailprestation> ovbPrestations;
    Detailprestation detailprestationSelected;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadTableView();
         txtErr.setVisible(false);
    }    
    
    private void loadTableView(){
        List<Detailprestation> detailprestations= service.showDetailPrestationsByIdPatient(ConnectionController.getCtrl().getUser().getId());
        ovbPrestations = FXCollections.observableArrayList(detailprestations);
        tblcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("prestation"));       
        tblvPrestation.setItems(ovbPrestations);
    }
    
    @FXML
    private void handleSelectPrestation(MouseEvent event) {
        detailprestationSelected = tblvPrestation.getSelectionModel().getSelectedItem();
        txtLibelle.setText(detailprestationSelected.getPrestation());
        txtStatus.setText(detailprestationSelected.getStatus());
        dtPickerDate.setValue(ViewService.convertToLocalDateViaInstant(detailprestationSelected.getDate()));
    }
    
    @FXML
    private void handleToCancelConsultation(ActionEvent event){
//        System.out.println(detailprestationSelected.getId());
        service.cancelPrestattion(detailprestationSelected.getId());
        txtLibelle.clear();
        txtStatus.clear();
        dtPickerDate.setValue(null);
        detailprestationSelected = null;
        loadTableView();
    }
}
