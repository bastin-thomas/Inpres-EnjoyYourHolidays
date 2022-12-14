/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ReservationDataLayer.entities.CreditCard;
import ReservationDataLayer.entities.Voyageurs;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
/**
 *
 * @author Arkios
 */
public class CreditCardGUI extends javax.swing.JDialog {
    private CreditCard creditcard;
    private Voyageurs selectedUser;
    
    /**
     * Creates new form CreditCard
     */
    public CreditCardGUI(java.awt.Frame parent, boolean modal, CreditCard cc, Voyageurs selectedU, double HowMuchToPay) {
        super(parent, modal);
        initComponents();
        creditcard = cc;
        this.selectedUser = selectedU;
        
        CardHolder.setText(selectedUser.getPrenomVoyageur().toUpperCase() + " " + selectedUser.getNomVoyageur().toUpperCase());
        
        
        
        howmuchtopay.setText( String.format("%.2f",HowMuchToPay) + " €");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        KO = new javax.swing.JButton();
        OK = new javax.swing.JButton();
        CardNumber = new javax.swing.JTextField();
        CCV = new javax.swing.JTextField();
        CardHolder = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        howmuchtopay = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Numero de Carte");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("CCV / CVC");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Nom Propriétaire");

        KO.setText("Annuler");
        KO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KOActionPerformed(evt);
            }
        });

        OK.setText("Payement");
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });

        CardNumber.setText("4111111111111");

        CCV.setText("111");

        CardHolder.setText("Hello World");

        jLabel1.setText("A payer:");

        howmuchtopay.setText("xx,xx€");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(KO, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addGap(46, 46, 46)
                .addComponent(OK, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addGap(77, 77, 77))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(CardNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CCV)
                                    .addComponent(CardHolder)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(howmuchtopay)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(CardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(CCV, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CardHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(howmuchtopay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OK, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KO, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KOActionPerformed
        this.dispose();
    }//GEN-LAST:event_KOActionPerformed

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        Pattern pCardNumber = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
        Matcher mCardNumber = pCardNumber.matcher(CardNumber.getText());
        
        if(!mCardNumber.find() && !CardNumber.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Le Numéro de carte est invalide...", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Pattern pCCV = Pattern.compile("^[0-9]{3,4}$");
        Matcher mCCV = pCCV.matcher(CCV.getText());
        
        if(!mCCV.find() && !CardNumber.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Le CCV est invalide...", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        creditcard.setCVV(CCV.getText());
        creditcard.setHolderName(CardHolder.getText());
        creditcard.setNumberCard(CardNumber.getText());
        
        this.dispose();
    }//GEN-LAST:event_OKActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CCV;
    private javax.swing.JTextField CardHolder;
    private javax.swing.JTextField CardNumber;
    private javax.swing.JButton KO;
    private javax.swing.JButton OK;
    private javax.swing.JLabel howmuchtopay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
