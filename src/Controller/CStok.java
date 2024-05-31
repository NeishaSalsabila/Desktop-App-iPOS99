/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DStok;
import Koneksi.Koneksi;
import Model.MStok;
import View.VStok;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MSI-PC
 */
public class CStok {

    Koneksi k;
    MStok model;
    VStok view;
    DStok dao;
    private Map<String, Integer> namaToIdMap = new HashMap<>();

    public CStok(VStok view) {
        this.view = view;
    }

    public void insertDetail() throws ClassNotFoundException {
        if (view.getTxtImei().getText().isBlank()
                || view.getTxtWarna().getText().isBlank()) {
            JOptionPane.showMessageDialog(view, "Tolong Isi Seluruh Data Terlebih Dahulu");
        } else {
            String selectedNama = (String) view.getCboNamaProduk().getSelectedItem();
            int selectedId = namaToIdMap.get(selectedNama);
            try {
                // is Warna Available? /////////////////////////////////////////
                int countWarna = dao.querySelectWarna(k.getKoneksi(), view.getTxtWarna().getText());
                if (countWarna == 0) {
                    dao.queryInsertWarna(k.getKoneksi(), view.getTxtWarna().getText());
                }
                int id_warna = dao.queryGetWarna(k.getKoneksi(), view.getTxtWarna().getText());
                ////////////////////////////////////////////////////////////////

                // is IMEI Available? //////////////////////////////////////////
                int status = 0;
                for (int i = 0; i < view.getTotalQuantity(); i++) {
                    if (view.getTxtImei().getText().equals(view.getTblStok().getValueAt(i, 2))) {
                        status = 1;
                    }
                }
                
                int countImei = dao.querySelectImei(k.getKoneksi(), view.getTxtImei().getText());
                if (countImei > 0) {
                    JOptionPane.showMessageDialog(null, "IMEI sudah Tersedia");
                }
                else if (status == 1) {
                    JOptionPane.showMessageDialog(view, "IMEI sudah Tersedia");
                }
                else {
                    model = new MStok(selectedId,
                            view.getCboNamaProduk().getSelectedItem().toString(),
                            view.getTxtImei().getText(),
                            view.getTxtWarna().getText(),
                            id_warna);
                    dao.addData(model);
                }
                ////////////////////////////////////////////////////////////////
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    public void deleteDetail() {
        int i = view.getTblStok().getSelectedRow();
        dao.deleteData(i);
    }

    public void Konfirmasi() throws ClassNotFoundException {
        k = new Koneksi();
        MStok modelStok = new MStok(view.getTotalQuantity(),
                view.getUser_id());
        dao = new DStok();
        try {
            dao.queryInsertStok(k.getKoneksi(), modelStok);
            for (int i = 0; i < view.getTotalQuantity(); i++) {
                MStok modelDstok = new MStok(view.getIdProduks().get(i),
                        view.getTblStok().getValueAt(i, 2).toString(),
                        view.getIdWarnas().get(i),
                        dao.getIdStok(k.getKoneksi()));
                dao.queryInsertDStok(k.getKoneksi(), modelDstok);
                dao.queryUpdateProduk(k.getKoneksi(), view.getIdProduks().get(i));
            }
            
            for (int i = 0; i < view.getTotalQuantity(); i++) {
                view.getTblStok().getModel().setValueAt(null, i, 0);
                view.getTblStok().getModel().setValueAt(null, i, 1);
                view.getTblStok().getModel().setValueAt(null, i, 2);
                view.getTblStok().getModel().setValueAt(null, i, 3);
            }
            
            JOptionPane.showMessageDialog(view, "Insert Data Stok Berhasil");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Insert Data Stok Gagal");
        }
    }

    public void viewDetail() {
        DefaultTableModel table = (DefaultTableModel) view.getTblStok().getModel();
        table.setRowCount(0);
        List<Integer> idProduks = new ArrayList<>();
        List<Integer> idWarnas = new ArrayList<>();
        int i = 1;
        for (MStok model : dao.getAllData()) {
            Object[] data = {
                i++,
                model.getNama_produk(),
                model.getImei(),
                model.getWarna()
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
        dao = new DStok();
        try {
            dao.querySelectProduk(k.getKoneksi(), view.getCboNamaProduk(), namaToIdMap);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void clear() {
        view.getTxtImei().setText("");
        view.getTxtWarna().setText("");
    }
}
