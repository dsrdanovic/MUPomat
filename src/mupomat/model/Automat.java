/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mupomat.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SrKy
 */
public class Automat extends Entitet{

    private String naziv;
    private String proizvodac;
    private BigDecimal cijena;
    private PolicijskaPostaja policijskaPostaja;

    public String getProizvodac() {
        return proizvodac;
    }

    public void setProizvodac(String proizvodac) {
        this.proizvodac = proizvodac;
    }

    public BigDecimal getCijena() {
        return cijena;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public PolicijskaPostaja getPolicijskapostaja() {
        return policijskaPostaja;
    }

    public void setPolicijskapostaja(PolicijskaPostaja policijskapostaja) {
        this.policijskaPostaja = policijskapostaja;
    }

    @Override
    public String toString() {
        return this.naziv;
    }
    
}
