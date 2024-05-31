/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.MLogin;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author MSI-PC
 */
public class DLogin {
    public List<MLogin> login(Connection con, MLogin model) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, model.getUsername());
        ps.setString(2, model.getPassword());
        List<MLogin> list = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            MLogin data = new MLogin(rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("nama"),
                    rs.getString("level_akses"));
            list.add(data);
        }
        return list;
    }
}
