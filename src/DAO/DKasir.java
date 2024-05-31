/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.MKasir;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.JComboBox;

/**
 *
 * @author MSI-PC
 */
public class DKasir {

    private List<MKasir> data = new ArrayList<>();

    public void addData(MKasir model) {
        data.add(model);
    }

    public void deleteData(int index) {
        data.remove(index);
    }

    public List<MKasir> getAllData() {
        return data;
    }

    public void queryInsertTransaksi(Connection con, MKasir model) throws SQLException {
        String query = "INSERT INTO transaksi(waktu, customer_id, jpembayaran_id, total_harga, total_quantity, user_id) VALUES(now(),?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, model.getCustomer_id());
        ps.setInt(2, model.getJpembayaran_id());
        ps.setInt(3, model.getTotal_harga());
        ps.setInt(4, model.getTotal_quantity());
        ps.setInt(5, model.getUser_id());
        ps.executeUpdate();
    }

    public void queryInsertDTransaksi(Connection con, MKasir model) throws SQLException {
        String query = "INSERT INTO detail_transaksi(produk_id, imei, warna_id, transaksi_id) VALUES(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, model.getProduk_id());
        ps.setString(2, model.getImei());
        ps.setInt(3, model.getWarna_id());
        ps.setInt(4, model.getTransaksi_id());
        ps.executeUpdate();
    }

    public void queryInsertCustomers(Connection con, MKasir model) throws SQLException {
        String query = "INSERT INTO customers(nama, nik, no_hp) VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, model.getNama_customer());
        ps.setString(2, model.getNik());
        ps.setString(3, model.getNo_hp());
        ps.executeUpdate();
    }

    public void queryUpdateCustomers(Connection con, String no_hp, int id) throws SQLException {
        String query = "UPDATE customers SET no_hp=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, no_hp);
        ps.setInt(2, id);
        ps.executeUpdate();
    }

