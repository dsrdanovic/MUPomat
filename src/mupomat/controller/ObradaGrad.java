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
import mupomat.model.PolicijskaUprava;
import mupomat.utility.MySqlBazaPodataka;

/**
 *
 * @author SrKy
 */
public class ObradaGrad extends Obrada<Grad> {

    @Override
    public Grad dodajNovi(Grad entitet) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("insert into grad (naziv,postanskibroj,datumunosa,datumpromjene,operater,policijskauprava) "
                    + "values (?,?,now(),now(),?,?)", Statement.RETURN_GENERATED_KEYS);
            izraz.setString(1, entitet.getNaziv());
            izraz.setString(2, entitet.getPostanskibroj());
            izraz.setInt(3, entitet.getOperater().getSifra());
            izraz.setInt(4, entitet.getPolicijskauprava().getSifra());
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
    public void promjeniPostojeci(Grad entitet) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = null;

            izraz = veza.prepareStatement("update grad set naziv=?, postanskibroj=?,datumpromjene=now(),operater=?,policijskauprava=? where sifra=?");

            izraz.setString(1, entitet.getNaziv());
            izraz.setString(2, entitet.getPostanskibroj());
            izraz.setInt(3, entitet.getOperater().getSifra());
            izraz.setInt(4, entitet.getPolicijskauprava().getSifra());
            izraz.setInt(5, entitet.getSifra());
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
    public void obrisiPostojeci(Grad entitet) throws SQLException {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("delete from grad  where sifra=? ");
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
    public List<Grad> dohvatiIzBaze(String uvjet) {
        List<Grad> listaGradova = new ArrayList<>();

        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select a.*, b.sifra as sifraPolicijskeUprave, b.naziv as nazivPolicijskeUprave\n"
                    + "from grad a inner join policijskauprava b on a.policijskauprava = b.sifra\n"
                    + "where a.naziv like ?");
            izraz.setString(1, "%" + uvjet + "%");

            ResultSet rs = izraz.executeQuery();

            Grad entitet = null;
            PolicijskaUprava policijskaUprava = null;
            while (rs.next()) {

                policijskaUprava = new PolicijskaUprava();
                policijskaUprava.setSifra(rs.getInt("sifraPolicijskeUprave"));
                policijskaUprava.setNaziv(rs.getString("nazivPolicijskeUprave"));

                entitet = new Grad();
                entitet.setPolicijskaUprava(policijskaUprava);
                entitet.setSifra(rs.getInt("sifra"));
                entitet.setNaziv(rs.getString("naziv"));
                entitet.setPostanskibroj(rs.getString("postanskibroj"));
                entitet.setDatumUnosa(new Date(rs.getTimestamp("datumunosa").getTime()));
                entitet.setDatumPromjene(new Date(rs.getTimestamp("datumpromjene").getTime()));
                listaGradova.add(entitet);

            }
            rs.close();
            izraz.close();
            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return listaGradova;
    }

    public boolean mozeBrisanje(Grad entitet) {

        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select sifra from policijskapostaja where grad=? limit 1");
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

    public List<Grad> dohvatiGradovePolicijskePostaje(int sifraPolicijskeUprave) {
        List<Grad> listaGradova = new ArrayList<>();

        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select * from grad where policijskauprava = ?");
            izraz.setInt(1, sifraPolicijskeUprave);

            ResultSet rs = izraz.executeQuery();

            Grad entitet = null;

            while (rs.next()) {

                entitet = new Grad();

                entitet.setSifra(rs.getInt("sifra"));
                entitet.setNaziv(rs.getString("naziv"));
                entitet.setPostanskibroj(rs.getString("postanskibroj"));
                entitet.setDatumUnosa(new Date(rs.getTimestamp("datumunosa").getTime()));
                entitet.setDatumPromjene(new Date(rs.getTimestamp("datumpromjene").getTime()));
                listaGradova.add(entitet);

            }
            rs.close();
            izraz.close();
            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return listaGradova;
    }
}
