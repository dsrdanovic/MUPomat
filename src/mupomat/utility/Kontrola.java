/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mupomat.utility;

import java.awt.Color;
import java.awt.Component;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.ModernBalloonStyle;

/**
 *
 * @author SrKy
 */
public class Kontrola {

    public static final String FORMAT_DATUMA_VRIJEME="dd. MM. yyyy. HH:mm:ss";
    public static final String FORMAT_DATUMA = "dd. MM. yyyy.";
    public static final String FORMAT_VRIJEME = "HH:mm:ss";
    
    // REGEX POLICIJSKE UPRAVE
    public static final String NAZIV_POLICIJSKE_UPRAVE = "^[a-zA-ZćĆčČđĐšŠžŽ-]{6,30}$";
    public static final String IME_PREZIME_NACELNIKA = "^[a-zA-Z ćĆčČđĐšŠžŽ]{5,50}$";
    public static final String NAZIV_SJEDISTA = "^[a-zA-ZćĆčČđĐšŠžŽ]{4,20}$";
    public static final String ADRESA = "^[a-zA-Z0-9ćĆčČđĐšŠžŽ .]{6,50}$";
    public static final String TELEFON = "^[0-9]{2,3}/[0-9]{3,4}-[0-9]{3,4}$";
    public static final String WEB_ADRESA = "^[a-z]{3,3}.[a-z-.]{3,30}.[a-z]{2,20}.[a-z]{2,4}$";

    // REGEX GRADOVI
    public static final String NAZIV_GRADA = "^[a-zA-ZćĆčČđĐšŠžŽ .]{4,50}$";
    public static final String POSTANSKI_BROJ = "^[0-9]{5,5}$";

    // REGEX POLICIJSKE POSTAJE
    public static final String NAZIV_POLICIJSKE_POSTAJE = "^[a-zA-ZćĆčČđĐšŠžŽ .]{10,50}$";

    // REGEX AUTOMATI
    public static final String NAZIV_AUTOMATA = "^[a-zA-Z0-9 ćĆčČđĐšŠžŽ-]{10,30}$";
    public static final String PROIZVODAC = "^[a-zA-ZćĆčČđĐšŠžŽ -]{4,30}$";
    public static final String FORMAT_CIJENA = "#,##0.00";

    // REGEX DOKUMENTI
    public static final String REDNI_BROJ_DOKUMENTA = "^[0-9]{1,4}$";
    public static final String NAZIV_DOKUMENTA = "^[a-zA-ZćĆčČđĐšŠžŽ -]{3,50}$";

    // REGEX REGISTRACIJA
    public static final String OIB = "^[0-9]{11,11}$";
    public static final String IME = "^[a-zA-Z ćĆčČđĐšŠžŽ]{2,50}$";
    public static final String PREZIME = "^[a-zA-Z ćĆčČđĐšŠžŽ]{2,50}$";
    public static final String KORISNICKO_IME = "^[a-z0-9_-]{6,15}$";
    public static final String LOZINKA = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,20}$";
    public static final String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean kontrolaPraznoPolje(String tekstZaKontrolu, JTextField polje, String tekstPoruke, ImageIcon ikona) {
        if (tekstZaKontrolu.trim().length() == 0) {
            kontrolaUnosa(polje, tekstPoruke, ikona);
            return false;
        }

        return true;
    }

    public static boolean kontrolaCijeliBroj(Component rootPane, String tekstZaKontrolu, String porukaGreske) {

        try {
            Integer.parseInt(tekstZaKontrolu);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, porukaGreske, "Pogreška kod unosa", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public static void oznaciPoljeGreske(JTextField polje) {

        polje.setBackground(Color.pink);
        polje.requestFocus();
    }

    public static void resetirajPoljeGreske(JTextField polje) {
        polje.setBackground(Color.white);
        polje.setForeground(Color.black);
    }

    public static void kontrolaUnosa(final JTextField polje, String tekstPoruke, ImageIcon ikona) {
        final BalloonTip myBalloonTip;
        JLabel poruka;
        BalloonTipStyle modern = new ModernBalloonStyle(WIDTH, HEIGHT, Color.pink, Color.pink, Color.black);
        poruka = new JLabel(tekstPoruke);
        poruka.setIcon(ikona);
        poruka.setIconTextGap(10);
        myBalloonTip = new BalloonTip(polje, poruka, modern, false);
        polje.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                myBalloonTip.closeBalloon();
                polje.setBackground(null);

            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                myBalloonTip.closeBalloon();
                polje.setBackground(null);
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                myBalloonTip.closeBalloon();
                polje.setBackground(null);
            }

        });
    }
}
