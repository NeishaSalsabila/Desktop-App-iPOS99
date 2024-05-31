/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DStokAdmin;
import Koneksi.Koneksi;
import Model.MStokAdmin;
import View.VStokAdmin;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

/**
 *
 * @author MSI-PC
 */
public class CStokAdmin {
    Koneksi k;
    VStokAdmin view;
    DStokAdmin dao;

    public CStokAdmin(VStokAdmin view) {
        this.view = view;
    }
    
    public void view() {
        DefaultTableModel table = (DefaultTableModel) view.getTblStokAdmin().getModel();
        table.setRowCount(0);
        k = new Koneksi();
        dao = new DStokAdmin();
        List<MStokAdmin> list;
        List<Integer> ids = new ArrayList<>();
        int i = 1;
        try {
            list = dao.querySelect(k.getKoneksi());
            for (MStokAdmin model : list) {
                Object data[] = {
                    i++,
                    model.getTgl(),
                    model.getWaktu(),
                    model.getNama_user(),
                    model.getTotal_quantity()
                };
                table.addRow(data);
                ids.add(model.getId_stok());
            }
            view.setIds(ids);
            i = 1;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }
    
    public void show() {
        int i = view.getTblStokAdmin().getSelectedRow();
        int id = view.getIds().get(i);
        view.setId(id);
    }
}
