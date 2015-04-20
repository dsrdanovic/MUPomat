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
import mupomat.model.Dokument;
import mupomat.utility.MySqlBazaPodataka;

/**
 *
 * @author SrKy
 */
public class ObradaDokument extends Obrada<Dokument> {

    @Override
    public Dokument dodajNovi(Dokument entitet) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("insert into dokument (rednibroj,naziv,automat,datumunosa,datumpromjene,operater) "
                    + "values (?,?,?,now(),now(),?)", Statement.RETURN_GENERATED_KEYS);
            izraz.setInt(1, entitet.getRedniBroj());
            izraz.setString(2, entitet.getNaziv());
            izraz.setInt(3, entitet.getAutomat().getSifra());
            izraz.setInt(4, entitet.getOperater().getSifra());
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
    public void promjeniPostojeci(Dokument entitet) {
          try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = null;

            izraz = veza.prepareStatement("update dokument set rednibroj=?, naziv=?, automat=?,datumpromjene=now(),operater=? where sifra=?");
            
            izraz.setInt(1, entitet.getRedniBroj());
            izraz.setString(2, entitet.getNaziv());
            izraz.setInt(3, entitet.getAutomat().getSifra());
            izraz.setInt(4, entitet.getOperater().getSifra());
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
    public void obrisiPostojeci(Dokument entitet) throws SQLException {
         try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("delete from  dokument  where sifra=? ");
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
    public List<Dokument> dohvatiIzBaze(String uvjet) {
        List<Dokument> lista = new ArrayList<>();
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select * from dokument where naziv like ? group by naziv order by sifra asc");
            izraz.setString(1, "%" + uvjet + "%");

            ResultSet rs = izraz.executeQuery();
            Dokument entitet = null;
//            Automat automat = null;
            
            while (rs.next()) {

//                automat = new Automat();
//                automat.setSifra(rs.getInt("sifraAutomata"));
//                automat.setNaziv(rs.getString("nazivAutomata"));              

                entitet = new Dokument();
//                entitet.setAutomat(automat);
                entitet.setSifra(rs.getInt("sifra"));
                entitet.setRedniBroj(rs.getInt("rednibroj"));
                entitet.setNaziv(rs.getString("naziv"));
                entitet.setDatumUnosa(new Date(rs.getTimestamp("datumunosa").getTime()));
                entitet.setDatumPromjene(new Date(rs.getTimestamp("datumpromjene").getTime()));
                lista.add(entitet);
                
            }
            rs.close();
            izraz.close();
            
            izraz = veza.prepareStatement("select b.sifra, b.naziv from dokument a inner join automat b on a.automat = b.sifra where a.naziv like ?");
            Automat automat = null;
            List<Automat> automati;
            for (Dokument dokument : lista) {

                izraz.setString(1, dokument.getNaziv());

                rs = izraz.executeQuery();
                automati = new ArrayList<>();
                while (rs.next()) {
                    automat = new Automat();
                    automat.setSifra(rs.getInt("sifra"));
                    automat.setNaziv(rs.getString("naziv"));
                    automati.add(automat);
                }
                rs.close();

                dokument.setAutomati(automati);

            }
            
            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return lista;
    }

    public List<Dokument> dohvatiDokumenteAutomata(int sifraAutomata) {
        List<Dokument> lista = new ArrayList<>();
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select * from dokument where automat = ?");
            izraz.setInt(1, sifraAutomata);

            ResultSet rs = izraz.executeQuery();
            Dokument entitet = null;

            while (rs.next()) {

                entitet = new Dokument();

                entitet.setSifra(rs.getInt("sifra"));
                entitet.setRedniBroj(rs.getInt("rednibroj"));
                entitet.setNaziv(rs.getString("naziv"));
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
