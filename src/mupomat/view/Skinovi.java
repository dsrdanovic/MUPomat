/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mupomat.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import mupomat.utility.PropertiesSkins;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

/**
 *
 * @author SrKy
 */
public class Skinovi extends javax.swing.JFrame {

    private Mupomat mupomat;

    /**
     * Creates new form Skinovi
     *
     * @param mupomat
     */
    public Skinovi(Mupomat mupomat) {
        initComponents();
        setTitle("MUPomat: Teme");
        this.mupomat = mupomat;
        this.setResizable(false);
        postaviSkin();
        centrirajZaslon();
        dodajIkonu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmbPromjeniSkin = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Odaberite temu"));

        cmbPromjeniSkin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Business", "BusinessBlueSteel", "BusinessBlackSteel", "Creme", "CremeCoffee", "Sahara", "Moderate", "Nebula", "NebulaBrickWall", "Autumn", "MistSilver", "MistAqua", "Dust", "DustCoffee", "Gemini", "Mariner", "Twilight", "Magellan", "Graphite", "GraphiteGlass", "GraphiteAqua", "Raven", "ChallengerDeep", "EmeraldDusk" }));
        cmbPromjeniSkin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPromjeniSkinItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbPromjeniSkin, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbPromjeniSkin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbPromjeniSkinItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPromjeniSkinItemStateChanged
        OutputStream output;

        try {

            String skin;
            Properties properties = PropertiesSkins.getProperties();
            skin = (String) cmbPromjeniSkin.getSelectedItem();
            properties.setProperty("a", skin);
            output = new FileOutputStream("skins.properties");
            properties.store(output, skin);

        } catch (IOException ex) {
            Logger.getLogger(Mupomat.class.getName()).log(Level.SEVERE, null, ex);
        }

        refreshSkin();
    }//GEN-LAST:event_cmbPromjeniSkinItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbPromjeniSkin;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private void refreshSkin() {
        JFrame.setDefaultLookAndFeelDecorated(true);
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
                repaint();
                mupomat.repaint();

            }
        });
    }

    private void postaviSkin() {
        try {
            Properties properties = PropertiesSkins.getProperties();

            cmbPromjeniSkin.setSelectedItem(properties.getProperty("a"));

        } catch (IOException ex) {
            Logger.getLogger(Mupomat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void centrirajZaslon() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setResizable(false);
    }

    private void dodajIkonu() {
        this.setIconImage(new ImageIcon(getClass()
                .getResource("/mupomat" + "/pictures" + "/m.png")).getImage());
    }
}