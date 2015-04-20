/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mupomat.model;

import java.util.Date;

/**
 *
 * @author SrKy
 */
public class Entitet {
    
    private int sifra;
    private Operater operater;
    private Date datumUnosa;
    private Date datumPromjene;
    private Date datumRegistracije;

    public Date getDatumRegistracije() {
        return datumRegistracije;
    }

    public void setDatumRegistracije(Date datumRegistracije) {
        this.datumRegistracije = datumRegistracije;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public Operater getOperater() {
        return operater;
    }

    public void setOperater(Operater operater) {
        this.operater = operater;
    }

    public Date getDatumUnosa() {
        return datumUnosa;
    }

    public void setDatumUnosa(Date datumUnosa) {
        this.datumUnosa = datumUnosa;
    }

    public Date getDatumPromjene() {
        return datumPromjene;
    }

    public void setDatumPromjene(Date datumPromjene) {
        this.datumPromjene = datumPromjene;
    }
    
}
