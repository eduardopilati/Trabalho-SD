/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.soapinterface;

/**
 *
 * @author UNIVERSO
 */
public class telamenu extends javax.swing.JFrame {

    /**
     * Creates new form telamenu
     */
    public telamenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btaddmenu = new javax.swing.JButton();
        btaltmenu = new javax.swing.JButton();
        btexcmenu = new javax.swing.JButton();
        btconsmenu = new javax.swing.JButton();
        btlistmenu = new javax.swing.JButton();
        btlocmenu = new javax.swing.JButton();
        btsairmenu = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Mentos - Soap");

        btaddmenu.setText("Adicionar");
        btaddmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddmenuActionPerformed(evt);
            }
        });

        btaltmenu.setText("Alterar");
        btaltmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaltmenuActionPerformed(evt);
            }
        });

        btexcmenu.setText("Excluir");
        btexcmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btexcmenuActionPerformed(evt);
            }
        });

        btconsmenu.setText("Consulta");
        btconsmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btconsmenuActionPerformed(evt);
            }
        });

        btlistmenu.setText("Listar");
        btlistmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlistmenuActionPerformed(evt);
            }
        });

        btlocmenu.setText("Localizar");
        btlocmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlocmenuActionPerformed(evt);
            }
        });

        btsairmenu.setText("Sair");
        btsairmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsairmenuActionPerformed(evt);
            }
        });

        jLabel2.setText("Localização de veículos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btaddmenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btaltmenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btexcmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btconsmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btlistmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btlocmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btsairmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel2)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btaddmenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btaltmenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btexcmenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btconsmenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlistmenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlocmenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btsairmenu)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btaddmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddmenuActionPerformed
        new viewadd().setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btaddmenuActionPerformed

    private void btaltmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaltmenuActionPerformed
        new viewaltera().setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btaltmenuActionPerformed

    private void btexcmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btexcmenuActionPerformed
        new viewexclui().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btexcmenuActionPerformed

    private void btconsmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btconsmenuActionPerformed
        new viewconsulta().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btconsmenuActionPerformed

    private void btlistmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlistmenuActionPerformed
        new viewlistatipo().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btlistmenuActionPerformed

    private void btlocmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlocmenuActionPerformed
        new viewlocaliza().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btlocmenuActionPerformed

    private void btsairmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsairmenuActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btsairmenuActionPerformed

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
            java.util.logging.Logger.getLogger(telamenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telamenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telamenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telamenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telamenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btaddmenu;
    private javax.swing.JButton btaltmenu;
    private javax.swing.JButton btconsmenu;
    private javax.swing.JButton btexcmenu;
    private javax.swing.JButton btlistmenu;
    private javax.swing.JButton btlocmenu;
    private javax.swing.JButton btsairmenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
