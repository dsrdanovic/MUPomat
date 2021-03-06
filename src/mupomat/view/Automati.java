/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mupomat.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import mupomat.controller.ObradaAutomat;
import mupomat.controller.ObradaPolicijskaPostaja;
import mupomat.model.Automat;
import mupomat.model.Operater;
import mupomat.model.PolicijskaPostaja;
import mupomat.utility.Kontrola;

/**
 *
 * @author SrKy
 */
public class Automati extends javax.swing.JFrame {

    private Pattern pattern;
    private Matcher matcher;
    private ObradaAutomat obradaAutomat;
    private Automat automat;
    private Operater operater;
    private BigDecimal cijena;
    private ImageIcon ikona;

    /**
     * Creates new form Automati
     *
     * @param operater
     */
    public Automati(Operater operater) {
        initComponents();
        setTitle("MUPomat: Automati");
        ikona = new ImageIcon(getClass().getResource("/mupomat/pictures/balloonerror.png"));
        centrirajZaslon();
        dodajIkonu();
        obradaAutomat = new ObradaAutomat();
        this.operater = operater;
        napuniCBAutomati();
        trazi();
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
        txtUvjet = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaAutomata = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNaziv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtProizvodac = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCijena = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbPolicijskePostaje = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnDodaj = new javax.swing.JButton();
        btnPromjeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnPonisti = new javax.swing.JButton();
        btnIzlaz = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblDatumPromjene = new javax.swing.JLabel();
        lblDatumUnosa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Traži automat"));

        txtUvjet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUvjetKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUvjet, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUvjet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Automati u bazi"));
        jPanel2.setPreferredSize(new java.awt.Dimension(275, 237));

        listaAutomata.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaAutomataValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listaAutomata);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Podaci o automatu"));

        jLabel1.setText("Naziv");

        jLabel2.setText("Proizvođač");

        jLabel3.setText("Cijena");

        txtCijena.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel4.setText("Policijska postaja");

