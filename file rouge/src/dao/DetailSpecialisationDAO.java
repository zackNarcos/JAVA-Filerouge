/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitiies.DetailSpecialisation;
import entitiies.Secretaire;
import entitiies.Specialisation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zackarieabessoloekouma
 */
public class DetailSpecialisationDAO implements IDao<DetailSpecialisation>{
    private final String SQL_FIND_BY_ID = "SELECT * "
            + "FROM `detail_specialisation` ds "
            + "INNER JOIN specialisation sp "
            + "ON ds.id = sp.id "
            + "WHERE ds.id_medecin = ?";
    private final String SQL_INSERT = "INSERT INTO `detail_specialisation` "
            + "(`id_specialisation`, `id_medecin`) "
            + "VALUES (?, ?)";
    private final String SQL_DELETE = "DELETE "
            + "FROM `detail_specialisation` "
            + "WHERE id = ?";
    private DataBase dataBase = new DataBase();
    
    
    @Override
    public int insert(DetailSpecialisation detailSpecialisation) {
        int idRdv = 0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_INSERT);
        try {
            dataBase.getPs().setInt(1, detailSpecialisation.getSpecialisation().getId());
            dataBase.getPs().setInt(2, detailSpecialisation.getMedecin().getId());
            dataBase.executeUpdate(SQL_INSERT);
            ResultSet rs = dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idRdv = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailSpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();
        }
        return idRdv;
    }

    @Override
    public int update(DetailSpecialisation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        int idRdv = 0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_DELETE);
        try {
            dataBase.getPs().setInt(1, id);
            dataBase.executeUpdate(SQL_DELETE);
            ResultSet rs = dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idRdv = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailSpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();
        }
        return idRdv;
    }

    @Override
    public List<DetailSpecialisation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public List<DetailSpecialisation> findAllById(int id) {
        List<DetailSpecialisation> detailSpecialisations = new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_FIND_BY_ID);
        try {
            dataBase.getPs().setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(DetailSpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = dataBase.executeSelect(SQL_FIND_BY_ID);
        try {
            while (rs.next()) {
                try {
                    DetailSpecialisation detailSpecialisation = new DetailSpecialisation();
                    detailSpecialisation.setId(rs.getInt("id"));
                    detailSpecialisation.setId(rs.getInt("libelle"));
                } catch (SQLException ex) {
                    Logger.getLogger(DetailSpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SecretaireDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return detailSpecialisations;
    }
    
    public List<Specialisation> findAllSpecilisationById(int id) {
        List<Specialisation> detailSpecialisations = new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_FIND_BY_ID);
        try {
            dataBase.getPs().setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(DetailSpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = dataBase.executeSelect(SQL_FIND_BY_ID);
        try {
            while (rs.next()) {
                try {
                    Specialisation detailSpecialisation = new Specialisation();
                    detailSpecialisation.setId(rs.getInt("id"));
                    detailSpecialisation.setLibelle(rs.getString("libelle"));
                } catch (SQLException ex) {
                    Logger.getLogger(DetailSpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SecretaireDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return detailSpecialisations;
    }

    @Override
    public DetailSpecialisation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int insertList(List<Specialisation> specialisations, int idMedecin) {
//        int idRdv = 0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_INSERT);
        try {
            for (int i = 0; i < specialisations.size(); i++) {
                dataBase.getPs().setInt(1, specialisations.get(i).getId());
                dataBase.getPs().setInt(2, idMedecin);
                dataBase.executeUpdate(SQL_INSERT);
                ResultSet rs = dataBase.getPs().getGeneratedKeys();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailSpecialisationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();
        }
        return 1;
    }
    
    
}
