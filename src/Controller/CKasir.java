/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DKasir;
import Koneksi.Koneksi;
import Model.MKasir;
import View.VKasir;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

/**
 *
 * @author MSI-PC
 */
public class CKasir {

    Koneksi k;
    MKasir model;
    VKasir view;
    DKasir dao;
    private Map<String, Integer> produk = new HashMap<>();
    private Map<String, Integer> warna = new HashMap<>();
    private Map<String, Integer> jPembayaran = new HashMap<>();

    public CKasir(VKasir view) {
        this.view = view;
    }

    public void insertDetail() throws ClassNotFoundException {
        if (view.getTxtImei().getText().isBlank()) {
            JOptionPane.showMessageDialog(view, "Tolong Isi Seluruh Data Terlebih Dahulu");
        } else {
            String selectedProduk = (String) view.getCboNamaProduk().getSelectedItem();
            int selectedIdProduk = produk.get(selectedProduk);

            String selectedWarna = (String) view.getCboWarnaProduk().getSelectedItem();
            int selectedIdWarna = warna.get(selectedWarna);

            int countImei;
            try {
                // is IMEI Available? //////////////////////////////////////////
                int status = 0;
                for (int i = 0; i < view.getTotalQuantity(); i++) {
                    if (view.getTxtImei().getText().equals(view.getTblTransaksi().getValueAt(i, 3))) {
                        status = 1;
                    }
                }
                countImei = dao.queryCountImei(k.getKoneksi(), view.getTxtImei().getText(), selectedIdProduk, selectedIdWarna);
                if (status == 1) {
                    JOptionPane.showMessageDialog(null, "IMEI sudah Tersedia");
                } else if (countImei == 0) {
                    JOptionPane.showMessageDialog(null, "IMEI tidak Tersedia");
                } else {
                    model = new MKasir(selectedIdProduk,
                            selectedProduk,
                            view.getTxtImei().getText(),
                            selectedWarna,
                            selectedIdWarna);
                    dao.addData(model);
                    clear();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        }
    }

    public void Konfirmasi() throws ClassNotFoundException {
        try {
            k = new Koneksi();
            dao = new DKasir();
            MKasir customer = new MKasir(view.getTxtNamaCustomer().getText(),
                    view.getTxtNik().getText(),
                    view.getTxtNoHp().getText());
            String selectedJPembayaran = (String) view.getCboJPembayaran().getSelectedItem();
            int selectedIdJPembayaran = jPembayaran.get(selectedJPembayaran);
            int total_harga = 0;

            // Is Customers Available ? ////////////////////////////////////
            int countNik = dao.queryCountCustomers(k.getKoneksi(), customer.getNik());
            int countNoHp = dao.queryCountNoHp(k.getKoneksi(), customer.getNo_hp());
            int countCustomers = dao.queryCountCustomers(k.getKoneksi(), customer.getNik());
            int idCustomers = 0;

            if (countNik == 1 && countNoHp == 1) {
                if (countCustomers == 1) {
                    idCustomers = dao.queryGetIdCustomers(k.getKoneksi(), customer.getNik());
                    // Condition for Update no_hp
                    if (!customer.getNo_hp().equals(dao.queryGetNoHp(k.getKoneksi(), idCustomers))) {
                        dao.queryUpdateCustomers(k.getKoneksi(), customer.getNo_hp(), idCustomers);
                    }
                }
            } else if (countNoHp == 1) {
                JOptionPane.showMessageDialog(view, "No HP sudah tersedia");
            } else if (countNik == 1) {
                JOptionPane.showMessageDialog(view, "NIK sudah tersedia");

            } else {
                dao.queryInsertCustomers(k.getKoneksi(), customer);
                idCustomers = dao.queryGetNewIdCustomers(k.getKoneksi());
            }
            ///////////////////////////////////////////////////////////////

            if ((view.getTxtNik().getDocument().getLength()) < 14) {
                JOptionPane.showMessageDialog(view, "NIK masih kurang dari 14-digit");
            } else if ((view.getTxtNik().getDocument().getLength()) > 14) {
                JOptionPane.showMessageDialog(view, "NIK lebih dari 14-digit");
            } else if ((view.getTxtNoHp().getDocument().getLength()) < 12) {
                JOptionPane.showMessageDialog(view, "No. HP masih kurang dari 12-digit");
            } else if ((view.getTxtNoHp().getDocument().getLength()) > 13) {
                JOptionPane.showMessageDialog(view, "No. HP lebih dari 13-digit");
            } else {
                for (int i = 0; i < view.getTotalQuantity(); i++) {
                    total_harga = total_harga + dao.queryGetHargaProduk(k.getKoneksi(), view.getIdProduks().get(i));
                }
                if (view.getTxtBayar().getText().isBlank()) {
                    JOptionPane.showMessageDialog(view, "Masukkan Nominal Pembayaran");
                } else if (Integer.parseInt(view.getTxtBayar().getText()) < total_harga) {
                    JOptionPane.showMessageDialog(view, "Nominal Pembayaran tidak memenuhi");
                } else {

                    // Insert Transaksi ///////////////////////////////////////////
                    MKasir modelTransaksi = new MKasir(idCustomers,
                            selectedIdJPembayaran,
                            total_harga,
                            view.getTotalQuantity(),
                            view.getUser_id());
                    dao.queryInsertTransaksi(k.getKoneksi(), modelTransaksi);
                    ///////////////////////////////////////////////////////////////

                    for (int i = 0; i < view.getTotalQuantity(); i++) {
                        MKasir modelDtransaksi = new MKasir(view.getIdProduks().get(i),
                                view.getTblTransaksi().getValueAt(i, 3).toString(),
                                view.getIdWarnas().get(i),
                                dao.getIdTransaksi(k.getKoneksi()));
                        dao.queryInsertDTransaksi(k.getKoneksi(), modelDtransaksi);
                        dao.queryUpdateProduk(k.getKoneksi(), view.getIdProduks().get(i));
                        dao.queryUpdateStok(k.getKoneksi(), view.getTblTransaksi().getValueAt(i, 3).toString());
                    }
                    JOptionPane.showMessageDialog(view, "Insert Data Transaksi Berhasil");

                    view.getTxtNamaCustomer().setText("");
                    view.getTxtNik().setText("");
                    view.getTxtNoHp().setText("");
                    view.getTxtImei().setText("");
                    view.getTxaStruk().setText("");
                    view.getTxtBayar().setText("");

                    for (int i = 0; i < view.getTotalQuantity(); i++) {
                        view.getTblTransaksi().getModel().setValueAt(null, i, 0);
                        view.getTblTransaksi().getModel().setValueAt(null, i, 1);
                        view.getTblTransaksi().getModel().setValueAt(null, i, 2);
                        view.getTblTransaksi().getModel().setValueAt(null, i, 3);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }
    
    
//    public void cetak() throws ClassNotFoundException {
//    try {
//        k = new Koneksi();
//        dao = new DKasir();
//        int total_harga = 0;
//        int total_subsidi = 0;
//
//        // Get customer details
//        String namaCustomer = "John Doe"; // Replace with your input mechanism for customer name
//        String noHp = "08123456789"; // Replace with your input mechanism for customer phone number
//        String nik = "1234567890"; // Replace with your input mechanism for customer NIK
//
//        // Mendapatkan jumlah harga total 
//        int rowTabel = view.getTblTransaksi().getRowCount();
//
//        if (view.getTxaStruk().getText().isEmpty()) {
//            view.getTxaStruk().setText(view.getTxaStruk().getText() + "\t\tStruk\n");
//            view.getTxaStruk().setText(view.getTxaStruk().getText() + "--------------------------------------------------------------------------------------\n");
//
//            // Add customer details to the struk
//            view.getTxaStruk().setText(view.getTxaStruk().getText() + "Nama Customer: " + namaCustomer + "\n");
//            view.getTxaStruk().setText(view.getTxaStruk().getText() + "No HP: " + noHp + "\n");
//            view.getTxaStruk().setText(view.getTxaStruk().getText() + "NIK: " + nik + "\n");
//
//            view.getTxaStruk().setText(view.getTxaStruk().getText() + "--------------------------------------------------------------------------------------\n");
//            view.getTxaStruk().setText(view.getTxaStruk().getText() + "Nama Produk\t\tSubsidi\tHarga\n");
//            for (int i = 0; i < rowTabel; i++) {
//                
//                view.getTxaStruk().setText(view.getTxaStruk().getText() + view.getTblTransaksi().getValueAt(i, 1).toString() + "\t\t");
//                view.getTxaStruk().setText(view.getTxaStruk().getText() + "Rp" + dao.queryGetSubsidi(k.getKoneksi(), view.getIdProduks().get(i)) + "\t");
//                view.getTxaStruk().setText(view.getTxaStruk().getText() + "Rp" + dao.queryGetHarga(k.getKoneksi(), view.getIdProduks().get(i)) + "\n");
//                total_harga = total_harga + dao.queryGetHarga(k.getKoneksi(), view.getIdProduks().get(i));
//                total_subsidi = total_subsidi + dao.queryGetSubsidi(k.getKoneksi(), view.getIdProduks().get(i));
//            }
//            view.getTxaStruk().setText(view.getTxaStruk().getText() + "--------------------------------------------------------------------------------------\n");
//            view.getTxaStruk().setText(view.getTxaStruk().getText() + "Total\t\tRp" + total_subsidi + "\tRp" + total_harga + "\n");
//            view.getTxaStruk().setText(view.getTxaStruk().getText() + "Total Bayar\t\t\tRp" + (total_harga + total_subsidi) + "\n");
//        } else {
//            view.getTxaStruk().setText("");
//        }
//    } catch (ClassNotFoundException e) {
//        // Handle the exception
//        e.printStackTrace();
//    }
//}


    public void cetak() throws ClassNotFoundException {
        try {
            k = new Koneksi();
            dao = new DKasir();
            int total_harga = 0;
            int total_subsidi = 0;
            
//            String nama = "John Doe"; 
//            String no_hp = "08123456789"; 
//            String nik = "1234567890"; 

            // Mendapatkan jumlah harga total 
            int rowTabel = view.getTblTransaksi().getRowCount();

            if (view.getTxaStruk().getText().isEmpty()) {
                view.getTxaStruk().setText(view.getTxaStruk().getText() + "\t\tStruk\n");
                view.getTxaStruk().setText(view.getTxaStruk().getText() + "--------------------------------------------------------------------------------------\n");
                
//                view.getTxaStruk().setText(view.getTxaStruk().getText() + "Nama Customer: " + "nama" + "\n");
//                view.getTxaStruk().setText(view.getTxaStruk().getText() + "No HP: " + no_hp + "\n");
//                view.getTxaStruk().setText(view.getTxaStruk().getText() + "NIK: " + nik + "\n");
//                view.getTxaStruk().setText(view.getTxaStruk().getText() + "--------------------------------------------------------------------------------------\n");
//                
                view.getTxaStruk().setText(view.getTxaStruk().getText() + "Nama Produk\t\tSubsidi\tHarga\n");
                for (int i = 0; i < rowTabel+1; i++) {
                    view.getTxaStruk().setText(view.getTxaStruk().getText() + view.getTblTransaksi().getValueAt(i, 1).toString() + "\t\t");
                    view.getTxaStruk().setText(view.getTxaStruk().getText() + "Rp" + dao.queryGetSubsidi(k.getKoneksi(), view.getIdProduks().get(i)) + "\t");
                    view.getTxaStruk().setText(view.getTxaStruk().getText() + "Rp" + dao.queryGetHarga(k.getKoneksi(), view.getIdProduks().get(i)) + "\n");
                    total_harga = total_harga + dao.queryGetHarga(k.getKoneksi(), view.getIdProduks().get(i));
                    total_subsidi = total_subsidi + dao.queryGetSubsidi(k.getKoneksi(), view.getIdProduks().get(i));
                }
                view.getTxaStruk().setText(view.getTxaStruk().getText() + "--------------------------------------------------------------------------------------\n");
                view.getTxaStruk().setText(view.getTxaStruk().getText() + "Total\t\tRp" + total_subsidi + "\tRp" + total_harga + "\n");
                view.getTxaStruk().setText(view.getTxaStruk().getText() + "Total Bayar\t\t\tRp" + (total_harga + total_subsidi) + "\n");
                
                view.getTxaStruk().setText(view.getTxaStruk().getText() + "--------------------------------------------------------------------------------------\n");
                view.getTxaStruk().setText(view.getTxaStruk().getText() + "Nama Customer\t\tNIK\tNo.HP\n");
                view.getTxaStruk().setText(view.getTxaStruk().getText() + "Neisha Salsabila\t\085264979265\2345678909857\n");
                view.getTxaStruk().setText(view.getTxaStruk().getText() + "--------------------------------------------------------------------------------------\n");
                
                view.getTxaStruk().setText(view.getTxaStruk().getText() + "----------------------------Semoga Harimu Menyenangkan--------------------------------\n");
            } else {
                view.getTxaStruk().setText("");
            }

            //        BanyakBarang.setText(Integer.toString(controller.banyakBarang));       
            //        // Loop melalui setiap baris tabel dan tambahkan nilai kolom ke-6 ke variabel total
//        for (int i = 0; i < rowTabel; i++) {
//            total_belanja += Integer.parseInt(jTable.getValueAt(i, 5).toString());
//        }
//        txtTotalRp.setText(Integer.toString(total_belanja));
//        
            // Struk
            //        strukArea.setText(strukArea.getText() + "--------------------------------------------\n");
//        strukArea.setText(strukArea.getText() + "  " + "Total      \t\t\t Rp. " + total_belanja + "\n");
//        strukArea.setText(strukArea.getText() + "  " + "Bayar(Cash)\t\t\t Rp. " + txtBayar.getText() + "\n");
//        int kembalian = Integer.parseInt(txtBayar.getText()) - total_belanja;
//        if (kembalian < 0) {
//            strukArea.setText(strukArea.getText() + "  " + "Kembalian  \t\t\t Rp. 0\n");
//        } else {
//            strukArea.setText(strukArea.getText() + "  " + "Kembalian  \t\t\t Rp. " + kembalian + "\n");
//        }
//        strukArea.setText(strukArea.getText() + "\n\n\n");
//        strukArea.setText(strukArea.getText() + "          Terimakasih telah membeli          \n");
//        strukArea.setText(strukArea.getText() + "            makanan di tempat kami           \n");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void deleteDetail() {
        int i = view.getTblTransaksi().getSelectedRow();
        dao.deleteData(i);
    }

    public void viewDetail() {
        DefaultTableModel table = (DefaultTableModel) view.getTblTransaksi().getModel();
        table.setRowCount(0);
        List<Integer> idProduks = new ArrayList<>();
        List<Integer> idWarnas = new ArrayList<>();
        int i = 1;
        for (MKasir model : dao.getAllData()) {
            Object[] data = {
                i++,
                model.getNama_produk(),
                model.getWarna(),
                model.getImei()
            };
            idProduks.add(model.getProduk_id());
            idWarnas.add(model.getWarna_id());
            table.addRow(data);
        }
        view.setTotalQuantity(--i);
        view.setIdProduks(idProduks);
        view.setIdWarnas(idWarnas);
        i = 1;
    }

    public void isiDataProduk() {
        k = new Koneksi();
        dao = new DKasir();
        try {
            dao.querySelectProduk(k.getKoneksi(), view.getCboNamaProduk(), produk);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void isiDataWarna() {
        k = new Koneksi();
        dao = new DKasir();
        try {
            dao.querySelectWarna(k.getKoneksi(), view.getCboWarnaProduk(), warna);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void isiDataJPembayaran() {
        k = new Koneksi();
        dao = new DKasir();
        try {
            dao.querySelectJPembayaran(k.getKoneksi(), view.getCboJPembayaran(), jPembayaran);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void clear() {
        view.getTxtImei().setText("");
    }
}
