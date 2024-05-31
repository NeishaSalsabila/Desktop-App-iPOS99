/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author MSI-PC
 */
public class MKasir {

    // Struktur tabel transaksi
    private int id_transaksi;
    private String tgl, waktu;
    private int customer_id, jpembayaran_id, total_harga, total_quantity, user_id;

    // Struktur tabel detail_transaksi
    private int id_dtransaksi, produk_id;
    private String nama_produk, imei, warna;
    private int warna_id;
    private int transaksi_id;
    
    // Struktur tabel customer
    private int id_customer;
    private String nama_customer, nik, no_hp;
    
    // Constructor Insert Database tabel customers
    public MKasir(String nama_customer, String nik, String no_hp) {
        this.nama_customer = nama_customer;
        this.nik = nik;
        this.no_hp = no_hp;
    }

    // Constructor Insert Database tabel transaksi
    public MKasir(int customer_id, int jpembayaran_id, int total_harga, int total_quantity, int user_id) {
        this.customer_id = customer_id;
        this.jpembayaran_id = jpembayaran_id;
        this.total_harga = total_harga;
        this.total_quantity = total_quantity;
        this.user_id = user_id;
    }

    // Constructor Insert Memori tabel detail_transaksi
    public MKasir(int produk_id, String nama_produk, String imei, String warna, int warna_id) {
        this.produk_id = produk_id;
        this.nama_produk = nama_produk;
        this.imei = imei;
        this.warna = warna;
        this.warna_id = warna_id;
    }

    // Constructor Insert Database tabel detail_transaksi
    public MKasir(int produk_id, String imei, int warna_id, int transaksi_id) {
        this.produk_id = produk_id;
        this.imei = imei;
        this.warna_id = warna_id;
        this.transaksi_id = transaksi_id;
    }

    // Method Accessor dan Mutator tabel transaksi//////////////////////////////
    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getJpembayaran_id() {
        return jpembayaran_id;
    }

    public void setJpembayaran_id(int jpembayaran_id) {
        this.jpembayaran_id = jpembayaran_id;
    }

    public int getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getId_dtransaksi() {
        return id_dtransaksi;
    }

    public void setId_dtransaksi(int id_dtransaksi) {
        this.id_dtransaksi = id_dtransaksi;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    ///////////////////////////////////////////////////////////////////////////

    // Method Accessor dan Mutator tabel detail_transaksi///////////////////////
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

    public int getWarna_id() {
        return warna_id;
    }

    public void setWarna_id(int warna_id) {
        this.warna_id = warna_id;
    }

    public int getTransaksi_id() {
        return transaksi_id;
    }

    public void setTransaksi_id(int transaksi_id) {
        this.transaksi_id = transaksi_id;
    }
    ///////////////////////////////////////////////////////////////////////////

    // Method Accessor dan Mutator tabel customers/////////////////////////////

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }
    ///////////////////////////////////////////////////////////////////////////
    
}
