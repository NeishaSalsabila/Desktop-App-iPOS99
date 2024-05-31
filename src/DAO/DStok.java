/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.MStok;
import java.sql.Connection;
import java.util.Map;
import javax.swing.JComboBox;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI-PC
 */
public class DStok {

    private List<MStok> data = new ArrayList<>();

    public void addData(MStok model) {
        data.add(model);
    }

    public void deleteData(int index) {
        data.remove(index);
    }

    public void queryInsertStok(Connection con, MStok model) throws SQLException {
        String query = "INSERT INTO stok(waktu, total_quantity, user_id) VALUES(now(),?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, model.getTotal_quantity());
        ps.setInt(2, model.getUser_id());
        ps.executeUpdate();
    }
    
    public void queryInsertDStok(Connection con, MStok model) throws SQLException {
        String query = "INSERT INTO detail_stok(produk_id, imei, warna_id, stok_id) VALUES(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, model.getProduk_id());
        ps.setString(2, model.getImei());
        ps.setInt(3, model.getWarna_id());
        ps.setInt(4, model.getStok_id());
        ps.executeUpdate();
    }
    
    public void queryUpdateProduk(Connection con, int produk_id) throws SQLException {
        String query = "UPDATE produk SET quantity = quantity + 1  WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, produk_id);
        ps.executeUpdate();
    }
    
    public int getIdStok(Connection con) throws SQLException {
        String query = "SELECT * FROM stok ORDER BY id DESC LIMIT 1";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public List<MStok> getAllData() {
        return data;
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
    
}
