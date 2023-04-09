package com.helloworld.entities;

import java.util.Objects;

public class LopMonHoc {
    private String ten_lop;

    private Integer ma_mon_hoc;

    public LopMonHoc() {
    }

    public LopMonHoc(String ten_lop, Integer ma_mon_hoc) {
        this.ten_lop = ten_lop;
        this.ma_mon_hoc = ma_mon_hoc;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LopMonHoc)) {
            return false;
        }
        LopMonHoc lopMonHoc = (LopMonHoc) o;
        return Objects.equals(ten_lop, lopMonHoc.ten_lop) && Objects.equals(ma_mon_hoc, lopMonHoc.ma_mon_hoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ten_lop, ma_mon_hoc);
    }

    @Override
    public String toString() {
        return "tên lớp : " + getTenLop() + "\n" + "mã môn : " + getMaMonHoc();
    }
}
