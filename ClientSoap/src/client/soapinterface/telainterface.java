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
public class telainterface extends javax.swing.JPanel {

    /**
     * Creates new form telainterface
     */
    public telainterface() {
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
        btaddinterface = new javax.swing.JButton();
        btaltinterface = new javax.swing.JButton();
        btexcinterface = new javax.swing.JButton();
        btconsinterface = new javax.swing.JButton();
        btlistinterface = new javax.swing.JButton();
        btlocinterface = new javax.swing.JButton();
        btsairinterface = new javax.swing.JButton();

        jLabel1.setText("Interface Soap Cliente");

        btaddinterface.setText("Adicionar");
        btaddinterface.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddinterfaceActionPerformed(evt);
            }
        });

        btaltinterface.setText("Alterar");

        btexcinterface.setText("Excluir");

        btconsinterface.setText("Consultar");

        btlistinterface.setText("Listar");

        btlocinterface.setText("Localizar");

        btsairinterface.setText("Sair");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(132, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(139, 139, 139))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btaltinterface, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btaddinterface, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btexcinterface, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btconsinterface, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btlistinterface, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btlocinterface, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btsairinterface, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(122, 122, 122))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btaddinterface)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btaltinterface)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btexcinterface)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btconsinterface)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlistinterface)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlocinterface)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btsairinterface)
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btaddinterfaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddinterfaceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btaddinterfaceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btaddinterface;
    private javax.swing.JButton btaltinterface;
    private javax.swing.JButton btconsinterface;
    private javax.swing.JButton btexcinterface;
    private javax.swing.JButton btlistinterface;
    private javax.swing.JButton btlocinterface;
    private javax.swing.JButton btsairinterface;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
