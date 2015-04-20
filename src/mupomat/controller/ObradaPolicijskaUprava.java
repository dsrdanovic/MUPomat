/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mupomat.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mupomat.model.PolicijskaUprava;
import mupomat.utility.MySqlBazaPodataka;

/**
 *
 * @author SrKy
 */
public class ObradaPolicijskaUprava extends Obrada<PolicijskaUprava> {

    @Override
    public PolicijskaUprava dodajNovi(PolicijskaUprava entitet) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("insert into policijskauprava (naziv,nacelnik,sjediste,adresa,telefon,internetstranica,datumunosa,datumpromjene,operater) values (?,?,?,?,?,?,now(),now(),?)", Statement.RETURN_GENERATED_KEYS);
            izraz.setString(1, entitet.getNaziv());
            izraz.setString(2, entitet.getNacelnik());
            izraz.setString(3, entitet.getSjediste());
            izraz.setString(4, entitet.getAdresa());
            izraz.setString(5, entitet.getTelefon());
            izraz.setString(6, entitet.getInternetstranica());
            izraz.setInt(7, entitet.getOperater().getSifra());
            izraz.executeUpdate();
            ResultSet rs = izraz.getGeneratedKeys();
            rs.next();
            entitet.setSifra(rs.getInt(1));
            rs.close();
            izraz.close();
            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        return entitet;
    }

    @Override
    public void promjeniPostojeci(PolicijskaUprava entitet) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = null;

            izraz = veza.prepareStatement("update policijskauprava set naziv=?,nacelnik=?,sjediste=?,adresa=?,telefon=?,internetstranica=?,datumpromjene=now(), operater=? where sifra=?");

            izraz.setString(1, entitet.getNaziv());
            izraz.setString(2, entitet.getNacelnik());
            izraz.setString(3, entitet.getSjediste());
            izraz.setString(4, entitet.getAdresa());
            izraz.setString(5, entitet.getTelefon());
            izraz.setString(6, entitet.getInternetstranica());
            izraz.setInt(7, entitet.getOperater().getSifra());
            izraz.setInt(8, entitet.getSifra());
            izraz.executeUpdate();

            izraz.close();
            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void obrisiPostojeci(PolicijskaUprava entitet) throws SQLException {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("delete from policijskauprava where sifra=?");
            izraz.setInt(1, entitet.getSifra());
            izraz.executeUpdate();
            izraz.close();
            veza.close();
        } catch (ClassNotFoundException | IOException e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    @Override
    public List<PolicijskaUprava> dohvatiIzBaze(String uvjet) {
        List<PolicijskaUprava> lista = new ArrayList<>();
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select * from policijskauprava where naziv like ?");
            izraz.setString(1, "%" + uvjet + "%");

            ResultSet rs = izraz.executeQuery();
            PolicijskaUprava policijskaUprava = null;
            while (rs.next()) {

                policijskaUprava = new PolicijskaUprava();
                policijskaUprava.setSifra(rs.getInt("sifra"));
                policijskaUprava.setNaziv(rs.getString("naziv"));
                policijskaUprava.setNacelnik(rs.getString("nacelnik"));
                policijskaUprava.setSjediste(rs.getString("sjediste"));
                policijskaUprava.setAdresa(rs.getString("adresa"));
                policijskaUprava.setTelefon(rs.getString("telefon"));
                policijskaUprava.setInternetstranica(rs.getString("internetstranica"));
                policijskaUprava.setDatumUnosa(new Date(rs.getTimestamp("datumunosa").getTime()));
                policijskaUprava.setDatumPromjene(new Date(rs.getTimestamp("datumpromjene").getTime()));
                lista.add(policijskaUprava);
            }
            rs.close();
            izraz.close();
            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
      
        return lista;

    }

    public boolean mozeBrisanje(PolicijskaUprava entitet) {

        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("delete from grad where policijskauprava=?");
            izraz.setInt(1, entitet.getSifra());

            ResultSet rs = izraz.executeQuery();
            boolean postoji = false;
            while (rs.next()) {
                postoji = true;
            }
            rs.close();
            izraz.close();
            veza.close();
            return !postoji;
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

}
