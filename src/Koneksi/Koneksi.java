/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class Koneksi {
    public Connection con;
    public Statement stm;
    
    private String url = "jdbc:mysql://localhost/ipos_99";
    private String username = "root";
    private String password = "";
    
    public static void main(String[] args) {
        // TODO code application logic here
        Koneksi k = new Koneksi();
        try {
            k.getKoneksi();
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal");
        }
    }
    
    public Connection getKoneksi() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }
    
    public void config(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=getKoneksi();
            stm = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "koneksi gagal "+e.getMessage());
        }
    }
}
