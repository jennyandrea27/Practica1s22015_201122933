/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariomaker;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author jenny
 */
public class Comportamiento extends javax.swing.JFrame {
    String comportamiento="";
    //generar nueva form
    JFrame juego=new JFrame();
    JPanel panel=new JPanel();

    /**
     * Creates new form Comportamiento
     */
    public Comportamiento() {
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
        bPila = new javax.swing.JButton();
        BCola = new javax.swing.JButton();
        bAnterior = new javax.swing.JButton();
        bSiguiente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("URW Bookman L", 0, 15)); // NOI18N
        jLabel1.setText("Seleccione el comportamiento ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 30, 270, 30);

        bPila.setFont(new java.awt.Font("URW Bookman L", 0, 15)); // NOI18N
        bPila.setText("PILA");
        bPila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPilaActionPerformed(evt);
            }
        });
        getContentPane().add(bPila);
        bPila.setBounds(100, 120, 46, 28);

        BCola.setFont(new java.awt.Font("URW Bookman L", 0, 15)); // NOI18N
        BCola.setText("COLA");
        BCola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BColaActionPerformed(evt);
            }
        });
        getContentPane().add(BCola);
        BCola.setBounds(240, 120, 56, 28);

        bAnterior.setFont(new java.awt.Font("URW Bookman L", 0, 15)); // NOI18N
        bAnterior.setText("VOLVER");
        bAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnteriorActionPerformed(evt);
            }
        });
        getContentPane().add(bAnterior);
        bAnterior.setBounds(30, 190, 90, 28);

        bSiguiente.setFont(new java.awt.Font("URW Bookman L", 0, 15)); // NOI18N
        bSiguiente.setText("SIGUIENTE");
        bSiguiente.setEnabled(false);
        bSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSiguienteActionPerformed(evt);
            }
        });
        getContentPane().add(bSiguiente);
        bSiguiente.setBounds(280, 190, 110, 28);

        jLabel2.setFont(new java.awt.Font("URW Bookman L", 0, 15)); // NOI18N
        jLabel2.setText("para remover los objetos...");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 70, 210, 16);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo_1.jpg"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(-300, -110, 720, 410);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bPilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPilaActionPerformed
        // TODO add your handling code here:
        comportamiento="pila";
        bSiguiente.setEnabled(true);
    }//GEN-LAST:event_bPilaActionPerformed

    private void BColaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BColaActionPerformed
        // TODO add your handling code here:
        comportamiento="cola";
        bSiguiente.setEnabled(true);
    }//GEN-LAST:event_BColaActionPerformed

    private void GenerarForm(){
        juego=new JFrame();
        panel=new JPanel(){
            
            public Dimension getPreferedSize(){
                return new Dimension(50,600);
            }
        };
        JScrollPane jsp=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(100,0,60,610);
        juego.add(jsp);
        juego.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    private void bAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnteriorActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);        
    }//GEN-LAST:event_bAnteriorActionPerformed

    private void bSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSiguienteActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_bSiguienteActionPerformed

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
            java.util.logging.Logger.getLogger(Comportamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Comportamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Comportamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Comportamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Comportamiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCola;
    private javax.swing.JButton bAnterior;
    private javax.swing.JButton bPila;
    private javax.swing.JButton bSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
