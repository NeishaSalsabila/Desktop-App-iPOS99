/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DUser;
import Koneksi.Koneksi;
import Model.MUser;
import View.VUser;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSI-PC
 */
public class CUser {
    
    Koneksi k;
    MUser model;
    VUser view;
    DUser dao;

    public CUser(VUser view) {
        this.view = view;
    }

    public void insert() throws ClassNotFoundException {
        if (view.getTxtUsername().getText().isBlank()
                || view.getTxtPassword().getText().isBlank()
                || view.getTxtNamaUser().getText().isBlank()) {
            JOptionPane.showMessageDialog(view, "Tolong Isi Seluruh Data Terlebih Dahulu");
        } else {
            k = new Koneksi();
            model = new MUser(view.getTxtUsername().getText(),
                    view.getTxtPassword().getText(),
                    view.getTxtNamaUser().getText(),
                    view.getCboLevelAkses().getSelectedItem().toString());
            dao = new DUser();
            try {
                dao.queryInsert(k.getKoneksi(), model);
                JOptionPane.showMessageDialog(view, "Input Data User Berhasil");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Username sudah digunakan");
            }
        }
    }
    
    public void update() throws ClassNotFoundException {
        if (view.getTxtUsername().getText().isBlank()
                || view.getTxtPassword().getText().isBlank()
                || view.getTxtNamaUser().getText().isBlank()) {
            JOptionPane.showMessageDialog(view, "Tolong Isi Seluruh Data Terlebih Dahulu");
        } else {
            k = new Koneksi();
            int i = view.getTblUser().getSelectedRow();          
            model = new MUser(view.getIds().get(i),
                    view.getTxtUsername().getText(),
                    view.getTxtPassword().getText(),
                    view.getTxtNamaUser().getText(),
                    view.getCboLevelAkses().getSelectedItem().toString());
            dao = new DUser();
            try {
                dao.queryUpdate(k.getKoneksi(), model);
                JOptionPane.showMessageDialog(view, "Update Data User Berhasil");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        }
    }
    
    public void delete() throws ClassNotFoundException {
        k = new Koneksi();
        dao = new DUser();
        int i = view.getTblUser().getSelectedRow();
        try {
            dao.queryDelete(k.getKoneksi(), view.getIds().get(i));
            JOptionPane.showMessageDialog(view, "Delete Data User Berhasil");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    public void view() {
        DefaultTableModel table = (DefaultTableModel) view.getTblUser().getModel();
        table.setRowCount(0);
        k = new Koneksi();
        dao = new DUser();
        List<MUser> list;
        List<Integer> ids = new ArrayList<>();
        int i = 1;
        try {
            list = dao.querySelect(k.getKoneksi());
            for (MUser model : list) {
                Object data[] = {
                    i++,
                    model.getUsername(),
                    model.getPassword(),
                    model.getNama(),
                    model.getLevel_akses()
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
        view.getTxtUsername().setText(view.getTblUser().getValueAt(view.getTblUser().getSelectedRow(), 1).toString());
        view.getTxtPassword().setText(view.getTblUser().getValueAt(view.getTblUser().getSelectedRow(), 2).toString());
        view.getTxtNamaUser().setText(view.getTblUser().getValueAt(view.getTblUser().getSelectedRow(), 3).toString());
        view.getCboLevelAkses().setSelectedItem(view.getTblUser().getValueAt(view.getTblUser().getSelectedRow(), 4).toString());
    }
    
    public void clear() {
        view.getTxtUsername().setText("");
        view.getTxtPassword().setText("");
        view.getTxtNamaUser().setText("");
    }
}
