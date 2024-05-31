/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author MSI-PC
 */
public class MProduk {
    private int id;
    private String nama;
    private int harga, subsidi, modal, quantity;

    // Constructor View
    public MProduk(int id, String nama, int harga, int subsidi, int modal, int quantity) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.subsidi = subsidi;
        this.modal = modal;
        this.quantity = quantity;
    }

    // Constructor Update
    public MProduk(int id, String nama_produk, int harga_produk, int subsidi, int modal) {
        this.id = id;
        this.nama = nama_produk;
        this.harga = harga_produk;
        this.subsidi = subsidi;
        this.modal = modal;
    }
    
    // Constructor Insert
    public MProduk(String nama_produk, int harga_produk, int subsidi, int modal) {
        this.nama = nama_produk;
        this.harga = harga_produk;
        this.subsidi = subsidi;
        this.modal = modal;
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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getSubsidi() {
        return subsidi;
    }

    public void setSubsidi(int subsidi) {
        this.subsidi = subsidi;
    }

    public int getModal() {
        return modal;
    }

    public void setModal(int modal) {
        this.modal = modal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
