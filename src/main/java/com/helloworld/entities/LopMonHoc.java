package com.helloworld.entities;

import java.util.Objects;

public class LopMonHoc {
    private String ten_lop;

    private Integer ma_mon_hoc;

    private Integer ma_giang_vien;


    public LopMonHoc() {
    }

    public LopMonHoc(String ten_lop, Integer ma_mon_hoc, Integer ma_giang_vien) {
        this.ten_lop = ten_lop;
        this.ma_mon_hoc = ma_mon_hoc;
        this.ma_giang_vien = ma_giang_vien;
    }

    public String getTenLop() {
        return this.ten_lop;
    }

    public void setTenLop(String ten_lop) {
        this.ten_lop = ten_lop;
    }

    public Integer getMaMonHoc() {
        return this.ma_mon_hoc;
    }

    public void setMaMonHoc(Integer ma_mon_hoc) {
        this.ma_mon_hoc = ma_mon_hoc;
    }

    public Integer getMaGiangVien() {
        return this.ma_giang_vien;
    }

    public void setMaGiangVien(Integer ma_giang_vien) {
        this.ma_giang_vien = ma_giang_vien;
    }

    public LopMonHoc ten_lop(String ten_lop) {
        setTenLop(ten_lop);
        return this;
    }

    public LopMonHoc ma_mon_hoc(Integer ma_mon_hoc) {
        setMaMonHoc(ma_mon_hoc);
        return this;
    }

    public LopMonHoc maGiangVien(Integer maGiangVien) {
        setMaGiangVien(maGiangVien);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LopMonHoc)) {
            return false;
        }
        LopMonHoc lopMonHoc = (LopMonHoc) o;
        return Objects.equals(ten_lop, lopMonHoc.ten_lop) 
            && Objects.equals(ma_mon_hoc, lopMonHoc.ma_mon_hoc) 
            && Objects.equals(ma_giang_vien, lopMonHoc.ma_giang_vien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ten_lop, ma_mon_hoc, ma_giang_vien);
    }

    @Override
    public String toString() {
        return "tên lớp : " + getTenLop() + "\n"
             + "mã môn : " + getMaMonHoc();
    }
}
