/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mupomat.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mupomat.model.Operater;
import mupomat.utility.MySqlBazaPodataka;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author SrKy
 */
public class ObradaOperater extends Obrada<Operater> {

    @Override
    public Operater dodajNovi(Operater entitet) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("insert into operater (korisnickoime,lozinka,ime,prezime,aktivan) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            izraz.setString(1, entitet.getKorisnickoIme());
            izraz.setString(2, DigestUtils.md5Hex(entitet.getLozinka()));
            izraz.setString(3, entitet.getIme());
            izraz.setString(4, entitet.getPrezime());
            izraz.setBoolean(5, entitet.isAktivan());
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
    public void promjeniPostojeci(Operater entitet) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = null;
            if (entitet.getLozinka().length() > 0) {
                izraz = veza.prepareStatement("update operater set lozinka=?,ime=?,prezime=?,aktivan=? where sifra=? ");

                izraz.setString(1, DigestUtils.md5Hex(entitet.getLozinka()));
                izraz.setString(2, entitet.getIme());
                izraz.setString(3, entitet.getPrezime());
                izraz.setBoolean(4, entitet.isAktivan());
                izraz.setInt(5, entitet.getSifra());
                izraz.executeUpdate();
            } else {
                izraz = veza.prepareStatement("update operater set  ime=?,prezime=?,aktivan=? where sifra=? ");

                izraz.setString(1, entitet.getIme());
                izraz.setString(2, entitet.getPrezime());
                izraz.setBoolean(3, entitet.isAktivan());
                izraz.setInt(4, entitet.getSifra());
                izraz.executeUpdate();
            }
            izraz.close();
            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void obrisiPostojeci(Operater entitet) throws SQLException {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("delete from  operater  where sifra=? ");
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
    public List<Operater> dohvatiIzBaze(String uvjet) {
        List<Operater> lista = new ArrayList<>();
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select * from operater where korisnickoime like ? or ime like ? or prezime like ?");
            izraz.setString(1, "%" + uvjet + "%");
            izraz.setString(2, "%" + uvjet + "%");
            izraz.setString(3, "%" + uvjet + "%");
            ResultSet rs = izraz.executeQuery();
            Operater operater = null;
            while (rs.next()) {
                //System.out.println("evo me");
                operater = new Operater();
                operater.setSifra(rs.getInt("sifra"));
                operater.setIme(rs.getString("ime"));
                operater.setPrezime(rs.getString("prezime"));
                operater.setKorisnickoIme(rs.getString("korisnickoime"));
                operater.setAktivan(rs.getBoolean("aktivan"));
                lista.add(operater);
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

    public Operater prijaviOperatera(String korisnickoIme, String lozinka) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {

        Connection veza = MySqlBazaPodataka.getConnection();
        PreparedStatement izraz = veza.prepareStatement("select * from operater where korisnickoime=? and lozinka=? and aktivan=1");
        izraz.setString(1, korisnickoIme);
        izraz.setString(2, DigestUtils.md5Hex(lozinka));
        ResultSet rs = izraz.executeQuery();
        Operater operater = null;
        while (rs.next()) {
            operater = new Operater();
            operater.setSifra(rs.getInt("sifra"));
            operater.setIme(rs.getString("ime"));
            operater.setPrezime(rs.getString("prezime"));
            operater.setKorisnickoIme(korisnickoIme);
            operater.setLozinka(lozinka);
        }
        rs.close();
        izraz.close();
        veza.close();
        return operater;
    }

    public Operater dohvatiAktivnogKorisnikaPoKorisnickomImenu(String korisnik) {
        Operater operater = null;
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select * from operater where korisnickoime  = ?");
            izraz.setString(1, korisnik);

            ResultSet rs = izraz.executeQuery();

            while (rs.next()) {
                //System.out.println("evo me");
                operater = new Operater();
                operater.setSifra(rs.getInt("sifra"));
                operater.setIme(rs.getString("ime"));
                operater.setPrezime(rs.getString("prezime"));
                operater.setKorisnickoIme(rs.getString("korisnickoime"));

            }
            rs.close();
            izraz.close();
            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return operater;

    }

}
