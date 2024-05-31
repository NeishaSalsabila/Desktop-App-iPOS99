/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author MSI-PC
 */
public class MDetailStok {
    private int id, produk_id;
    private String nama_produk, imei;
    private int warna_id;
    private String warna;
    private int stok_id, status;

    // Constructor View
    public MDetailStok(int id, String nama_produk, String imei, String warna, int status) {
        this.id = id;
        this.nama_produk = nama_produk;
        this.imei = imei;
        this.warna = warna;
        this.status = status;
    }

    // Constructor Update
    public MDetailStok(int id, int produk_id, String imei, int warna_id, int stok_id, int status) {
        this.id = id;
        this.produk_id = produk_id;
        this.imei = imei;
        this.warna_id = warna_id;
        this.stok_id = stok_id;
        this.status = status;
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

    public int getStok_id() {
        return stok_id;
    }

    public void setStok_id(int stok_id) {
        this.stok_id = stok_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
