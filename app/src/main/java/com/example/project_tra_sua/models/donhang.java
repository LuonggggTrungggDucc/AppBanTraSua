package com.example.project_tra_sua.models;

public class donhang {
    int iddh,idgh,idtk;
    String loinhan,ngaygio,hanhchinh,ghichu;

    public donhang(int iddh, int idgh, int idtk, String loinhan, String ngaygio, String hanhchinh, String ghichu) {
        this.iddh = iddh;
        this.idgh = idgh;
        this.idtk = idtk;
        this.loinhan = loinhan;
        this.ngaygio = ngaygio;
        this.hanhchinh = hanhchinh;
        this.ghichu = ghichu;
    }

    public int getIdtk() {
        return idtk;
    }

    public int getIddh() {
        return iddh;
    }

    public int getIdgh() {
        return idgh;
    }

    public String getLoinhan() {
        return loinhan;
    }

    public String getNgaygio() {
        return ngaygio;
    }

    public String getHangchinh() {
        return hanhchinh;
    }

    public String getGhichu() {
        return ghichu;
    }
}
