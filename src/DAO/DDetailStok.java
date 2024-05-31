/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.MDetailStok;
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
public class DDetailStok {

    public void queryUpdate(Connection con, MDetailStok model) throws SQLException {
        String query = "UPDATE detail_stok SET produk_id = ?, imei = ?, warna_id = ?, stok_id = ?, status =? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, model.getProduk_id());
        ps.setString(2, model.getImei());
        ps.setInt(3, model.getWarna_id());
        ps.setInt(4, model.getStok_id());
        ps.setInt(5, model.getStatus());
        ps.setInt(6, model.getId());
        ps.executeUpdate();
    }
    
    public void queryDelete(Connection con, int id) throws SQLException {
        String query = "DELETE FROM detail_stok WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public List<MDetailStok> querySelect(Connection con, int stok_id) throws SQLException {
        String query = "SELECT ds.id as id,  p.nama as nama_produk, ds.imei as imei, w.nama as warna, ds.status as status "
                + "FROM detail_stok ds JOIN produk p ON (ds.produk_id = p.id) "
                + "JOIN warna w ON(ds.warna_id = w.id) "
                + "WHERE ds.stok_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, stok_id);
        ResultSet rs = ps.executeQuery();
        List<MDetailStok> list = new ArrayList<>();
        while (rs.next()) {
            MDetailStok model = new MDetailStok(rs.getInt("id"),
                    rs.getString("nama_produk"),
                    rs.getString("imei"),
                    rs.getString("warna"),
                    rs.getInt("status"));
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
    
    public int queryCountDStok(Connection con, int stok_id) throws SQLException {
        String query = "SELECT COUNT(*) as count FROM detail_stok WHERE stok_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, stok_id);
        ResultSet rs = ps.executeQuery();
        int count = -1;
        while (rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }
    
    public void queryDeleteStok(Connection con, int id) throws SQLException {
        String query = "DELETE FROM stok WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    
    public void queryUpdateProduk(Connection con, int produk_id) throws SQLException {
        String query = "UPDATE produk SET quantity = quantity - 1  WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, produk_id);
        ps.executeUpdate();
    }
}
