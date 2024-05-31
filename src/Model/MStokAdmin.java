/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author MSI-PC
 */
public class MStokAdmin {
    private int id_stok;
    private String tgl, waktu;
    private int total_quantity, user_id;
    private String nama_user;

    public MStokAdmin(int id_stok, String tgl, String waktu, int total_quantity, String nama_user) {
        this.id_stok = id_stok;
        this.tgl = tgl;
        this.waktu = waktu;
        this.total_quantity = total_quantity;
        this.nama_user = nama_user;
    }

    public int getId_stok() {
        return id_stok;
    }

    public void setId_stok(int id_stok) {
        this.id_stok = id_stok;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }
    
}
