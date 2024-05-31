/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.MUser;
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
public class DUser {
    public void queryInsert(Connection con, MUser model) throws SQLException {
        String query = "INSERT INTO users(username, password, nama, level_akses) VALUES(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, model.getUsername());
        ps.setString(2, model.getPassword());
        ps.setString(3, model.getNama());
        ps.setString(4, model.getLevel_akses());
        ps.executeUpdate();
    }
    
    public void queryUpdate(Connection con, MUser model) throws SQLException {
        String query = "UPDATE users SET username = ?, password = ?, nama = ?, level_akses = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, model.getUsername());
        ps.setString(2, model.getPassword());
        ps.setString(3, model.getNama());
        ps.setString(4, model.getLevel_akses());
        ps.setInt(5, model.getId());
        ps.executeUpdate();
    }
    
    public void queryDelete(Connection con, int id) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
    
    public List<MUser> querySelect(Connection con) throws SQLException {
        String query = "SELECT * FROM users";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<MUser> list = new ArrayList<>();
        while (rs.next()) {
            MUser model = new MUser(rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("nama"),
                    rs.getString("level_akses"));
            list.add(model);
        }
        return list;
    }
}
