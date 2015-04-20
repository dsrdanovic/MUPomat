/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mupomat.model;

/**
 *
 * @author SrKy
 */
public class PolicijskaUprava extends Entitet {

    private String naziv;
    private String nacelnik;
    private String sjediste;
    private String adresa;
    private String telefon;
    private String internetStranica;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getNacelnik() {
        return nacelnik;
    }

    public void setNacelnik(String nacelnik) {
        this.nacelnik = nacelnik;
    }

    public String getSjediste() {
        return sjediste;
    }

    public void setSjediste(String sjediste) {
        this.sjediste = sjediste;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getInternetstranica() {
        return internetStranica;
    }

    public void setInternetstranica(String internetstranica) {
        this.internetStranica = internetstranica;
    } 

    @Override
    public String toString() {
        return naziv;
    }

}
