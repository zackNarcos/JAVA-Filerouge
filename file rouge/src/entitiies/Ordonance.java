/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiies;

import java.util.List;

/**
 *
 * @author zackarieabessoloekouma
 */
public class Ordonance {
    protected int id;
    protected List<DetailOrdonance> medicaments;

    public Ordonance() {
    }

    public Ordonance(List<DetailOrdonance> medicaments) {
        this.medicaments = medicaments;
    }

    public Ordonance(int id, List<DetailOrdonance> medicaments) {
        this.id = id;
        this.medicaments = medicaments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DetailOrdonance> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(List<DetailOrdonance> medicaments) {
        this.medicaments = medicaments;
    }
    
    
}
