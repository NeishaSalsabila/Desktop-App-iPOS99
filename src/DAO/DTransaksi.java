/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.MTransaksi;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MSI-PC
 */
public class DTransaksi {
    public List<MTransaksi> querySelect(Connection con) throws SQLException {
        String query = "SELECT t.id as id, t.tgl as tgl, t.waktu as waktu, t.total_quantity as total_quantity, u.nama as nama "
                + "FROM transaksi t JOIN users u ON(t.user_id = u.id) ";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<MTransaksi> list = new ArrayList<>();
        while (rs.next()) {
            MTransaksi model = new MTransaksi(rs.getInt("id"),
                    rs.getString("tgl"),
                    rs.getString("waktu"),
                    rs.getInt("total_quantity"),
                    rs.getString("nama"));
            list.add(model);
        }
        return list;
    }
}
