/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.squallsoft.presentation;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author aliri
 */
public class MainForm extends javax.swing.JFrame {

    private final FileFilter filterCSV;
    private final FileFilter filterPNG;
    private File levelFile, enemyFile, objectFile;
    private List<Integer> red, green, blue;
    private Boolean bRed, bGreen, bBlue;
    private int heidht, width;
    private BufferedImage img;
    
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        
        red = new ArrayList<>();
        green = new ArrayList<>();
        blue = new ArrayList<>();
        
        bRed = bGreen = bBlue = Boolean.FALSE;
        
        filterCSV = new FileNameExtensionFilter("Tiled CSV (.csv)", "csv");
        jfcLevel.setAcceptAllFileFilterUsed(false);    
        jfcLevel.setFileFilter(filterCSV);
        jfcLevel.setDialogType(JFileChooser.OPEN_DIALOG);
        jfcLevel.addActionListener(this::levelFileChooser); 
        
        jfcEnemy.setAcceptAllFileFilterUsed(false);    
        jfcEnemy.setFileFilter(filterCSV);
        jfcEnemy.setDialogType(JFileChooser.OPEN_DIALOG);
        jfcEnemy.addActionListener(this::enemyFileChooser); 
        
        jfcObject.setAcceptAllFileFilterUsed(false);    
        jfcObject.setFileFilter(filterCSV);
        jfcObject.setDialogType(JFileChooser.OPEN_DIALOG);
        jfcObject.addActionListener(this::objectFileChooser); 
        
        filterPNG = new FileNameExtensionFilter("Kaarin png (.png)", "png");
        jfcGenerate.setAcceptAllFileFilterUsed(false);    
        jfcGenerate.setFileFilter(filterPNG);
        jfcGenerate.setDialogType(JFileChooser.SAVE_DIALOG);
        jfcGenerate.addActionListener(this::generateFileChooser);     
        
