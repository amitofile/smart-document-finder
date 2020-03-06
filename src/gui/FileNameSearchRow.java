/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author amit
 */
public class FileNameSearchRow extends javax.swing.JPanel {

    private final String filePath;
    /**
     * Creates new form ContentSearch
     *
     * @param filePath
     * @param fileName
     * @param word
     */
    public FileNameSearchRow(String filePath, String fileName, String word) {
        this.filePath = filePath;
        initComponents();
        String _fileName = fileName.replaceAll(word, "<font color='red'>" + word + "</font>");
        jTextPane1.setText("<a><font color='blue'>" + filePath.replace(fileName, _fileName) + "</font></a>");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextPane1 = new javax.swing.JTextPane();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(32767, 45));
        setMinimumSize(new java.awt.Dimension(0, 45));
        setPreferredSize(new java.awt.Dimension(380, 45));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jTextPane1.setEditable(false);
        jTextPane1.setContentType("text/html"); // NOI18N
        jTextPane1.setAutoscrolls(false);
        jTextPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextPane1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(220, Short.MAX_VALUE))
                    .addComponent(jTextPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(jTextPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void jTextPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextPane1MouseClicked
        try {
            File file = new File(filePath);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (IOException ex) {
            //Logger.getLogger(ContentSearchRow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextPane1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
