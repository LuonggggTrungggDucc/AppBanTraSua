package com.example.project_tra_sua.models;

public class taikhoan {
    int idtk;
    String email;
    String hoten;
    String mk;
    String sdt;
    String diachi;

    public taikhoan(int idtk, String email, String hoten, String mk, String sdt, String diachi) {
        this.idtk = idtk;
        this.email = email;
        this.hoten = hoten;
        this.mk = mk;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public int getIdtk() {
        return idtk;
    }

    public String getHoten() {
        return hoten;
    }
    public String getEmail() {
        return email;
    }

    public String getMk() {
        return mk;
    }
}
