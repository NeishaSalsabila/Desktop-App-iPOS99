/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.MJPembayaran;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI-PC
 */
public class DJPembayaran {
    public void queryInsert(Connection con, MJPembayaran model) throws SQLException {
        String query = "INSERT INTO jenis_pembayaran(nama, kategori) VALUES(?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, model.getNama());
        ps.setString(2, model.getKategori());
        ps.executeUpdate();
    }
    
    public void queryUpdate(Connection con, MJPembayaran model) throws SQLException {
        String query = "UPDATE jenis_pembayaran SET nama = ?, kategori = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, model.getNama());
        ps.setString(2, model.getKategori());
        ps.setInt(3, model.getId());
        ps.executeUpdate();
    }
    
    public void queryDelete(Connection con, int id) throws SQLException {
        String query = "DELETE FROM jenis_pembayaran WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    
    public List<MJPembayaran> querySelect(Connection con) throws SQLException {
        String query = "SELECT * FROM jenis_pembayaran";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<MJPembayaran> list = new ArrayList<>();
        while (rs.next()) {
            MJPembayaran model = new MJPembayaran(rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("kategori"));
            list.add(model);
        }
        return list;
    }
}
