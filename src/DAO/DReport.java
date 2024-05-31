/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.MReport;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author MSI-PC
 */
public class DReport {

    public List<MReport> queryPengadaanBarangHarian(Connection con, String bulan, String tahun) throws SQLException {
        String query = "SELECT s.tgl as tgl, COUNT(p.id) as total_quantity, CONCAT(\"Rp\", FORMAT(SUM(p.modal), 0, \"id_ID\")) as total "
                + "FROM detail_stok ds JOIN produk p ON(ds.produk_id = p.id) "
                + "JOIN stok s ON(s.id = ds.stok_id) "
                + "WHERE MONTHNAME(s.tgl) = ? AND YEAR(s.tgl) = ? "
                + "GROUP BY s.tgl";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, bulan);
        ps.setString(2, tahun);
        ResultSet rs = ps.executeQuery();
        List<MReport> list = new ArrayList<>();
        int i = 1;
        while (rs.next()) {
            MReport model = new MReport(i++,
                    rs.getString("tgl"),
                    rs.getInt("total_quantity"),
                    rs.getString("total"));
            list.add(model);
        }
        return list;
    }

    public List<MReport> queryTransaksiHarian(Connection con, String bulan, String tahun) throws SQLException {
        String query = "SELECT t.tgl as tgl, COUNT(p.id) as total_quantity, CONCAT(\"Rp\", FORMAT(SUM(p.harga+p.subsidi), 0, \"id_ID\")) as total "
                + "FROM detail_transaksi ts JOIN produk p ON(ts.produk_id = p.id) "
                + "                    JOIN transaksi t ON(t.id = ts.transaksi_id) "
                + "WHERE MONTHNAME(t.tgl) = ? AND YEAR(t.tgl) = ? "
                + "GROUP BY t.tgl";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, bulan);
        ps.setString(2, tahun);
        ResultSet rs = ps.executeQuery();
        List<MReport> list = new ArrayList<>();
        int i = 1;
        while (rs.next()) {
            MReport model = new MReport(i++,
                    rs.getString("tgl"),
                    rs.getInt("total_quantity"),
                    rs.getString("total"));
            list.add(model);
        }
        return list;
    }
  
}