        this.setLocationRelativeTo(null);
    }
    
    public void levelFileChooser(ActionEvent e) {
        if (e.getActionCommand().equals(javax.swing.JFileChooser.APPROVE_SELECTION)) {
            try {
                levelFile = jfcLevel.getSelectedFile();
                tfLevel.setText(levelFile.getAbsolutePath());
                
                BufferedReader buffReader = new BufferedReader(new FileReader(levelFile));
                
                red.clear();
                bRed = false;
                
                int i;
                String d;
                StringTokenizer st;
                width  = 0;
                heidht = 0;
                    
                while(buffReader.ready()){
                    d = buffReader.readLine();
                    st = new StringTokenizer(d, ",");
                    width  = st.countTokens();
                    heidht = heidht + 1;
                    while(st.hasMoreTokens()){
                        i = Integer.parseInt(st.nextToken());
                        //i = Integer.decode(scanner.next().trim());
                        if(i < 0){
                            JOptionPane.showMessageDialog(this, "Value (-1) was found. Make sure Transparent tiles was set in Tiled.\n Reload all csv files", "Wrong value found it.", JOptionPane.WARNING_MESSAGE);
                            width  = 0;
                            heidht = 0;
                            return;
                        }
                            red.add(i);
                    }
                }
                
                bRed = true;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        
        dialogLevel.setVisible(false);
    }
    
    public void enemyFileChooser(ActionEvent e) {
        if (e.getActionCommand().equals(javax.swing.JFileChooser.APPROVE_SELECTION)) {
            try {
                enemyFile = jfcEnemy.getSelectedFile();
                tfEnemy.setText(enemyFile.getAbsolutePath());
                
                BufferedReader buffReader = new BufferedReader(new FileReader(enemyFile));
                
                green.clear();
                bGreen = false;
                
                int i;
                String d;
                StringTokenizer st;
                    
                while(buffReader.ready()){
                    d = buffReader.readLine();
                    st = new StringTokenizer(d, ",");
                    
                    while(st.hasMoreTokens()){
                        i = Integer.parseInt(st.nextToken());
                        if(i < 0){
                            JOptionPane.showMessageDialog(this, "Value (-1) was found. Make sure Transparent tiles was set in Tiled.\n Reload all csv files", "Wrong value found it.", JOptionPane.WARNING_MESSAGE);
                            width  = 0;
                            heidht = 0;
                            return;
                        }
                        green.add(i);
                    }
                }
                
                bGreen = true;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        
        dialogEnemy.setVisible(false);
    }
    
    public void objectFileChooser(ActionEvent e) {
        if (e.getActionCommand().equals(javax.swing.JFileChooser.APPROVE_SELECTION)) {
            try {
                objectFile = jfcObject.getSelectedFile();
                tfObject.setText(objectFile.getAbsolutePath());
                
                BufferedReader buffReader = new BufferedReader(new FileReader(enemyFile));
                
                blue.clear();
                bBlue = false;
                
                int i;
                String d;
                StringTokenizer st;
                    
                while(buffReader.ready()){
                    d = buffReader.readLine();
                    st = new StringTokenizer(d, ",");
                    
                    while(st.hasMoreTokens()){
                        i = Integer.parseInt(st.nextToken());
                        if(i < 0){
                            JOptionPane.showMessageDialog(this, "Value (-1) was found. Make sure Transparent tiles was set in Tiled.\n Reload all csv files", "Wrong value found it.", JOptionPane.WARNING_MESSAGE);
                            width  = 0;
                            heidht = 0;
                            return;
                        }
                        blue.add(i);
                    }
                }
                bBlue = true;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        
        dialogObject.setVisible(false);
    }
    
    public void generateFileChooser(ActionEvent e){
        if (e.getActionCommand().equals(javax.swing.JFileChooser.APPROVE_SELECTION)) {
            
            try {
                ImageIO.write(img, "png", jfcGenerate.getSelectedFile());
            } catch (IOException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        
        dialogGenerate.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogLevel = new javax.swing.JDialog();
        jfcLevel = new javax.swing.JFileChooser();
        dialogEnemy = new javax.swing.JDialog();
        jfcEnemy = new javax.swing.JFileChooser();
        dialogObject = new javax.swing.JDialog();
        jfcObject = new javax.swing.JFileChooser();
        dialogGenerate = new javax.swing.JDialog();
        jfcGenerate = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfLevel = new javax.swing.JTextField();
        tfEnemy = new javax.swing.JTextField();
        tfObject = new javax.swing.JTextField();
        btnLevel = new javax.swing.JButton();
        btnEnemy = new javax.swing.JButton();
        btnObject = new javax.swing.JButton();
        btnGenerate = new javax.swing.JButton();

        dialogLevel.setAlwaysOnTop(true);
        dialogLevel.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        jfcLevel.setCurrentDirectory(new java.io.File("C:\\"));

            javax.swing.GroupLayout dialogLevelLayout = new javax.swing.GroupLayout(dialogLevel.getContentPane());
            dialogLevel.getContentPane().setLayout(dialogLevelLayout);
            dialogLevelLayout.setHorizontalGroup(
                dialogLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jfcLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            );
            dialogLevelLayout.setVerticalGroup(
                dialogLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogLevelLayout.createSequentialGroup()
                    .addComponent(jfcLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jfcEnemy.setCurrentDirectory(new java.io.File("C:\\"));

                javax.swing.GroupLayout dialogEnemyLayout = new javax.swing.GroupLayout(dialogEnemy.getContentPane());
                dialogEnemy.getContentPane().setLayout(dialogEnemyLayout);
                dialogEnemyLayout.setHorizontalGroup(
                    dialogEnemyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jfcEnemy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );
                dialogEnemyLayout.setVerticalGroup(
                    dialogEnemyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogEnemyLayout.createSequentialGroup()
                        .addComponent(jfcEnemy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );

                jfcObject.setCurrentDirectory(new java.io.File("C:\\"));

                    javax.swing.GroupLayout dialogObjectLayout = new javax.swing.GroupLayout(dialogObject.getContentPane());
                    dialogObject.getContentPane().setLayout(dialogObjectLayout);
                    dialogObjectLayout.setHorizontalGroup(
                        dialogObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jfcObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    );
                    dialogObjectLayout.setVerticalGroup(
                        dialogObjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogObjectLayout.createSequentialGroup()
                            .addComponent(jfcObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                    );

                    jfcGenerate.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
                    jfcGenerate.setCurrentDirectory(new java.io.File("C:\\"));

                        javax.swing.GroupLayout dialogGenerateLayout = new javax.swing.GroupLayout(dialogGenerate.getContentPane());
                        dialogGenerate.getContentPane().setLayout(dialogGenerateLayout);
                        dialogGenerateLayout.setHorizontalGroup(
                            dialogGenerateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jfcGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        );
                        dialogGenerateLayout.setVerticalGroup(
                            dialogGenerateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogGenerateLayout.createSequentialGroup()
                                .addComponent(jfcGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        );

                        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                        setTitle("TiledToKaarin Level Image - by Squall");

                        jLabel1.setText("Level:");

                        jLabel2.setText("Enemy:");

                        jLabel3.setText("Object:");

                        tfLevel.setEditable(false);

                        tfEnemy.setEditable(false);

                        tfObject.setEditable(false);

                        btnLevel.setText("...");
                        btnLevel.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnLevelActionPerformed(evt);
                            }
                        });

                        btnEnemy.setText("...");
                        btnEnemy.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnEnemyActionPerformed(evt);
                            }
                        });

                        btnObject.setText("...");
                        btnObject.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnObjectActionPerformed(evt);
                            }
                        });

                        btnGenerate.setText("Generate Level Image");
                        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnGenerateActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfEnemy))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfObject)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnLevel)
                                            .addComponent(btnEnemy)
                                            .addComponent(btnObject))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(btnGenerate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        );

                        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(tfLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLevel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(tfEnemy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEnemy))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(tfObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnObject))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGenerate)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void btnLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLevelActionPerformed
        dialogLevel.pack();
        dialogLevel.setLocationRelativeTo(null);
        dialogLevel.setVisible(true);
    }//GEN-LAST:event_btnLevelActionPerformed

    private void btnEnemyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnemyActionPerformed
        dialogEnemy.pack();
        dialogEnemy.setLocationRelativeTo(null);
        dialogEnemy.setVisible(true);
    }//GEN-LAST:event_btnEnemyActionPerformed

    private void btnObjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObjectActionPerformed
        dialogObject.pack();
        dialogObject.setLocationRelativeTo(null);
        dialogObject.setVisible(true);
    }//GEN-LAST:event_btnObjectActionPerformed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        // make image...
        System.out.println("Map width: "+width+" Map heidth: "+heidht);
        if(width == 0 || heidht == 0) return;
        img = new BufferedImage(width, heidht, TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        for(int y = 0; y < heidht; y++){
            for(int x = 0; x < width; x++){
                int idx = (y*width)+x;
                
                int redColor   = red.size()   > idx ? red.get(idx)   : 255;
                int greenColor = green.size() > idx ? green.get(idx) : 255;
                int blueColor  = blue.size()  > idx ? blue.get(idx)  : 255;
                
                Color cor = new Color(redColor, greenColor, blueColor);
                g.setColor(cor);
                g.fillRect(x, y, 1, 1);
            }
        }
        g.dispose();
        //Open to save file
        dialogGenerate.pack();
        dialogGenerate.setLocationRelativeTo(null);
        dialogGenerate.setVisible(true);
    }//GEN-LAST:event_btnGenerateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnemy;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnLevel;
    private javax.swing.JButton btnObject;
    private javax.swing.JDialog dialogEnemy;
    private javax.swing.JDialog dialogGenerate;
    private javax.swing.JDialog dialogLevel;
    private javax.swing.JDialog dialogObject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JFileChooser jfcEnemy;
    private javax.swing.JFileChooser jfcGenerate;
    private javax.swing.JFileChooser jfcLevel;
    private javax.swing.JFileChooser jfcObject;
    private javax.swing.JTextField tfEnemy;
    private javax.swing.JTextField tfLevel;
    private javax.swing.JTextField tfObject;
    // End of variables declaration//GEN-END:variables
}
