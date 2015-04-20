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
public class PolicijskaPostaja extends Entitet {

    private String naziv;
    private String nacelnik;
    private String adresa;
    private String telefon;
    private String slika;
    private Grad grad;

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

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

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    @Override
    public String toString() {
        return naziv;
    }

}
