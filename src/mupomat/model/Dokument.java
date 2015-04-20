/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mupomat.model;

import java.util.List;

/**
 *
 * @author SrKy
 */
public class Dokument extends Entitet {

    private int redniBroj;
    private String naziv;
    private Automat automat;
    private List<Automat> automati;

    public List<Automat> getAutomati() {
        return automati;
    }

    public void setAutomati(List<Automat> automati) {
        this.automati = automati;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Automat getAutomat() {
        return automat;
    }

    public void setAutomat(Automat automat) {
        this.automat = automat;
    }

    @Override
    public String toString() {
        return redniBroj + "." + " " + naziv;
    }

}
