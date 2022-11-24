/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;
import ProtocolFUCAMP.*;

/**
 *
 * @author Arkios
 */
public class Client_Login extends javax.swing.JFrame {
    
    Properties config;
    
    /**
     * Creates new form Client_Activities
     */
    public Client_Login() {
        initComponents();
        File f = new File("client.cfg");
        config = new Properties();
        
        try {
            if(f.createNewFile()){
                OutputStream os = new FileOutputStream(f.getPath());
                config.setProperty("port", "50005");
                config.setProperty("ip", "127.0.0.1");
                
                config.store(os, "Configuration du Client:");
            }
            else{
                FileInputStream fis = new FileInputStream(f.getPath());
                config.load(fis);
            }
        } catch (IOException ex) {
            Logger.getLogger(Client_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Login = new javax.swing.JButton();
        KO = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        email = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");

        Login.setText("Connexion");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });

        KO.setText("Annuler");
        KO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KOActionPerformed(evt);
            }
        });

        password.setText("abc");

        email.setText("thomas.bastin@inpres.com");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(password)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(KO, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(Login))
                    .addComponent(email))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(KO)
                    .addComponent(Login))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KOActionPerformed
        System.exit(0);
    }//GEN-LAST:event_KOActionPerformed

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        String login = Login.getText();
        String hash = "";
        
        System.out.println("Debut Hashage");
        try {
            hash = hashMD5(password.getPassword());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(Client_Login.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        System.out.println("Fin Hashage: " + hash);
        
        Socket sock;
        InetAddress ip;
        int port;
        
        System.out.println("Recuperation Port");
        //Get Port
        try{
            port = Integer.parseInt(config.getProperty("port"));
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Le Port ne suit pas un format valide (Client.cfg)", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        System.out.println("Recuperation Addresse IP");
        //Get Ip
        try {
            ip = InetAddress.getByName((String)config.getProperty("ip"));
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(this, "L'ip ne suit pas un format valide (Client.cfg)", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
                
        System.out.println("Creation de la connexion avec le socket");
        try {
            //Connect to the Server (from properties)
            sock = new Socket();
            sock.connect(new InetSocketAddress(ip,port));
            sock.getOutputStream().flush();
            
            System.out.println("Récupération des Output Stream");
            ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
            oos.flush();
            
            System.out.println("Creation d'un Objet LoginRequest");
            LoginRequest myreq = new LoginRequest(login, hash);
            
             
            System.out.println("Envoie d'un Objet LoginRequest");
            //Send Login request 
            oos.writeObject((Object)myreq);
            oos.flush();
            
            
            System.out.println("Récupération des Input Stream");
            ObjectInputStream ios = new ObjectInputStream(sock.getInputStream());
            
            Object resp = ios.readObject();
            
            //If it's a Login you look respond, else look if it's a timeout
            LoginResponse lr;
            TimeOut to;
            
            
            if(resp.getClass() == LoginResponse.class){
                lr = (LoginResponse) resp;
            } else if(resp.getClass() == TimeOut.class){
                to = (TimeOut) resp;
                JOptionPane.showMessageDialog(this, "Le Serveur c'est éteint", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else{
                JOptionPane.showMessageDialog(this, "Message reçus du Serveur Inconnus", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            oos.close();
            ios.close();
            
            //check login response:
            switch(lr.getCode()){
                case 200:
                        //Recuperation csocket
                        System.out.println("LoginRéussi, construction de la fenètre main.");
                        Client_Main window = new Client_Main(this, false, sock);
                        this.setVisible(false);
                        window.setVisible(true);
                    break;
                    
                case 401:
                        JOptionPane.showMessageDialog(this, "L'adresse email est invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                    break;
                    
                case 402:
                        JOptionPane.showMessageDialog(this, "Mauvais mot de passe", "Erreur", JOptionPane.ERROR_MESSAGE);
                    break;
                    
                case 403:
                        JOptionPane.showMessageDialog(this, "Une erreur lors de la connexion de la base de donnée", "Erreur", JOptionPane.ERROR_MESSAGE);
                    break;
                    
                case 404:                   
                default:
                    JOptionPane.showMessageDialog(this, "Erreur Inconnue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "La connexion n'a pas réussie\n" + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_LoginActionPerformed
    
    
    
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
            java.util.logging.Logger.getLogger(Client_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client_Login().setVisible(true);
            }
        });
    }
    
    private String hashMD5(char[] pswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        byte[] qzdqzd = new String(pswd).getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(qzdqzd);
        
        return DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton KO;
    private javax.swing.JButton Login;
    private javax.swing.JTextField email;
    private javax.swing.JPasswordField password;
    // End of variables declaration//GEN-END:variables
}