    public int queryGetIdCustomers(Connection con, String nik) throws SQLException {
        String query = "SELECT id FROM customers WHERE nik=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, nik);
        ResultSet rs = ps.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public int queryGetNewIdCustomers(Connection con) throws SQLException {
        String query = "SELECT id FROM customers ORDER BY id DESC LIMIT 1";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public int queryCountCustomers(Connection con, String nik) throws SQLException {
        String query = "SELECT COUNT(*) as count FROM customers WHERE nik=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, nik);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        while (rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }
    
    public int queryCountNoHp(Connection con, String no_hp) throws SQLException {
        String query = "SELECT COUNT(*) as count FROM customers WHERE no_hp=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, no_hp);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        while (rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }

    public String queryGetNoHp(Connection con, int id) throws SQLException {
        String query = "SELECT no_hp FROM customers WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        String no_hp = null;
        while (rs.next()) {
            no_hp = rs.getString("no_hp");
        }
        return no_hp;
    }

    public void querySelectProduk(Connection con, JComboBox comboBox, Map<String, Integer> produk) throws SQLException {
        String query = "SELECT * FROM produk";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            produk.put(rs.getString("nama"), rs.getInt("id"));
            comboBox.addItem(rs.getString("nama"));
        }
    }

    public void querySelectWarna(Connection con, JComboBox comboBox, Map<String, Integer> warna) throws SQLException {
        String query = "SELECT * FROM warna";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            warna.put(rs.getString("nama"), rs.getInt("id"));
            comboBox.addItem(rs.getString("nama"));
        }
    }

    public void querySelectJPembayaran(Connection con, JComboBox comboBox, Map<String, Integer> jpembayaran) throws SQLException {
        String query = "SELECT * FROM jenis_pembayaran";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            jpembayaran.put(rs.getString("nama"), rs.getInt("id"));
            comboBox.addItem(rs.getString("nama"));
        }
    }

    public int queryCountImei(Connection con, String imei, int produk_id, int warna_id) throws SQLException {
        String query = "SELECT COUNT(imei) as count FROM detail_stok WHERE imei=? AND produk_id=? AND warna_id=? AND status=false";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, imei);
        ps.setInt(2, produk_id);
        ps.setInt(3, warna_id);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        while (rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }

    public int queryGetHargaProduk(Connection con, int id) throws SQLException {
        String query = "SELECT harga + subsidi as harga FROM produk WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        int harga = 0;
        while (rs.next()) {
            harga = rs.getInt("harga");
        }
        return harga;
    }
    
    public int queryGetHarga(Connection con, int id) throws SQLException {
        String query = "SELECT harga FROM produk WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        int harga = 0;
        while (rs.next()) {
            harga = rs.getInt("harga");
        }
        return harga;
    }
    
    public int queryGetSubsidi(Connection con, int id) throws SQLException {
        String query = "SELECT subsidi FROM produk WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        int subsidi = 0;
        while (rs.next()) {
            subsidi = rs.getInt("subsidi");
        }
        return subsidi;
    }

    public int getIdTransaksi(Connection con) throws SQLException {
        String query = "SELECT id FROM transaksi ORDER BY id DESC LIMIT 1";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public void queryUpdateProduk(Connection con, int produk_id) throws SQLException {
        String query = "UPDATE produk SET quantity = quantity - 1  WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, produk_id);
        ps.executeUpdate();
    }

    public void queryUpdateStok(Connection con, String imei) throws SQLException {
        String query = "UPDATE detail_stok SET status = 1  WHERE imei = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, imei);
        ps.executeUpdate();
    }

//    public List<String> querySelectNiks(Connection con) throws SQLException {
//        String query = "SELECT nik FROM customers";
//        PreparedStatement ps = con.prepareStatement(query);
//        ResultSet rs = ps.executeQuery();
//        List<String> niks = new ArrayList<>();
//        while (rs.next()) {
//            niks.add(rs.getString("nik"));
//        }
//        return niks;
//    }
//    public int queryCountWarna(Connection con, int produk_id, int warna_id) throws SQLException {
//        String query = "SELECT COUNT(*) as count FROM detail_stok ds WHERE produk_id=? AND warna_id=? AND status=false";
//        PreparedStatement ps = con.prepareStatement(query);
//        ps.setInt(1, produk_id);
//        ps.setInt(2, warna_id);
//        ResultSet rs = ps.executeQuery();
//        int count = 0;
//        while (rs.next()) {
//            count = rs.getInt("count");
//        }
//        return count;
//    }
//    
//    public void queryInsertDStok(Connection con, MStok model) throws SQLException {
//        String query = "INSERT INTO detail_stok(produk_id, imei, warna_id, stok_id) VALUES(?,?,?,?)";
//        PreparedStatement ps = con.prepareStatement(query);
//        ps.setInt(1, model.getProduk_id());
//        ps.setString(2, model.getImei());
//        ps.setInt(3, model.getWarna_id());
//        ps.setInt(4, model.getStok_id());
//        ps.executeUpdate();
//    }
//    
//    public void queryUpdateProduk(Connection con, int produk_id) throws SQLException {
//        String query = "UPDATE produk SET quantity = quantity + 1  WHERE id = ?";
//        PreparedStatement ps = con.prepareStatement(query);
//        ps.setInt(1, produk_id);
//        ps.executeUpdate();
//    }
//    
//    public int getIdStok(Connection con) throws SQLException {
//        String query = "SELECT * FROM stok ORDER BY id DESC LIMIT 1";
//        PreparedStatement ps = con.prepareStatement(query);
//        ResultSet rs = ps.executeQuery();
//        int id = 0;
//        while (rs.next()) {
//            id = rs.getInt("id");
//        }
//        return id;
//    }
//
//    
//    public int querySelectWarna(Connection con, String nama) throws SQLException {
//        String query = "SELECT COUNT(*) as count FROM warna WHERE UPPER(nama) = UPPER(?)";
//        PreparedStatement ps = con.prepareStatement(query);
//        ps.setString(1, nama);
//        ResultSet rs = ps.executeQuery();
//        int count = 0;
//        while (rs.next()) {
//            count = rs.getInt("count");
//        }
//        return count;
//    }
//    
//    public void queryInsertWarna(Connection con, String nama) throws SQLException {
//        String query = "INSERT INTO warna(nama) VALUES(UPPER(?))";
//        PreparedStatement ps = con.prepareStatement(query);
//        ps.setString(1, nama);
//        ps.executeUpdate();
//    }
//    
//    public int queryGetWarna(Connection con, String nama) throws SQLException {
//        String query = "SELECT * FROM warna WHERE UPPER(nama) = UPPER(?)";
//        PreparedStatement ps = con.prepareStatement(query);
//        ps.setString(1, nama);
//        ResultSet rs = ps.executeQuery();
//        int id = 0;
//        while (rs.next()) {
//            id = rs.getInt("id");
//        }
//        return id;
//    }
//    
//    public int querySelectImei(Connection con, String imei) throws SQLException {
//        String query = "SELECT COUNT(*) as count FROM detail_stok WHERE imei = ? AND status=false";
//        PreparedStatement ps = con.prepareStatement(query);
//        ps.setString(1, imei);
//        ResultSet rs = ps.executeQuery();
//        int count = 0;
//        while (rs.next()) {
//            count = rs.getInt("count");
//        }
//        return count;
//    }
}
