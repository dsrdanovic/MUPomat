/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mupomat.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import mupomat.controller.ObradaGrad;
import mupomat.controller.ObradaPolicijskaPostaja;
import mupomat.model.Grad;
import mupomat.model.Operater;
import mupomat.model.PolicijskaPostaja;
import mupomat.utility.Kontrola;

/**
 *
 * @author SrKy
 */
public class PolicijskePostaje extends javax.swing.JFrame {

    private Pattern pattern;
    private Matcher matcher;
    private JFileChooser jfc;
    private ObradaPolicijskaPostaja obradaPolicijskaPostaja;
    private PolicijskaPostaja policijskaPostaja;
    private Operater operater;
    private ImageIcon ikona;

    /**
     * Creates new form PolicijskePostaje
     *
     * @param operater
     */
    public PolicijskePostaje(Operater operater) {
        initComponents();
        setTitle("MUPomat: Policijske postaje");
        ikona = new ImageIcon(getClass().getResource("/mupomat/pictures/balloonerror.png"));
        centrirajZaslon();
        dodajIkonu();
        obradaPolicijskaPostaja = new ObradaPolicijskaPostaja();
        this.operater = operater;
        napuniCBGradove();
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
        listaPolicijskihPostaja = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNaziv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNacelnik = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAdresa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefon = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cmbGradovi = new javax.swing.JComboBox();
        panelSlika = new javax.swing.JLabel();
        btnUcitajSliku = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtPutanjaSlike = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblDatumPromjene = new javax.swing.JLabel();
        lblDatumUnosa = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnDodaj = new javax.swing.JButton();
        btnPromjeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnPonisti = new javax.swing.JButton();
        btnIzlaz = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Traži policijsku postaju"));

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
                .addComponent(txtUvjet)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUvjet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Policijske postaje u bazi"));

        listaPolicijskihPostaja.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaPolicijskihPostajaValueChanged(evt);
            }
        });
        listaPolicijskihPostaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listaPolicijskihPostajaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(listaPolicijskihPostaja);

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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Podaci o policijskoj postaji"));

        jLabel1.setText("Naziv");

        jLabel2.setText("Načelnik");

        jLabel3.setText("Adresa");

        jLabel4.setText("Telefon");

        jLabel8.setText("Grad");

        panelSlika.setBackground(new java.awt.Color(255, 255, 255));
        panelSlika.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelSlika.setText("SLIKA");
        panelSlika.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelSlika.setOpaque(true);

        btnUcitajSliku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mupomat/pictures/pronadislikuikona.png"))); // NOI18N
        btnUcitajSliku.setText("Pronađi sliku...");
        btnUcitajSliku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUcitajSlikuActionPerformed(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mupomat/pictures/obrisiikona.png"))); // NOI18N
        btnReset.setText("Obriši");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPutanjaSlike)
                    .addComponent(txtTelefon)
                    .addComponent(txtAdresa)
                    .addComponent(txtNacelnik)
                    .addComponent(txtNaziv)
                    .addComponent(cmbGradovi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSlika, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnUcitajSliku)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbGradovi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNacelnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelSlika, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUcitajSliku)
                    .addComponent(btnReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPutanjaSlike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datumi"));
        jPanel4.setPreferredSize(new java.awt.Dimension(240, 79));

        jLabel6.setText("Datum unosa:");

        jLabel7.setText("Datum promjene:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDatumUnosa))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDatumPromjene)))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblDatumUnosa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblDatumPromjene))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Opcije"));

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
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
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDodaj)
                    .addComponent(btnPromjeni)
                    .addComponent(btnObrisi)
                    .addComponent(btnPonisti)
                    .addComponent(btnIzlaz))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 580, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUvjetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUvjetKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            trazi();
        }
    }//GEN-LAST:event_txtUvjetKeyPressed

    private void listaPolicijskihPostajaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaPolicijskihPostajaValueChanged
        if (evt.getValueIsAdjusting()) {
            txtPutanjaSlike.setText("");
        }

        policijskaPostaja = (PolicijskaPostaja) listaPolicijskihPostaja.getSelectedValue();

        if (policijskaPostaja == null) {
            return;
        }

        postaviGradove();

        SimpleDateFormat df = new SimpleDateFormat(Kontrola.FORMAT_DATUMA_VRIJEME);
        lblDatumUnosa.setText(df.format(policijskaPostaja.getDatumUnosa()));

        if (policijskaPostaja.getDatumPromjene() != null) {
            lblDatumPromjene.setText(df.format(policijskaPostaja.getDatumPromjene()));
        }

        txtNaziv.setText(policijskaPostaja.getNaziv());
        txtNacelnik.setText(policijskaPostaja.getNacelnik());
        txtAdresa.setText(policijskaPostaja.getAdresa());
        txtTelefon.setText(policijskaPostaja.getTelefon());

        if (policijskaPostaja.getSlika() == null) {
            panelSlika.setText("SLIKA");
            panelSlika.setIcon(null);
        } else {
            ImageIcon slika = new ImageIcon(policijskaPostaja.getSlika());
            Image image = slika.getImage(); // transform it
            Image novaSlika = image.getScaledInstance(230, 230, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            slika = new ImageIcon(novaSlika);
            panelSlika.setText("");
            panelSlika.setIcon(slika);

        }


    }//GEN-LAST:event_listaPolicijskihPostajaValueChanged

    private void btnIzlazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzlazActionPerformed
        Object[] options = {"Izađi",
            "Ostani",};
        int rez = JOptionPane.showOptionDialog(rootPane,//parent container of JOptionPane
                "Jeste li sigurni da želite izaći iz policijskih postaja?",
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
        if (cmbGradovi.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Niste odabrali grad!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        policijskaPostaja = new PolicijskaPostaja();
        policijskaPostaja.setGrad((Grad) cmbGradovi.getSelectedItem());
        policijskaPostaja.setNaziv(txtNaziv.getText());
        policijskaPostaja.setNacelnik(txtNacelnik.getText());
        policijskaPostaja.setAdresa(txtAdresa.getText());

        if (panelSlika.getText().matches("SLIKA")) {
            policijskaPostaja.setSlika(null);

        } else {

            policijskaPostaja.setSlika("slike" + File.separator + jfc.getSelectedFile().getName());
        }
        try {
            BufferedImage image = ImageIO.read(new File(jfc.getSelectedFile().getAbsolutePath()));
            ImageIO.write(image, "jpg", new File("slike" + File.separator + jfc.getSelectedFile().getName()));

        } catch (Exception e) {
        }

        policijskaPostaja.setTelefon(txtTelefon.getText());
        policijskaPostaja.setOperater(operater);
        policijskaPostaja = obradaPolicijskaPostaja.dodajNovi(policijskaPostaja);

        trazi();
        pocistiPolja();
    }//GEN-LAST:event_btnDodajActionPerformed

    private void btnPromjeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromjeniActionPerformed

        if (policijskaPostaja == null) {
            JOptionPane.showMessageDialog(rootPane, "Prvo odaberite policijsku postaju!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cmbGradovi.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Niste odabrali grad!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!kontrola()) {
            return;
        }

        policijskaPostaja.setNaziv(txtNaziv.getText());
        policijskaPostaja.setNacelnik(txtNacelnik.getText());
        policijskaPostaja.setAdresa(txtAdresa.getText());
        policijskaPostaja.setTelefon(txtTelefon.getText());

        if (panelSlika.getText().matches("SLIKA")) {
            policijskaPostaja.setSlika(null);

        } else {

            policijskaPostaja.setSlika("slike" + File.separator + jfc.getSelectedFile().getName());
            try {
                BufferedImage image = ImageIO.read(new File(jfc.getSelectedFile().getAbsolutePath()));
                ImageIO.write(image, "jpg", new File("slike" + File.separator + jfc.getSelectedFile().getName()));

            } catch (Exception e) {
            }
        }
        policijskaPostaja.setOperater(operater);
        policijskaPostaja.setGrad((Grad) cmbGradovi.getSelectedItem());
        obradaPolicijskaPostaja.promjeniPostojeci(policijskaPostaja);

        pocistiPolja();
        trazi();


    }//GEN-LAST:event_btnPromjeniActionPerformed

    private void btnPonistiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPonistiActionPerformed
        pocistiPolja();
    }//GEN-LAST:event_btnPonistiActionPerformed

    private void btnUcitajSlikuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUcitajSlikuActionPerformed
        if (policijskaPostaja == null) {
            JOptionPane.showMessageDialog(rootPane, "Prvo odaberite policijsku postaju!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;

        }

        jfc = new JFileChooser();
        File startFile = new File(System.getProperty("user.home") + File.separator + "Desktop");
        FileFilter ff = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        jfc.setFileFilter(ff);
        jfc.setCurrentDirectory(startFile);
        int rez = jfc.showOpenDialog(this);
        if (rez == JFileChooser.APPROVE_OPTION) {

            ImageIcon slika = new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
            Image image = slika.getImage(); // transform it
            Image novaSlika = image.getScaledInstance(230, 230, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            slika = new ImageIcon(novaSlika);  // transform it back
            panelSlika.setText("");
            panelSlika.setIcon(slika);
            txtPutanjaSlike.setText(jfc.getSelectedFile().getAbsolutePath());

        }
    }//GEN-LAST:event_btnUcitajSlikuActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        if (policijskaPostaja == null) {
            JOptionPane.showMessageDialog(rootPane, "Prvo odaberite policijsku postaju!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cmbGradovi.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Niste odabrali grad!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] options = {"Da",
            "Ne",};
        int rez = JOptionPane.showOptionDialog(rootPane,//parent container of JOptionPane
                "Sigurno obrisati",
                "Brisanje",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,//do not use a custom Icon
                options,//the titles of buttons
                null);//default button title
        if (rez == JOptionPane.YES_OPTION) {
            try {
                obradaPolicijskaPostaja.obrisiPostojeci(policijskaPostaja);
                trazi();
                pocistiPolja();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Policijska postaja se ne može obrisati jer je na nju prijavljen jedan ili više automata!", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void listaPolicijskihPostajaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listaPolicijskihPostajaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            btnObrisiActionPerformed(null);
        }
    }//GEN-LAST:event_listaPolicijskihPostajaKeyPressed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

        panelSlika.setIcon(null);
        panelSlika.setText("SLIKA");
        txtPutanjaSlike.setText("");
        ObrisiSliku();
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnIzlaz;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnPonisti;
    private javax.swing.JButton btnPromjeni;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUcitajSliku;
    private javax.swing.JComboBox cmbGradovi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDatumPromjene;
    private javax.swing.JLabel lblDatumUnosa;
    private javax.swing.JList listaPolicijskihPostaja;
    private javax.swing.JLabel panelSlika;
    private javax.swing.JTextField txtAdresa;
    private javax.swing.JTextField txtNacelnik;
    private javax.swing.JTextField txtNaziv;
    private javax.swing.JTextField txtPutanjaSlike;
    private javax.swing.JTextField txtTelefon;
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
                .getResource("/mupomat" + "/pictures" + "/ppmenuicon.png")).getImage());
    }

    private void trazi() {
        List<PolicijskaPostaja> lista = obradaPolicijskaPostaja.dohvatiIzBaze(txtUvjet.getText().trim());
        DefaultListModel<PolicijskaPostaja> m = new DefaultListModel<>();

        for (PolicijskaPostaja policijskaPostaja : lista) {
            m.addElement(policijskaPostaja);
        }
        listaPolicijskihPostaja.setModel(m);
    }

    private boolean kontrola() {

        if (!kontrolaNaziv()
                || !kontrolaNacelnik()
                || !kontrolaAdresa()
                || !kontrolaTelefon()) {
            return false;
        }

        return true;

    }

    private void pocistiPolja() {
        txtNaziv.setText("");
        txtNacelnik.setText("");
        txtAdresa.setText("");
        txtTelefon.setText("");
        txtPutanjaSlike.setText("");
        panelSlika.setIcon(null);
        panelSlika.setText("SLIKA");
        cmbGradovi.setSelectedIndex(0);
    }

    private boolean kontrolaNaziv() {
        pattern = Pattern.compile(Kontrola.NAZIV_POLICIJSKE_POSTAJE);
        matcher = pattern.matcher(txtNaziv.getText().trim());

        Kontrola.resetirajPoljeGreske(txtNaziv);
        if (!Kontrola.kontrolaPraznoPolje(txtNaziv.getText(), txtNaziv, "Ovo polje je obavezno! ", ikona)) {
            Kontrola.oznaciPoljeGreske(txtNaziv);
            return false;
        }
        if (!matcher.matches()) {
            Kontrola.kontrolaUnosa(txtNaziv, "<html>Netočan unos.<br>Dopušteno: [a-ž,A-Ž,.,razmak]<br>(min.10 max.50 znakova)</html> ", ikona);
            Kontrola.oznaciPoljeGreske(txtNaziv);
            return false;
        }

        return true;
    }

    private boolean kontrolaNacelnik() {
        pattern = Pattern.compile(Kontrola.IME_PREZIME_NACELNIKA);
        matcher = pattern.matcher(txtNacelnik.getText().trim());

        Kontrola.resetirajPoljeGreske(txtNacelnik);
        if (!Kontrola.kontrolaPraznoPolje(txtNacelnik.getText(), txtNacelnik, "Ovo polje je obavezno! ", ikona)) {
            Kontrola.oznaciPoljeGreske(txtNacelnik);
            return false;
        }
        if (!matcher.matches()) {
            Kontrola.kontrolaUnosa(txtNacelnik, "<html>Netočan unos.<br>Dopušteno: [a-ž,A-Ž,razmak]<br>(min.5 max.50 znakova)</html> ", ikona);
            Kontrola.oznaciPoljeGreske(txtNacelnik);
            return false;
        }

        return true;
    }

    private boolean kontrolaAdresa() {
        pattern = Pattern.compile(Kontrola.ADRESA);
        matcher = pattern.matcher(txtAdresa.getText().trim());

        Kontrola.resetirajPoljeGreske(txtAdresa);
        if (!Kontrola.kontrolaPraznoPolje(txtAdresa.getText(), txtAdresa, "Ovo polje je obavezno! ", ikona)) {
            Kontrola.oznaciPoljeGreske(txtAdresa);
            return false;
        }
        if (!matcher.matches()) {
            Kontrola.kontrolaUnosa(txtAdresa, "<html>Netočan unos.<br>Dopušteno: [a-ž,A-Ž,0-9,.,razmak]<br>(min.8 max.50 znakova)</html> ", ikona);
            Kontrola.oznaciPoljeGreske(txtAdresa);
            return false;
        }

        return true;
    }

    private boolean kontrolaTelefon() {
        pattern = Pattern.compile(Kontrola.TELEFON);
        matcher = pattern.matcher(txtTelefon.getText().trim());

        Kontrola.resetirajPoljeGreske(txtTelefon);
        if (!Kontrola.kontrolaPraznoPolje(txtTelefon.getText(), txtTelefon, "Ovo polje je obavezno! ", ikona)) {
            Kontrola.oznaciPoljeGreske(txtTelefon);
            return false;
        }
        if (!matcher.matches()) {
            Kontrola.kontrolaUnosa(txtTelefon, "<html>Netočan unos.<br>Dopušteno: [0-9,/,-]<br>(min.10 max.11 znakova)<br>Primjer: 123/456-789</html> ", ikona);
            Kontrola.oznaciPoljeGreske(txtTelefon);
            return false;
        }

        return true;
    }

    private void napuniCBGradove() {
        ObradaGrad og = new ObradaGrad();
        List<Grad> l = og.dohvatiIzBaze("");
        DefaultComboBoxModel<Grad> m = new DefaultComboBoxModel<>();
        Grad g = new Grad();
        g.setSifra(0);
        g.setNaziv("--- Odaberite grad ---");
        m.addElement(g);
        for (Grad grad : l) {
            m.addElement(grad);
        }
        cmbGradovi.setModel(m);

    }

    private void postaviGradove() {
        DefaultComboBoxModel<Grad> m = (DefaultComboBoxModel<Grad>) cmbGradovi.getModel();
        int odabrano = 0;
        for (int i = 0; i < m.getSize(); i++) {
            if (m.getElementAt(i).getSifra() == policijskaPostaja.getGrad().getSifra()) {
                odabrano = i;
                break;
            }
        }
        cmbGradovi.setSelectedIndex(odabrano);
    }

    private void ObrisiSliku() {
        if (policijskaPostaja.getSlika() != null) {
            File f = new File(policijskaPostaja.getSlika());
            f.delete();
        }

        policijskaPostaja.setSlika(null);
    }

}