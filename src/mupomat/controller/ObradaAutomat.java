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
import mupomat.model.Automat;
import mupomat.model.PolicijskaPostaja;
import mupomat.utility.MySqlBazaPodataka;

/**
 *
 * @author SrKy
 */
public class ObradaAutomat extends Obrada<Automat> {

    @Override
    public Automat dodajNovi(Automat entitet) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("insert into automat (naziv,proizvodac,cijena,policijskapostaja,datumunosa,datumpromjene,operater) values (?,?,?,?,now(),now(),?)", Statement.RETURN_GENERATED_KEYS);
            izraz.setString(1, entitet.getNaziv());
            izraz.setString(2, entitet.getProizvodac());
            izraz.setBigDecimal(3, entitet.getCijena());
            izraz.setInt(4, entitet.getPolicijskapostaja().getSifra());
            izraz.setInt(5, entitet.getOperater().getSifra());
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
    public void promjeniPostojeci(Automat entitet) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = null;

            izraz = veza.prepareStatement("update automat set naziv=?,proizvodac=?,cijena=?,policijskapostaja=?,datumpromjene=now(),operater=? where sifra=?");

            izraz.setString(1, entitet.getNaziv());
            izraz.setString(2, entitet.getProizvodac());
            izraz.setBigDecimal(3, entitet.getCijena());
            izraz.setInt(4, entitet.getPolicijskapostaja().getSifra());
            izraz.setInt(5, entitet.getOperater().getSifra());           
            izraz.setInt(6, entitet.getSifra());
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
    public void obrisiPostojeci(Automat entitet) throws SQLException {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("delete from  automat  where sifra=? ");
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
    public List<Automat> dohvatiIzBaze(String uvjet) {
        List<Automat> listaAutomata = new ArrayList<>();

        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select a.*, b.sifra as sifraPolicijskePostaje, b.naziv as nazivPolicijskePostaje\n"
                    + "from automat a inner join policijskapostaja b on a.policijskapostaja = b.sifra\n"
                    + "where a.naziv like ?");
            izraz.setString(1, "%" + uvjet + "%");

            ResultSet rs = izraz.executeQuery();

            Automat entitet = null;
            PolicijskaPostaja policijskaPostaja = null;
            while (rs.next()) {

                policijskaPostaja = new PolicijskaPostaja();
                policijskaPostaja.setSifra(rs.getInt("sifraPolicijskePostaje"));
                policijskaPostaja.setNaziv(rs.getString("nazivPolicijskePostaje"));

                entitet = new Automat();
                entitet.setPolicijskapostaja(policijskaPostaja);
                entitet.setSifra(rs.getInt("sifra"));
                entitet.setNaziv(rs.getString("naziv"));
                entitet.setProizvodac(rs.getString("proizvodac"));
                entitet.setCijena(rs.getBigDecimal("cijena"));
                entitet.setDatumUnosa(new Date(rs.getTimestamp("datumunosa").getTime()));
                entitet.setDatumPromjene(new Date(rs.getTimestamp("datumpromjene").getTime()));
                listaAutomata.add(entitet);

            }
            rs.close();
            izraz.close();
            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return listaAutomata;

    }

    public List<Automat> dohvatiAutomatePolicijskihPostaja(int sifraPolicijskihPostaja) {
        List<Automat> listaAutomata = new ArrayList<>();

        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select * from automat where policijskapostaja = ?");
            izraz.setInt(1, sifraPolicijskihPostaja);

            ResultSet rs = izraz.executeQuery();

            Automat entitet = null;

            while (rs.next()) {

                entitet = new Automat();

                entitet.setSifra(rs.getInt("sifra"));
                entitet.setNaziv(rs.getString("naziv"));
                entitet.setProizvodac(rs.getString("proizvodac"));
                entitet.setCijena(rs.getBigDecimal("cijena"));
                entitet.setDatumUnosa(new Date(rs.getTimestamp("datumunosa").getTime()));
                entitet.setDatumPromjene(new Date(rs.getTimestamp("datumpromjene").getTime()));
                listaAutomata.add(entitet);

            }
            rs.close();
            izraz.close();
            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return listaAutomata;

    }
}
