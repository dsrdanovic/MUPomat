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
public class Grad extends Entitet {

    private String naziv;
    private String postanskiBroj;
    private PolicijskaUprava policijskaUprava;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPostanskibroj() {
        return postanskiBroj;
    }

    public void setPostanskibroj(String postanskibroj) {
        this.postanskiBroj = postanskibroj;
    }

    public PolicijskaUprava getPolicijskauprava() {
        return policijskaUprava;
    }

    public void setPolicijskaUprava(PolicijskaUprava policijskauprava) {
        this.policijskaUprava = policijskauprava;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
}
