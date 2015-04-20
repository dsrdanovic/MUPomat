/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mupomat;

import java.util.Properties;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import mupomat.utility.PropertiesSkins;
import mupomat.view.PrijavaKorisnika;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

/**
 *
 * @author SrKy
 */
public class MUPomat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //uključuje se dekoriranje     
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
  
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Properties properties = PropertiesSkins.getProperties();
                    // poziva se look and feel kroz ui manager - zadnji dio u navodnicima (u zagradi) je naziv skina - imate sve nazive na onim linkovima gore  
                    UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.Substance" + properties.getProperty("a") + "LookAndFeel");
                } catch (Exception e) {
                    System.out.println("Insubstantial skin failed to initialize");
                }

//colorization factor ne morate stavljati, ja sam ga stavio jer po defaultu sve boje stavlja na 50% prozirnosti, dakle factor je po defaultu 0.5, a na 1.0 ne stavlja prozirnost uopće - meni je to više odgovaralo pa sam ipak stavio
                UIManager.put(SubstanceLookAndFeel.COLORIZATION_FACTOR, 1.0);
                new PrijavaKorisnika().setVisible(true);
            }
        });

    }
}
