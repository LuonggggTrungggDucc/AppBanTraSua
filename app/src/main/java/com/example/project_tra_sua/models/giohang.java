package com.example.project_tra_sua.models;

public class giohang {
    int idgh;
    int idtk;
    int idsp;
    int sl;
    String chon;
    String giax1;
    int tongtien;
    String ghichu;

    public giohang(int idgh, int idtk, int idsp, int sl, String chon, String giax1, int tongtien, String ghichu) {
        this.idgh = idgh;
        this.idtk = idtk;
        this.idsp = idsp;
        this.sl = sl;
        this.chon = chon;
        this.giax1 = giax1;
        this.tongtien = tongtien;
        this.ghichu = ghichu;
    }

    public int getIdtk() {
        return idtk;
    }

    public String getGiax1() {
        return giax1;
    }

    public int getIdgh() {
        return idgh;
    }

    public void setIdgh(int idgh) {
        this.idgh = idgh;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public String getChon() {
        return chon;
    }

    public void setChon(String chon) {
        this.chon = chon;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
