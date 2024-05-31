/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author MSI-PC
 */
public class MDetailTransaksi {
    private int id, produk_id;
    private String nama_produk, imei;
    private int warna_id;
    private String warna;
    private int transaksi_id;

    // Constructor View
    public MDetailTransaksi(int id, String nama_produk, String imei, String warna) {
        this.id = id;
        this.nama_produk = nama_produk;
        this.imei = imei;
        this.warna = warna;
    }

    // Constructor Update
    public MDetailTransaksi(int id, int produk_id, String imei, int warna_id, int transaksi_id) {
        this.id = id;
        this.produk_id = produk_id;
        this.imei = imei;
        this.warna_id = warna_id;
        this.transaksi_id = transaksi_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getWarna_id() {
        return warna_id;
    }

    public void setWarna_id(int warna_id) {
        this.warna_id = warna_id;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public int getTransaksi_id() {
        return transaksi_id;
    }

    public void setTransaksi_id(int transaksi_id) {
        this.transaksi_id = transaksi_id;
    }
    
}
