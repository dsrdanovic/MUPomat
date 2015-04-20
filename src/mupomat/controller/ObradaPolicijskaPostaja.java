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
import mupomat.model.Grad;
import mupomat.model.PolicijskaPostaja;
import mupomat.utility.MySqlBazaPodataka;

/**
 *
 * @author SrKy
 */
public class ObradaPolicijskaPostaja extends Obrada<PolicijskaPostaja> {

    @Override
    public PolicijskaPostaja dodajNovi(PolicijskaPostaja entitet) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("insert into policijskapostaja (naziv,nacelnik,adresa,telefon,slika,grad,datumunosa,datumpromjene,operater) values (?,?,?,?,?,?,now(),now(),?)", Statement.RETURN_GENERATED_KEYS);
            izraz.setString(1, entitet.getNaziv());
            izraz.setString(2, entitet.getNacelnik());
            izraz.setString(3, entitet.getAdresa());
            izraz.setString(4, entitet.getTelefon());
            izraz.setString(5, entitet.getSlika());
            izraz.setInt(6, entitet.getGrad().getSifra());
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
    public void promjeniPostojeci(PolicijskaPostaja entitet) {
       try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = null;

            izraz = veza.prepareStatement("update policijskapostaja set naziv=?, nacelnik=?, adresa=?, telefon=?, slika=?, datumpromjene=now(),operater=?,grad=? where sifra=?");

            izraz.setString(1, entitet.getNaziv());
            izraz.setString(2, entitet.getNacelnik());
            izraz.setString(3, entitet.getAdresa());
            izraz.setString(4, entitet.getTelefon());
            izraz.setString(5, entitet.getSlika());
            izraz.setInt(6, entitet.getOperater().getSifra());
            izraz.setInt(7, entitet.getGrad().getSifra());
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
    public void obrisiPostojeci(PolicijskaPostaja entitet) throws SQLException {
         try{
             Connection veza = MySqlBazaPodataka.getConnection();
        PreparedStatement izraz = veza.prepareStatement("delete from  policijskapostaja  where sifra=? ");
          izraz.setInt(1, entitet.getSifra());
          izraz.executeUpdate();
        izraz.close();
        veza.close();
        }catch( ClassNotFoundException | IOException e){
           //  System.out.println(e.getMessage());
            e.printStackTrace();
            return ;
        }
    }

    @Override
    public List<PolicijskaPostaja> dohvatiIzBaze(String uvjet) {
        List<PolicijskaPostaja> lista = new ArrayList<>();

        try {
            Connection veza = MySqlBazaPodataka.getConnection();

            PreparedStatement izraz = veza.prepareStatement("select a.*, b.sifra as sifraGrada, b.naziv as nazivGrada\n"
                    + "from policijskapostaja a inner join grad b on a.grad = b.sifra\n"
                    + "where a.naziv like ?");
            izraz.setString(1, "%" + uvjet + "%");

            ResultSet rs = izraz.executeQuery();

            PolicijskaPostaja entitet = null;
            Grad grad = null;

            while (rs.next()) {

                grad = new Grad();
                grad.setSifra(rs.getInt("sifraGrada"));
                grad.setNaziv(rs.getString("nazivGrada"));

                entitet = new PolicijskaPostaja();
                entitet.setGrad(grad);
                entitet.setSifra(rs.getInt("sifra"));
                entitet.setNaziv(rs.getString("naziv"));
                entitet.setNacelnik(rs.getString("nacelnik"));
                entitet.setAdresa(rs.getString("adresa"));
                entitet.setTelefon(rs.getString("telefon"));
                entitet.setSlika(rs.getString("slika"));
                entitet.setDatumUnosa(new Date(rs.getTimestamp("datumunosa").getTime()));
                entitet.setDatumPromjene(new Date(rs.getTimestamp("datumpromjene").getTime()));
                lista.add(entitet);
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

    public List<PolicijskaPostaja> dohvatiPolicijskePostajeGradova(int sifraGrada) {
        List<PolicijskaPostaja> lista = new ArrayList<>();

        try {
            Connection veza = MySqlBazaPodataka.getConnection();

            PreparedStatement izraz = veza.prepareStatement("select * from policijskapostaja where grad = ?");
            izraz.setInt(1, sifraGrada);

            ResultSet rs = izraz.executeQuery();

            PolicijskaPostaja entitet = null;

            while (rs.next()) {

                entitet = new PolicijskaPostaja();

                entitet.setSifra(rs.getInt("sifra"));
                entitet.setNaziv(rs.getString("naziv"));
                entitet.setNacelnik(rs.getString("nacelnik"));
                entitet.setAdresa(rs.getString("adresa"));
                entitet.setTelefon(rs.getString("telefon"));
                entitet.setSlika(rs.getString("slika"));
                entitet.setDatumUnosa(new Date(rs.getTimestamp("datumunosa").getTime()));
                entitet.setDatumPromjene(new Date(rs.getTimestamp("datumpromjene").getTime()));
                lista.add(entitet);
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
}
