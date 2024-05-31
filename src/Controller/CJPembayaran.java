/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DJPembayaran;
import Koneksi.Koneksi;
import Model.MJPembayaran;
import View.VJPembayaran;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSI-PC
 */
public class CJPembayaran {
    
    Koneksi k;
    MJPembayaran model;
    VJPembayaran view;
    DJPembayaran dao;

    public CJPembayaran(VJPembayaran view) {
        this.view = view;
    }

    public void insert() throws ClassNotFoundException {
        if (view.getTxtNama().getText().isBlank()) {
            JOptionPane.showMessageDialog(view, "Tolong Isi Seluruh Data Terlebih Dahulu");
        } else {
            k = new Koneksi();
            model = new MJPembayaran(view.getTxtNama().getText(),
                    view.getCboKategori().getSelectedItem().toString());
            dao = new DJPembayaran();
            try {
                dao.queryInsert(k.getKoneksi(), model);
                JOptionPane.showMessageDialog(view, "Input Data Produk Berhasil");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        }
    }
    
    public void update() throws ClassNotFoundException {
        if (view.getTxtNama().getText().isBlank()) {
            JOptionPane.showMessageDialog(view, "Tolong Isi Seluruh Data Terlebih Dahulu");
        } else {
            k = new Koneksi();
            int i = view.getTblJPembayaran().getSelectedRow();          
            model = new MJPembayaran(view.getIds().get(i),
                    view.getTxtNama().getText(),
                    view.getCboKategori().getSelectedItem().toString());
            dao = new DJPembayaran();
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
        dao = new DJPembayaran();
        int i = view.getTblJPembayaran().getSelectedRow();
        try {
            dao.queryDelete(k.getKoneksi(), view.getIds().get(i));
            JOptionPane.showMessageDialog(view, "Delete Data Produk Berhasil");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void view() {
        DefaultTableModel table = (DefaultTableModel) view.getTblJPembayaran().getModel();
        table.setRowCount(0);
        k = new Koneksi();
        dao = new DJPembayaran();
        List<MJPembayaran> list;
        List<Integer> ids = new ArrayList<>();
        int i = 1;
        try {
            list = dao.querySelect(k.getKoneksi());
            for (MJPembayaran model : list) {
                Object data[] = {
                    i++,
                    model.getNama(),
                    model.getKategori()
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
        view.getTxtNama().setText(view.getTblJPembayaran().getValueAt(view.getTblJPembayaran().getSelectedRow(), 1).toString());
        view.getCboKategori().setSelectedItem(view.getTblJPembayaran().getValueAt(view.getTblJPembayaran().getSelectedRow(), 2));
    }
    
    public void clear() {
        view.getTxtNama().setText("");
    }
}
