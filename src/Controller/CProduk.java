/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DProduk;
import Koneksi.Koneksi;
import Model.MProduk;
import View.VProduk;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSI-PC
 */
public class CProduk {

    Koneksi k;
    MProduk model;
    VProduk view;
    DProduk dao;

    public CProduk(VProduk view) {
        this.view = view;
    }

    public void insert() throws ClassNotFoundException {
        if (view.getTxtNamaProduk().getText().isBlank()
                || view.getTxtHarga().getText().isBlank()
                || view.getTxtSubsidi().getText().isBlank()
                || view.getTxtModal().getText().isBlank()) {
            JOptionPane.showMessageDialog(view, "Tolong Isi Seluruh Data Terlebih Dahulu");
        } else {
            k = new Koneksi();
            model = new MProduk(view.getTxtNamaProduk().getText(),
                    Integer.parseInt(view.getTxtHarga().getText()),
                    Integer.parseInt(view.getTxtSubsidi().getText()),
                    Integer.parseInt(view.getTxtModal().getText()));
            dao = new DProduk();
            try {
                dao.queryInsert(k.getKoneksi(), model);
                JOptionPane.showMessageDialog(view, "Input Data Produk Berhasil");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        }
    }
    
    public void update() throws ClassNotFoundException {
        if (view.getTxtNamaProduk().getText().isBlank()
                || view.getTxtHarga().getText().isBlank()
                || view.getTxtSubsidi().getText().isBlank()
                || view.getTxtModal().getText().isBlank()) {
            JOptionPane.showMessageDialog(view, "Tolong Isi Seluruh Data Terlebih Dahulu");
        } else {
            k = new Koneksi();
            int i = view.getTblProduk().getSelectedRow();          
            model = new MProduk(view.getIds().get(i),
                    view.getTxtNamaProduk().getText(),
                    Integer.parseInt(view.getTxtHarga().getText()),
                    Integer.parseInt(view.getTxtSubsidi().getText()),
                    Integer.parseInt(view.getTxtModal().getText()));
            dao = new DProduk();
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
        dao = new DProduk();
        int i = view.getTblProduk().getSelectedRow();
        try {
            dao.queryDelete(k.getKoneksi(), view.getIds().get(i));
            JOptionPane.showMessageDialog(view, "Delete Data Produk Berhasil");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void view() {
        DefaultTableModel table = (DefaultTableModel) view.getTblProduk().getModel();
        table.setRowCount(0);
        k = new Koneksi();
        dao = new DProduk();
        List<MProduk> list;
        List<Integer> ids = new ArrayList<>();
        int i = 1;
        try {
            list = dao.querySelect(k.getKoneksi());
            for (MProduk model : list) {
                Object data[] = {
                    i++,
                    model.getNama(),
                    model.getHarga(),
                    model.getModal(),
                    model.getSubsidi(),
                    model.getQuantity()
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
        view.getTxtNamaProduk().setText(view.getTblProduk().getValueAt(view.getTblProduk().getSelectedRow(), 1).toString());
        view.getTxtHarga().setText(view.getTblProduk().getValueAt(view.getTblProduk().getSelectedRow(), 2).toString());
        view.getTxtModal().setText(view.getTblProduk().getValueAt(view.getTblProduk().getSelectedRow(), 3).toString());
        view.getTxtSubsidi().setText(view.getTblProduk().getValueAt(view.getTblProduk().getSelectedRow(), 4).toString());
    }
    
    public void clear() {
        view.getTxtNamaProduk().setText("");
        view.getTxtHarga().setText("");
        view.getTxtSubsidi().setText("");
        view.getTxtModal().setText("");
    }
}
