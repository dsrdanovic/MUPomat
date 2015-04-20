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
import java.util.Date;
import java.util.List;
import mupomat.model.Korisnik;
import mupomat.utility.MySqlBazaPodataka;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author SrKy
 */
public class ObradaKorisnik extends Obrada<Korisnik> {

    @Override
    public Korisnik dodajNovi(Korisnik entitet) {

        try {
            Connection veza = MySqlBazaPodataka.getConnection();

            veza.setAutoCommit(false);

            PreparedStatement izraz = veza.prepareStatement("insert into osoba (oib,ime,prezime,email) values (?,?,?,?)");
            izraz.setString(1, entitet.getOib());
            izraz.setString(2, entitet.getIme());
            izraz.setString(3, entitet.getPrezime());
            izraz.setString(4, entitet.getEmail());
            izraz.executeUpdate();

            izraz = veza.prepareStatement("insert into korisnik (oib,automat,datumregistracije,korisnickoime,lozinka,uloga,aktivan) values (?,?,now(),?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            izraz.setString(1, entitet.getOib());
            izraz.setString(2, entitet.getAutomat());
            izraz.setString(3, entitet.getKorisnickoIme());
            izraz.setString(4, DigestUtils.md5Hex(entitet.getLozinka()));
            izraz.setString(5, entitet.getUloga());
            izraz.setBoolean(6, entitet.isAktivan());
            izraz.executeUpdate();

            ResultSet rs = izraz.getGeneratedKeys();
            rs.next();
            entitet.setSifra(rs.getInt(1));

            izraz.close();
            veza.commit();
            veza.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return entitet;
    }

    @Override
    public void promjeniPostojeci(Korisnik entitet) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();

            PreparedStatement izraz = veza.prepareStatement("update korisnik set aktivan=0 where sifra=?");

            izraz.setInt(1, entitet.getSifra());
            izraz.executeUpdate();

            izraz.close();

            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

    @Override
    public void obrisiPostojeci(Korisnik entitet) throws SQLException {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();

            veza.setAutoCommit(false);

            PreparedStatement izraz = veza.prepareStatement("delete from korisnik where sifra=?");

            izraz.setInt(1, entitet.getSifra());
            izraz.executeUpdate();

            izraz = veza.prepareStatement("delete from osoba  where oib=?");

            izraz.setString(1, entitet.getOib());
            izraz.executeUpdate();

            izraz.close();

            veza.commit();
            veza.close();
        } catch (ClassNotFoundException | IOException e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

    @Override
    public List<Korisnik> dohvatiIzBaze(String uvjet) {
        List<Korisnik> lista = new ArrayList<>();
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select a.sifra,a.datumregistracije,a.korisnickoime,a.uloga,a.aktivan, b.* from korisnik a inner join osoba b on a.oib=b.oib where b.ime like  ? and aktivan=1");
            izraz.setString(1, "%" + uvjet + "%");
//            izraz.setString(2, "%" + uvjet + "%");

            ResultSet rs = izraz.executeQuery();
            Korisnik entitet = null;
            while (rs.next()) {
                //System.out.println("evo me");
                entitet = new Korisnik();
                entitet.setSifra(rs.getInt("sifra"));
                entitet.setIme(rs.getString("ime"));
                entitet.setPrezime(rs.getString("prezime"));
                entitet.setOib(rs.getString("oib"));
                entitet.setEmail(rs.getString("email"));
                entitet.setDatumRegistracije(new Date(rs.getTimestamp("datumregistracije").getTime()));
                entitet.setKorisnickoIme(rs.getString("korisnickoime"));
                entitet.setUloga(rs.getString("uloga"));
                entitet.setAktivan(rs.getBoolean("aktivan"));

                lista.add(entitet);

            }
            rs.close();
            izraz.close();
            veza.close();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

        return lista;
    }

    public Korisnik prijaviKorisnika(String korisnickoIme, String lozinka) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {

        Korisnik korisnik;
        try (Connection veza = MySqlBazaPodataka.getConnection()) {
            PreparedStatement izraz = veza.prepareStatement("select * from korisnik where korisnickoime=? and lozinka=? and uloga like 'korisnik' and aktivan=1");
            izraz.setString(1, korisnickoIme);
            izraz.setString(2, DigestUtils.md5Hex(lozinka));
            ResultSet rs = izraz.executeQuery();
            korisnik = null;
            while (rs.next()) {
                korisnik = new Korisnik();
                korisnik.setKorisnickoIme(korisnickoIme);
                korisnik.setLozinka(lozinka);
                korisnik.setUloga(rs.getString("uloga"));
            }
            rs.close();
            izraz.close();
            veza.close();
        }
        return korisnik;
    }

    public List<Korisnik> dohvatiNeaktivnogKorisnika(String uvjet) {
        List<Korisnik> lista = new ArrayList<>();
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select a.sifra,a.datumregistracije,a.korisnickoime,a.uloga,a.aktivan, b.* from korisnik a inner join osoba b on a.oib=b.oib where b.ime like  ? and aktivan=0");
            izraz.setString(1, "%" + uvjet + "%");
//            izraz.setString(2, "%" + uvjet + "%");

            ResultSet rs = izraz.executeQuery();
            Korisnik entitet = null;
            while (rs.next()) {
                //System.out.println("evo me");
                entitet = new Korisnik();
                entitet.setSifra(rs.getInt("sifra"));
                entitet.setIme(rs.getString("ime"));
                entitet.setPrezime(rs.getString("prezime"));
                entitet.setOib(rs.getString("oib"));
                entitet.setEmail(rs.getString("email"));
                entitet.setDatumRegistracije(new Date(rs.getTimestamp("datumregistracije").getTime()));
                entitet.setKorisnickoIme(rs.getString("korisnickoime"));
                entitet.setUloga(rs.getString("uloga"));
                entitet.setAktivan(rs.getBoolean("aktivan"));

                lista.add(entitet);

            }
            rs.close();
            izraz.close();
            veza.close();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

        return lista;
    }

    public void aktivirajPostojeci(Korisnik entitet) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();

            PreparedStatement izraz = veza.prepareStatement("update korisnik set aktivan=1 where sifra=?");

            izraz.setInt(1, entitet.getSifra());
            izraz.executeUpdate();

            izraz.close();

            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

    public Korisnik dohvatiKorisnikaPoKorisnickomImenu(String korisnik) {
        Korisnik k = null;
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select * from korisnik where korisnickoime = ?");
            izraz.setString(1, korisnik);

            ResultSet rs = izraz.executeQuery();

            while (rs.next()) {
                //System.out.println("evo me");
                k = new Korisnik();
                k.setSifra(rs.getInt("sifra"));
                k.setKorisnickoIme(rs.getString("korisnickoime"));
                k.setUloga(rs.getString("uloga"));

            }
            rs.close();
            izraz.close();
            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return k;

    }

    public List<Korisnik> dohvatiKorisnickoImeILozinkuPoEmailu(String email) {
        List<Korisnik> lista = new ArrayList<>();
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select a.korisnickoime, a.lozinka from korisnik a inner join osoba b on a.oib = b.oib where b.email = ?");
            izraz.setString(1, email);

            ResultSet rs = izraz.executeQuery();
            Korisnik k = null;
            while (rs.next()) {
                //System.out.println("evo me");
                k = new Korisnik();
                //k.setSifra(rs.getInt("sifra"));
                k.setKorisnickoIme(rs.getString("korisnickoime"));
                k.setLozinka(rs.getString("lozinka"));
                lista.add(k);

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

    public void promjeniPostojecuLozinku(String lozinka, String email) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();

            PreparedStatement izraz = veza.prepareStatement("update korisnik a, osoba b set a.lozinka=? where a.oib = b.oib and b.email=?");

            izraz.setString(1, DigestUtils.md5Hex(lozinka));
            izraz.setString(2, email);
            izraz.executeUpdate();

            izraz.close();

            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }
    public boolean provjeraEmaila(String email){
        
        try{
             Connection veza = MySqlBazaPodataka.getConnection();
        PreparedStatement izraz = veza.prepareStatement("select * from osoba where email=?");
        izraz.setString(1, email);
         
        ResultSet rs = izraz.executeQuery();
        boolean postoji=false;
        while (rs.next()) {
        postoji=true;
        }
        rs.close();
        izraz.close();
        veza.close();
        return !postoji;
        }catch(Exception e){
           //  System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        
        
        
    }
    public void postaviNovuLozinku(String lozinka, String korisnickoIme) {
        try {
            Connection veza = MySqlBazaPodataka.getConnection();

            PreparedStatement izraz = veza.prepareStatement("update korisnik set lozinka=? where korisnickoime=?");

            izraz.setString(1, DigestUtils.md5Hex(lozinka));
            izraz.setString(2, korisnickoIme);
            
            
            izraz.executeUpdate();

            izraz.close();

            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }
      public Korisnik dohvatiKorisnikaPoOibu(String oib) {
        Korisnik k = null;
        try {
            Connection veza = MySqlBazaPodataka.getConnection();
            PreparedStatement izraz = veza.prepareStatement("select * from korisnik where oib = ?");
            izraz.setString(1, oib);

            ResultSet rs = izraz.executeQuery();

            while (rs.next()) {
                //System.out.println("evo me");
                k = new Korisnik();
                k.setSifra(rs.getInt("sifra"));
                k.setOib(rs.getString("oib"));
                k.setUloga(rs.getString("uloga"));

            }
            rs.close();
            izraz.close();
            veza.close();
        } catch (Exception e) {
            //  System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return k;

    }
}
