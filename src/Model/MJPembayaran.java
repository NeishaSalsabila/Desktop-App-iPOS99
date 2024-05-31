/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author MSI-PC
 */
public class MJPembayaran {
    private int id;
    private String nama, kategori;
    
    // Constructor View dan Update
    public MJPembayaran(int id, String nama, String kategori) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
    }
    
    // Constructor Insert
    public MJPembayaran(String nama, String kategori) {
        this.nama = nama;
        this.kategori = kategori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
}
