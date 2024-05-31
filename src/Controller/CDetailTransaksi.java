/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DDetailTransaksi;
import Koneksi.Koneksi;
import Model.MDetailTransaksi;
import View.VDetailTransaksi;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MSI-PC
 */
public class CDetailTransaksi {

    Koneksi k;
    VDetailTransaksi view;
    MDetailTransaksi model;
    DDetailTransaksi dao;

    private Map<String, Integer> produk = new HashMap<>();

    public CDetailTransaksi(VDetailTransaksi view) {
        this.view = view;
    }

    public void update() throws ClassNotFoundException {
        if (view.getTxtImei().getText().isBlank()
                || view.getTxtWarna().getText().isBlank()) {
            JOptionPane.showMessageDialog(view, "Tolong Isi Seluruh Data Terlebih Dahulu");
        } else {
            k = new Koneksi();
            dao = new DDetailTransaksi();
            int i = view.getTblDetailTransaksi().getSelectedRow();

            String selectedProduk = (String) view.getCboNamaProduk().getSelectedItem();
            int idProduk = produk.get(selectedProduk);

            try {
                // is Warna Available? /////////////////////////////////////////
                int countWarna = dao.querySelectWarna(k.getKoneksi(), view.getTxtWarna().getText());
                if (countWarna == 0) {
                    dao.queryInsertWarna(k.getKoneksi(), view.getTxtWarna().getText());
                }
                int id_warna = dao.queryGetWarna(k.getKoneksi(), view.getTxtWarna().getText());
                ////////////////////////////////////////////////////////////////

                // is IMEI Available?///////////////////////////////////////////
                int countImei = dao.querySelectImei(k.getKoneksi(), view.getTxtImei().getText());
                if (countImei > 0) {
                    JOptionPane.showMessageDialog(null, "IMEI sudah Tersedia");
                } else {
                    model = new MDetailTransaksi(view.getIds().get(i),
                            idProduk,
                            view.getTxtImei().getText(),
                            id_warna,
                            Integer.parseInt(view.getTxtId().getText()));
                    dao.queryUpdate(k.getKoneksi(), model);
                    JOptionPane.showMessageDialog(view, "Update Data Transaksi Berhasil");
                }
                ////////////////////////////////////////////////////////////////
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        }
    }

    public void delete() throws ClassNotFoundException {
        k = new Koneksi();
        dao = new DDetailTransaksi();
        int i = view.getTblDetailTransaksi().getSelectedRow();

        try {
            String selectedProduk = (String) view.getCboNamaProduk().getSelectedItem();
            int idProduk = produk.get(selectedProduk);
            dao.queryDelete(k.getKoneksi(), view.getIds().get(i));
            dao.queryUpdateProduk(k.getKoneksi(), idProduk);
            dao.queryUpdateStok(k.getKoneksi(), view.getTblDetailTransaksi().getValueAt(i, 2).toString());

            // is detail_stok empty? //////////////////////////////////////////////
            int count;
            count = dao.queryCountDStok(k.getKoneksi(), Integer.parseInt(view.getTxtId().getText()));
            if (count == 0) {
                dao.queryDeleteStok(k.getKoneksi(), Integer.parseInt(view.getTxtId().getText()));
                view.dispose();
            }
            ////////////////////////////////////////////////////////////////////
            JOptionPane.showMessageDialog(view, "Hapus Data Transaksi Berhasil");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void view() {
        DefaultTableModel table = (DefaultTableModel) view.getTblDetailTransaksi().getModel();
        table.setRowCount(0);
        k = new Koneksi();
        dao = new DDetailTransaksi();
        List<MDetailTransaksi> list;
        List<Integer> ids = new ArrayList<>();
        int i = 1;
        try {
            list = dao.querySelect(k.getKoneksi(), Integer.parseInt(view.getTxtId().getText()));
            for (MDetailTransaksi model : list) {
                String status = null;
                Object data[] = {
                    i++,
                    model.getNama_produk(),
                    model.getImei(),
                    model.getWarna()
                };
                table.addRow(data);
                ids.add(model.getId());
            }
            view.setIds(ids);
            i = 1;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void isiDataProduk() {
        k = new Koneksi();
        dao = new DDetailTransaksi();
        try {
            dao.querySelectProduk(k.getKoneksi(), view.getCboNamaProduk(), produk);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void search() {
        view.getCboNamaProduk().setSelectedItem(view.getTblDetailTransaksi().getValueAt(view.getTblDetailTransaksi().getSelectedRow(), 1));
        view.getTxtImei().setText(view.getTblDetailTransaksi().getValueAt(view.getTblDetailTransaksi().getSelectedRow(), 2).toString());
        view.getTxtWarna().setText(view.getTblDetailTransaksi().getValueAt(view.getTblDetailTransaksi().getSelectedRow(), 3).toString());
    }

    public void clear() {
        view.getTxtImei().setText("");
        view.getTxtWarna().setText("");
    }

}
