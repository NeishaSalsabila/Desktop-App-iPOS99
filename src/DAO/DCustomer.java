/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.MCustomer;
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
public class DCustomer {
    public List<MCustomer> querySelect(Connection con) throws SQLException {
        String query = "SELECT * FROM customers";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<MCustomer> list = new ArrayList<>();
        while (rs.next()) {
            MCustomer model = new MCustomer(rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("nik"),
                    rs.getString("no_hp"));
            list.add(model);
        }
        return list;
    }
    
    public void queryUpdate(Connection con, MCustomer model) throws SQLException {
        String query = "UPDATE customers SET nama = ?, nik = ?, no_hp = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, model.getNama());
        ps.setString(2, model.getNik());
        ps.setString(3, model.getNo_hp());
        ps.setInt(4, model.getId());
        ps.executeUpdate();
    }
    
    public void queryDelete(Connection con, int id) throws SQLException {
        String query = "DELETE FROM customers WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    
    public int queryTransaksi(Connection con, int id) throws SQLException {
        String query = "SELECT COUNT(*) as count FROM transaksi WHERE customer_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        while (rs.next()) {
            count = rs.getInt("count");
        }
        return count;
    }
}
