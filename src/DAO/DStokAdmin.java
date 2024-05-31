/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.MStokAdmin;
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
public class DStokAdmin {
    public List<MStokAdmin> querySelect(Connection con) throws SQLException {
        String query = "SELECT s.id as id, s.tgl as tgl, s.waktu as waktu, s.total_quantity as total_quantity, u.nama as nama "
                + "FROM stok s JOIN users u ON(s.user_id = u.id) ";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<MStokAdmin> list = new ArrayList<>();
        while (rs.next()) {
            MStokAdmin model = new MStokAdmin(rs.getInt("id"),
                    rs.getString("tgl"),
                    rs.getString("waktu"),
                    rs.getInt("total_quantity"),
                    rs.getString("nama"));
            list.add(model);
        }
        return list;
    }
}
