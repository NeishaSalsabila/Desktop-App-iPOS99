/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.MProduk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI-PC
 */
public class DProduk {
    public void queryInsert(Connection con, MProduk model) throws SQLException {
        String query = "INSERT INTO produk(nama, harga, subsidi, modal) VALUES(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, model.getNama());
        ps.setInt(2, model.getHarga());
        ps.setInt(3, model.getSubsidi());
        ps.setInt(4, model.getModal());
        ps.executeUpdate();
    }
    
    public void queryUpdate(Connection con, MProduk model) throws SQLException {
        String query = "UPDATE produk SET nama = ?, harga = ?, subsidi = ?, modal = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, model.getNama());
        ps.setInt(2, model.getHarga());
        ps.setInt(3, model.getSubsidi());
        ps.setInt(4, model.getModal());
        ps.setInt(5, model.getId());
        ps.executeUpdate();
    }
    
    public void queryDelete(Connection con, int id) throws SQLException {
        String query = "DELETE FROM produk WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    
    public List<MProduk> querySelect(Connection con) throws SQLException {
        String query = "SELECT * FROM produk";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<MProduk> list = new ArrayList<>();
        while (rs.next()) {
            MProduk model = new MProduk(rs.getInt("id"),
                    rs.getString ("nama"),
                    rs.getInt  ("harga"),
                    rs.getInt ("subsidi"),
                    rs.getInt("modal"),
                    rs.getInt("quantity"));
            list.add(model);
        }
        return list;
    }
}
