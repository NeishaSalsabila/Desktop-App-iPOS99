/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author MSI-PC
 */
public class MStok {
    // Struktur tabel stok
    private int id_stok;
    private String tgl_masuk, waktu;
    private int total_quantity, user_id;
    
    // Struktur tabel detail_stok
    private int id_dstok, produk_id;
    private String nama_produk, imei, warna;
    private int warna_id;
    private int stok_id;

    // Constructor Insert Database tabel stok
    public MStok(int total_quantity, int user_id) {
        this.total_quantity = total_quantity;
        this.user_id = user_id;
    }

    // Constructor Insert Memori tabel detail_stok
    public MStok(int produk_id, String nama_produk, String imei, String warna, int warna_id) {
        this.produk_id = produk_id;
        this.nama_produk = nama_produk;
        this.imei = imei;
        this.warna = warna;
        this.warna_id = warna_id;
    }

    // Constructor Insert Database tabel detail_stok
    public MStok(int produk_id, String imei, int warna_id, int stok_id) {
        this.produk_id = produk_id;
        this.imei = imei;
        this.warna_id = warna_id;
        this.stok_id = stok_id;
    }

    // Method Accessor dan Mutator tabel stok//////////////////////////////////
    public int getId_stok() {
        return id_stok;
    }

    public void setId_stok(int id_stok) {
        this.id_stok = id_stok;
    }

    public String getTgl_masuk() {
        return tgl_masuk;
    }

    public void setTgl_masuk(String tgl_masuk) {
        this.tgl_masuk = tgl_masuk;
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
    ///////////////////////////////////////////////////////////////////////////
    
    // Method Accessor dan Mutator tabel detail_stok///////////////////////////
    public int getId_dstok() {
        return id_dstok;
    }

    public void setId_dstok(int id_dstok) {
        this.id_dstok = id_dstok;
    }

    public int getProduk_id() {
        return produk_id;
    }

    public void setProduk_id(int produk_id) {
        this.produk_id = produk_id;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public int getStok_id() {
        return stok_id;
    }

    public void setStok_id(int stok_id) {
        this.stok_id = stok_id;
    }

    public int getWarna_id() {
        return warna_id;
    }

    public void setWarna_id(int warna_id) {
        this.warna_id = warna_id;
    }
    ///////////////////////////////////////////////////////////////////////////
    
}