        jTextField1.setEditable(false);
        jTextField1.setText("kn");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNaziv)
                    .addComponent(txtProizvodac)
                    .addComponent(cmbPolicijskePostaje, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtCijena)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProizvodac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCijena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPolicijskePostaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Opcije"));

        btnDodaj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mupomat/pictures/dodajikona.png"))); // NOI18N
        btnDodaj.setText("Dodaj");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        btnPromjeni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mupomat/pictures/promjeniikona.png"))); // NOI18N
        btnPromjeni.setText("Promjeni");
        btnPromjeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromjeniActionPerformed(evt);
            }
        });

        btnObrisi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mupomat/pictures/obrisiikona.png"))); // NOI18N
        btnObrisi.setText("Obriši");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        btnPonisti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mupomat/pictures/ponistiikona.png"))); // NOI18N
        btnPonisti.setText("Poništi");
        btnPonisti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPonistiActionPerformed(evt);
            }
        });

        btnIzlaz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mupomat/pictures/izlazikona.png"))); // NOI18N
        btnIzlaz.setText("Izlaz");
        btnIzlaz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzlazActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPromjeni)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPonisti, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIzlaz, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDodaj)
                    .addComponent(btnPromjeni)
                    .addComponent(btnObrisi)
                    .addComponent(btnPonisti)
                    .addComponent(btnIzlaz))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Datumi"));
        jPanel5.setPreferredSize(new java.awt.Dimension(240, 79));

        jLabel5.setText("Datum unosa:");

        jLabel6.setText("Datum promjene:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDatumUnosa)
                    .addComponent(lblDatumPromjene))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblDatumUnosa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblDatumPromjene))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listaAutomataValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaAutomataValueChanged
        if (evt.getValueIsAdjusting()) {
            return;
        }

        automat = (Automat) listaAutomata.getSelectedValue();

        if (automat == null) {
            return;
        }
        postaviPolicijskePostaje();

        SimpleDateFormat df = new SimpleDateFormat(Kontrola.FORMAT_DATUMA_VRIJEME);
        lblDatumUnosa.setText(df.format(automat.getDatumUnosa()));

        if (automat.getDatumPromjene() != null) {
            lblDatumPromjene.setText(df.format(automat.getDatumPromjene()));
        }

        txtNaziv.setText(automat.getNaziv());
        txtProizvodac.setText(automat.getProizvodac());
        DecimalFormat kn = new DecimalFormat(Kontrola.FORMAT_CIJENA);
        try {
            txtCijena.setText(kn.format(automat.getCijena()));

        } catch (Exception e) {
            txtCijena.setText(kn.format(0));

        }
    }//GEN-LAST:event_listaAutomataValueChanged

    private void txtUvjetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUvjetKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            trazi();
        }
    }//GEN-LAST:event_txtUvjetKeyPressed

    private void btnIzlazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzlazActionPerformed
        Object[] options = {"Izađi",
            "Ostani",};
        int rez = JOptionPane.showOptionDialog(rootPane,//parent container of JOptionPane
                "Jeste li sigurni da želite izaći iz automata?",
                "Izlaz",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,//do not use a custom Icon
                options,//the titles of buttons
                null);//default button title
        if (rez == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_btnIzlazActionPerformed

    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajActionPerformed
        if (!kontrola()) {
            return;
        }

        if (cmbPolicijskePostaje.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Niste odabrali policijsku postaju!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        automat = new Automat();
        automat.setNaziv(txtNaziv.getText());
        automat.setProizvodac(txtProizvodac.getText());
        automat.setCijena(cijena);
        automat.setOperater(operater);
        automat.setPolicijskapostaja((PolicijskaPostaja) cmbPolicijskePostaje.getSelectedItem());
        automat = obradaAutomat.dodajNovi(automat);

        trazi();
        pocistiPolja();
    }//GEN-LAST:event_btnDodajActionPerformed

    private void btnPromjeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromjeniActionPerformed
        if (automat == null) {
            JOptionPane.showMessageDialog(rootPane, "Prvo odaberite automat!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!kontrola()) {
            return;
        }

        automat.setNaziv(txtNaziv.getText());
        automat.setProizvodac(txtProizvodac.getText());
        automat.setCijena(cijena);
        automat.setOperater(operater);
        automat.setPolicijskapostaja((PolicijskaPostaja) cmbPolicijskePostaje.getSelectedItem());
        obradaAutomat.promjeniPostojeci(automat);

        pocistiPolja();
        trazi();
    }//GEN-LAST:event_btnPromjeniActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        if (automat == null) {
            JOptionPane.showMessageDialog(rootPane, "Prvo odaberite automat!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] options = {"Da",
            "Ne",};
        int rez = JOptionPane.showOptionDialog(rootPane,//parent container of JOptionPane
                "Sigurno obrisati?",
                "Brisanje",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,//do not use a custom Icon
                options,//the titles of buttons
                null);//default button title
        if (rez == JOptionPane.YES_OPTION) {
            try {
                obradaAutomat.obrisiPostojeci(automat);
                trazi();
                pocistiPolja();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Automat se ne može obrisati jer je na njega prijavljeno jedna ili vise dokumenata!", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnPonistiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPonistiActionPerformed
        pocistiPolja();
    }//GEN-LAST:event_btnPonistiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnIzlaz;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnPonisti;
    private javax.swing.JButton btnPromjeni;
    private javax.swing.JComboBox cmbPolicijskePostaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblDatumPromjene;
    private javax.swing.JLabel lblDatumUnosa;
    private javax.swing.JList listaAutomata;
    private javax.swing.JTextField txtCijena;
    private javax.swing.JTextField txtNaziv;
    private javax.swing.JTextField txtProizvodac;
    private javax.swing.JTextField txtUvjet;
    // End of variables declaration//GEN-END:variables

    private void centrirajZaslon() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setResizable(false);
    }

    private void dodajIkonu() {
        this.setIconImage(new ImageIcon(getClass()
                .getResource("/mupomat" + "/pictures" + "/automatmenuicon.png")).getImage());
    }

    private void trazi() {
        List<Automat> lista = obradaAutomat.dohvatiIzBaze(txtUvjet.getText().trim());
        DefaultListModel<Automat> m = new DefaultListModel<>();

        for (Automat automat : lista) {
            m.addElement(automat);
        }
        listaAutomata.setModel(m);
    }

    private void napuniCBAutomati() {
        ObradaPolicijskaPostaja opp = new ObradaPolicijskaPostaja();
        List<PolicijskaPostaja> lista = opp.dohvatiIzBaze("");
        DefaultComboBoxModel<PolicijskaPostaja> m = new DefaultComboBoxModel<>();
        PolicijskaPostaja pp = new PolicijskaPostaja();
        pp.setSifra(0);
        pp.setNaziv("--- Odaberite policijsku postaju ---");
        m.addElement(pp);
        for (PolicijskaPostaja policijskaPostaja : lista) {
            m.addElement(policijskaPostaja);
        }
        cmbPolicijskePostaje.setModel(m);
    }

    private void postaviPolicijskePostaje() {
        DefaultComboBoxModel<PolicijskaPostaja> m = (DefaultComboBoxModel<PolicijskaPostaja>) cmbPolicijskePostaje.getModel();
        int odabrano = 0;
        for (int i = 0; i < m.getSize(); i++) {
            if (m.getElementAt(i).getSifra() == automat.getPolicijskapostaja().getSifra()) {
                odabrano = i;
                break;
            }
        }
        cmbPolicijskePostaje.setSelectedIndex(odabrano);
    }

    private void pocistiPolja() {
        txtNaziv.setText("");
        txtProizvodac.setText("");
        txtCijena.setText("");
        cmbPolicijskePostaje.setSelectedIndex(0);
    }

    private boolean kontrola() {
        cijena = null;

        //ovdje će doći kontrole
        if (!kontrolaNaziv()
                || !kontrolaProizvodac()
                || !kontrolaCijena()) {

            return false;
        }

        return true;
    }

    private boolean kontrolaNaziv() {
        pattern = Pattern.compile(Kontrola.NAZIV_AUTOMATA);
        matcher = pattern.matcher(txtNaziv.getText().trim());

        Kontrola.resetirajPoljeGreske(txtNaziv);
        if (!Kontrola.kontrolaPraznoPolje(txtNaziv.getText(), txtNaziv, "Ovo polje je obavezno! ", ikona)) {
            Kontrola.oznaciPoljeGreske(txtNaziv);
            return false;
        }
        if (!matcher.matches()) {
            Kontrola.kontrolaUnosa(txtNaziv, "<html>Netočan unos.<br>Dopušteno: [a-žA-Ž0-9-]<br>(min.10 max.30 znakova)</html> ", ikona);
            Kontrola.oznaciPoljeGreske(txtNaziv);
            return false;
        }

        return true;
    }

    private boolean kontrolaProizvodac() {
        pattern = Pattern.compile(Kontrola.PROIZVODAC);
        matcher = pattern.matcher(txtProizvodac.getText().trim());

        Kontrola.resetirajPoljeGreske(txtNaziv);
        if (!Kontrola.kontrolaPraznoPolje(txtProizvodac.getText(), txtProizvodac, "Ovo polje je obavezno! ", ikona)) {
            Kontrola.oznaciPoljeGreske(txtProizvodac);
            return false;
        }
        if (!matcher.matches()) {
            Kontrola.kontrolaUnosa(txtProizvodac, "<html>Netočan unos.<br>Dopušteno: [a-ž,A-Ž,-,razmak]<br>(min.4 max.30 znakova)</html> ", ikona);
            Kontrola.oznaciPoljeGreske(txtProizvodac);
            return false;
        }

        return true;
    }

    private boolean kontrolaCijena() {
        Kontrola.resetirajPoljeGreske(txtCijena);
        if (!Kontrola.kontrolaPraznoPolje(txtCijena.getText(), txtCijena, "Ovo polje je obavezno! ", ikona)) {
            Kontrola.oznaciPoljeGreske(txtCijena);
            return false;
        }
        if (txtCijena.getText().trim().length() > 0) {
            try {
                cijena = new BigDecimal(txtCijena.getText().replace(".", "").replace(",", "."));
            } catch (Exception e) {
                Kontrola.kontrolaUnosa(txtCijena, "<html>Netočan unos.<br>Dopušteno: [0-9]<br>(min.4 znaka)</html> ", ikona);
                Kontrola.oznaciPoljeGreske(txtCijena);
                return false;
            }
        }

        return true;
    }
}
