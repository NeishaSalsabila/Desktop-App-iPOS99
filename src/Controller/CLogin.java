/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DLogin;
import Koneksi.Koneksi;
import Model.MLogin;
import View.VHome;
import View.VKasir;
import View.VLogin;
import View.VSupplier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI-PC
 */
public class CLogin {

    Koneksi k;
    VLogin view;
    DLogin dao;
    MLogin model;
    VHome home;
    VKasir kasir;
    VSupplier supplier;

    public CLogin(VLogin view) {
        this.view = view;
    }

    public void login() throws ClassNotFoundException {
        k = new Koneksi();
        dao = new DLogin();
        model = new MLogin(view.getTxtUsername().getText(),
                view.getPsfPassword().getText());
        List<MLogin> list = new ArrayList<>();
        try {
            list = dao.login(k.getKoneksi(), model);
            JOptionPane.showMessageDialog(view, "Anda Berhasil Login");
            for (MLogin model : list) {
                if (model.getLevel_akses().equals("Admin")) {
                    home = new VHome(model.getLevel_akses(), model.getId());
                    view.dispose();
                    home.setVisible(true);
                    break;
                } else if (model.getLevel_akses().equals("Kasir")) {
                    kasir = new VKasir(model.getLevel_akses(), model.getId());
                    view.dispose();
                    kasir.setVisible(true);
                    break;
                } else if (model.getLevel_akses().equals("Supplier")) {
                    supplier = new VSupplier(model.getLevel_akses(), model.getId());
                    view.dispose();
                    supplier.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(view, "Anda Gagal Login");
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Anda Gagal Login");
        }

    }
}
