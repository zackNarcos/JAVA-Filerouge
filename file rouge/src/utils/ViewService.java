/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.ComboBox;

/**
 *
 * @author zackarieabessoloekouma
 */
public class ViewService {
    
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }
    
    public Date convertToDateViaInstant2(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant());    
    }
    
    public static Date getDateNow(){
        return java.sql.Date.valueOf(java.time.LocalDate.now());
    }
    
    public static long diffHeureOfTwoDate(Date avant, Date apres) {
        Long diff = avant.getTime() - apres.getTime();
        return (diff/(1000*60*60));
    }
    
    public static void loadComboBoxTypeRdv(ComboBox<String> cboTypeRdv){
        cboTypeRdv.getItems().add("Veuillez Choisir");
        cboTypeRdv.getItems().add("Prestation");
        cboTypeRdv.getItems().add("Consultation");
        cboTypeRdv.getSelectionModel().selectFirst();
    } 
}
