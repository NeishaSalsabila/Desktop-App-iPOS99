/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DCustomer;
import Koneksi.Koneksi;
import Model.MCustomer;
import View.VCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI-PC
 */
public class CCustomer {

    Koneksi k;
    VCustomer view;
    MCustomer model;
    DCustomer dao;

    public CCustomer(VCustomer view) {
        this.view = view;
    }

    public void update() throws ClassNotFoundException {
        if (view.getTxtNamaCustomer().getText().isBlank()
                || view.getTxtNik().getText().isBlank()
                || view.getTxtNoHp().getText().isBlank()) {
            JOptionPane.showMessageDialog(view, "Tolong Isi Seluruh Data Terlebih Dahulu");
        } else {
            k = new Koneksi();
            int i = view.getTblCustomer().getSelectedRow();
            model = new MCustomer(view.getIds().get(i),
                    view.getTxtNamaCustomer().getText(),
                    view.getTxtNik().getText(),
                    view.getTxtNoHp().getText());
            dao = new DCustomer();
            try {
                dao.queryUpdate(k.getKoneksi(), model);
                JOptionPane.showMessageDialog(view, "Update Data Produk Berhasil");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        }
    }

    public void delete() throws ClassNotFoundException {
        k = new Koneksi();
        dao = new DCustomer();
        int i = view.getTblCustomer().getSelectedRow();
        try {
            int count = dao.queryTransaksi(k.getKoneksi(), view.getIds().get(i));
            if (count > 0) {
                JOptionPane.showMessageDialog(view, "Data Customer tidak bisa dihapus, Karena memiliki riwayat Transaksi");
            } else {
                dao.queryDelete(k.getKoneksi(), view.getIds().get(i));
                JOptionPane.showMessageDialog(view, "Delete Data Produk Berhasil");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void view() {
        DefaultTableModel table = (DefaultTableModel) view.getTblCustomer().getModel();
        table.setRowCount(0);
        k = new Koneksi();
        dao = new DCustomer();
        List<MCustomer> list;
        List<Integer> ids = new ArrayList<>();
        int i = 1;
        try {
            list = dao.querySelect(k.getKoneksi());
            for (MCustomer model : list) {
                Object data[] = {
                    i++,
                    model.getNama(),
                    model.getNik(),
                    model.getNo_hp()
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

    public void search() {
        view.getTxtNamaCustomer().setText(view.getTblCustomer().getValueAt(view.getTblCustomer().getSelectedRow(), 1).toString());
        view.getTxtNik().setText(view.getTblCustomer().getValueAt(view.getTblCustomer().getSelectedRow(), 2).toString());
        view.getTxtNoHp().setText(view.getTblCustomer().getValueAt(view.getTblCustomer().getSelectedRow(), 3).toString());
    }

    public void clear() {
        view.getTxtNamaCustomer().setText("");
        view.getTxtNik().setText("");
        view.getTxtNoHp().setText("");
    }
}
