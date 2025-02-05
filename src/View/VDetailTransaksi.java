/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.CDetailTransaksi;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author MSI-PC
 */
public class VDetailTransaksi extends javax.swing.JFrame {

    /**
     * Creates new form VDetailTransaksi
     */
    
    CDetailTransaksi controller;
    private List<Integer> ids;
    private String lvl_akses;
    private int user_id;
    
    public VDetailTransaksi(String lvl_akses, int user_id) {
        initComponents();
        controller = new CDetailTransaksi(this);
        controller.isiDataProduk();
        this.lvl_akses = lvl_akses;
        this.user_id = user_id;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JComboBox<String> getCboNamaProduk() {
        return cboNamaProduk;
    }

    public JTable getTblDetailTransaksi() {
        return tblDetailTransaksi;
    }

    public JTextField getTxtImei() {
        return txtImei;
    }

    public JTextField getTxtWarna() {
        return txtWarna;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
    
    private VDetailTransaksi() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetailTransaksi = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboNamaProduk = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtImei = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtWarna = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        tblDetailTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nama Produk", "IMEI", "Warna Produk"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDetailTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetailTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetailTransaksi);
        if (tblDetailTransaksi.getColumnModel().getColumnCount() > 0) {
            tblDetailTransaksi.getColumnModel().getColumn(0).setResizable(false);
            tblDetailTransaksi.getColumnModel().getColumn(1).setResizable(false);
            tblDetailTransaksi.getColumnModel().getColumn(2).setResizable(false);
            tblDetailTransaksi.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(482, 70, 720, 600);

        txtId.setEditable(false);
        getContentPane().add(txtId);
        txtId.setBounds(140, 70, 50, 30);

        jLabel1.setText("ID                      :");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 70, 80, 30);

        jLabel2.setText("Nama Produk  :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 110, 90, 30);

        getContentPane().add(cboNamaProduk);
        cboNamaProduk.setBounds(140, 120, 240, 30);

        jLabel3.setText("IMEI                  :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 160, 90, 30);
        getContentPane().add(txtImei);
        txtImei.setBounds(140, 160, 240, 30);

        jLabel4.setText("Warna Produk :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 210, 90, 30);
        getContentPane().add(txtWarna);
        txtWarna.setBounds(140, 210, 240, 30);

        btnUpdate.setBackground(new java.awt.Color(204, 153, 0));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate);
        btnUpdate.setBounds(170, 260, 90, 30);

        btnDelete.setBackground(new java.awt.Color(204, 153, 0));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete);
        btnDelete.setBounds(270, 260, 90, 30);

        btnSearch.setBackground(new java.awt.Color(204, 153, 0));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        getContentPane().add(btnSearch);
        btnSearch.setBounds(210, 70, 90, 30);

        btnBack.setBackground(new java.awt.Color(204, 204, 204));
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack);
        btnBack.setBounds(1210, 10, 60, 20);

        setSize(new java.awt.Dimension(1294, 727));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblDetailTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetailTransaksiMouseClicked
        // TODO add your handling code here:
        controller.search();
    }//GEN-LAST:event_tblDetailTransaksiMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            // TODO add your handling code here:
            controller.update();
            if (!txtImei.getText().isBlank()
                && !txtWarna.getText().isBlank()) {
                controller.clear();
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getException());
        }
        controller.view();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int option = JOptionPane.showConfirmDialog(null, "Yakin Ingin Menghapus Data?", "Alert", JOptionPane.YES_NO_OPTION);
        try {
            if (option == JOptionPane.YES_OPTION) {
                controller.delete();
                new VTransaksi(lvl_akses, user_id).setVisible(true);
            };
            if (!txtImei.getText().isBlank()
                && !txtWarna.getText().isBlank()) {
                controller.clear();
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getException());
        }
        controller.view();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        controller.view();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        dispose();
        new VTransaksi(lvl_akses, user_id).setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(VDetailTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VDetailTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VDetailTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VDetailTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VDetailTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboNamaProduk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDetailTransaksi;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtImei;
    private javax.swing.JTextField txtWarna;
    // End of variables declaration//GEN-END:variables
}
