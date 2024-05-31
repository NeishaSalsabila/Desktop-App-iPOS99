/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.MDetailTransaksi;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;

/**
 *
 * @author MSI-PC
 */
public class DDetailTransaksi {
    
    public void queryUpdate(Connection con, MDetailTransaksi model) throws SQLException {
        String query = "UPDATE detail_transaksi SET produk_id = ?, imei = ?, warna_id = ?, transaksi_id = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, model.getProduk_id());
        ps.setString(2, model.getImei());
        ps.setInt(3, model.getWarna_id());
        ps.setInt(4, model.getTransaksi_id());
        ps.setInt(5, model.getId());
        ps.executeUpdate();
    }
    
    public void queryDelete(Connection con, int id) throws SQLException {
        String query = "DELETE FROM detail_transaksi WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public List<MDetailTransaksi> querySelect(Connection con, int transaksi_id) throws SQLException {
        String query = "SELECT dt.id as id,  p.nama as nama_produk, dt.imei as imei, w.nama as warna "
                + "FROM detail_transaksi dt JOIN produk p ON (dt.produk_id = p.id) "
                + "JOIN warna w ON(dt.warna_id = w.id) "
                + "WHERE dt.transaksi_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, transaksi_id);
        ResultSet rs = ps.executeQuery();
        List<MDetailTransaksi> list = new ArrayList<>();
        while (rs.next()) {
            MDetailTransaksi model = new MDetailTransaksi(rs.getInt("id"),
                    rs.getString("nama_produk"),
                    rs.getString("imei"),
                    rs.getString("warna"));
            list.add(model);
        }
        return list;
    }

    public int querySelectWarna(Connection con, String nama) throws SQLException {
        String query = "SELECT COUNT(*) as count FROM warna WHERE UPPER(nama) = UPPER(?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, nama);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        while (rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }

    public void queryInsertWarna(Connection con, String nama) throws SQLException {
        String query = "INSERT INTO warna(nama) VALUES(UPPER(?))";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, nama);
        ps.executeUpdate();
    }

    public int queryGetWarna(Connection con, String nama) throws SQLException {
        String query = "SELECT * FROM warna WHERE UPPER(nama) = UPPER(?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, nama);
        ResultSet rs = ps.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public int querySelectImei(Connection con, String imei) throws SQLException {
        String query = "SELECT COUNT(*) as count FROM detail_stok WHERE imei = ? AND status=false";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, imei);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        while (rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }
    
    public void querySelectProduk(Connection con, JComboBox comboBox, Map<String, Integer> namaToIdMap) throws SQLException {
        String query = "SELECT * FROM produk";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            namaToIdMap.put(rs.getString("nama"), rs.getInt("id"));
            comboBox.addItem(rs.getString("nama"));
        }
    }
    
    public int queryCountDStok(Connection con, int transaksi_id) throws SQLException {
        String query = "SELECT COUNT(*) as count FROM detail_transaksi WHERE transaksi_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, transaksi_id);
        ResultSet rs = ps.executeQuery();
        int count = -1;
        while (rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }
    
    public void queryDeleteStok(Connection con, int id) throws SQLException {
        String query = "DELETE FROM transaksi WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    
    public void queryUpdateProduk(Connection con, int produk_id) throws SQLException {
        String query = "UPDATE produk SET quantity = quantity + 1  WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, produk_id);
        ps.executeUpdate();
    }
    
    public void queryUpdateStok(Connection con, String imei) throws SQLException {
        String query = "UPDATE detail_stok SET status = 0  WHERE imei = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, imei);
        ps.executeUpdate();
    }
}
