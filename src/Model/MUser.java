/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author MSI-PC
 */
public class MUser {
    private int id;
    private String username, password, nama, level_akses;

    // Constructor View & Update
    public MUser(int id, String username, String password, String nama, String level_akses) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.level_akses = level_akses;
    }

    // Constructor Insert
    public MUser(String username, String password, String nama, String level_akses) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.level_akses = level_akses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLevel_akses() {
        return level_akses;
    }

    public void setLevel_akses(String level_akses) {
        this.level_akses = level_akses;
    }
    
}
