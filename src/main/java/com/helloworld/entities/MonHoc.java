package com.helloworld.entities;

import java.util.Objects;

public class MonHoc {
    private static final int MAX_SO_BUOI = 50;
    private static final int MAX_SO_TIN_CHI = 3;

    private Integer ma_mon_hoc;

    private String ten_mon_hoc;

    private Integer so_tin_chi;

    private Boolean la_tien_quyet;

    private Boolean la_bat_buoc;

    private Integer so_buoi;

    public MonHoc() {
    }

    public MonHoc(String ten_mon_hoc, Integer so_tin_chi, Boolean la_tien_quyet, Boolean la_bat_buoc, Integer so_buoi) {
        this.ten_mon_hoc = ten_mon_hoc;
        this.so_tin_chi = so_tin_chi;
        this.la_tien_quyet = la_tien_quyet;
        this.la_bat_buoc = la_bat_buoc;
        this.so_buoi = so_buoi;
    }

    public Integer getMaMonHoc() {
        return this.ma_mon_hoc;
    }

    public void setMaMonHoc(Integer ma_mon_hoc) {
        this.ma_mon_hoc = ma_mon_hoc;
    }

    public String getTenMonHoc() {
        return this.ten_mon_hoc;
    }

    public void setTenMonHoc(String ten_mon_hoc) {
        this.ten_mon_hoc = ten_mon_hoc;
    }

    public Integer getSoTinChi() {
        return this.so_tin_chi;
    }

    public void setSoTinChi(Integer so_tin_chi) {
        if (so_tin_chi < 0 || so_tin_chi > MAX_SO_TIN_CHI) {
            throw new IllegalArgumentException("number 'so_tin_chi' < 0");            
        }
        this.so_tin_chi = so_tin_chi;
    }

    public String laTienQuyet() {
        return this.la_tien_quyet ? "có" : "không";
    }

    public Boolean getLaTienQuyet() {
        return this.la_tien_quyet;
    }

    public void setLaTienQuyet(Boolean la_tien_quyet) {
        this.la_tien_quyet = la_tien_quyet;
    }

    public String laBatBuoc() {
        return this.la_bat_buoc ? "có" : "không" ;
    }

    public Boolean getLaBatBuoc() {
        return this.la_bat_buoc;
    }

    public void setLaBatBuoc(Boolean la_bat_buoc) {
        this.la_bat_buoc = la_bat_buoc;
    }

    public Integer getSoBuoi() {
        return this.so_buoi;
    }

    public void setSoBuoi(Integer so_buoi) {
        if (so_buoi < 0 || so_buoi > MAX_SO_BUOI) {
            throw new IllegalArgumentException("number 'so_buoi' < 0");
        }
        this.so_buoi = so_buoi;
    }

    public MonHoc ma_mon_hoc(Integer ma_mon_hoc) {
        setMaMonHoc(ma_mon_hoc);
        return this;
    }

    public MonHoc ten_mon_hoc(String ten_mon_hoc) {
        setTenMonHoc(ten_mon_hoc);
        return this;
    }

    public MonHoc so_tin_chi(Integer so_tin_chi) {
        setSoTinChi(so_tin_chi);
        return this;
    }

    public MonHoc la_tien_quyet(Boolean la_tien_quyet) {
        setLaTienQuyet(la_tien_quyet);
        return this;
    }

    public MonHoc la_bat_buoc(Boolean la_bat_buoc) {
        setLaBatBuoc(la_bat_buoc);
        return this;
    }

    public MonHoc so_buoi(Integer so_buoi) {
        setSoBuoi(so_buoi);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MonHoc)) {
            return false;
        }
        MonHoc monHoc = (MonHoc) o;
        return Objects.equals(ma_mon_hoc, monHoc.ma_mon_hoc) && Objects.equals(ten_mon_hoc, monHoc.ten_mon_hoc) && Objects.equals(so_tin_chi, monHoc.so_tin_chi) && Objects.equals(la_tien_quyet, monHoc.la_tien_quyet) && Objects.equals(la_bat_buoc, monHoc.la_bat_buoc) && Objects.equals(so_buoi, monHoc.so_buoi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ma_mon_hoc, ten_mon_hoc, so_tin_chi, la_tien_quyet, la_bat_buoc, so_buoi);
    }

    @Override
    public String toString() {
        return "{" +
            " mã môn học = '" + getMaMonHoc() + "'" +
            ", tên môn học = '" + getTenMonHoc() + "'" +
            ", số tín chỉ = '" + getSoTinChi() + "'" +
            ", là tiên quyết = '" + laTienQuyet() + "'" +
            ", môn bắt buộc = '" + laBatBuoc() + "'" +
            ", số buổi = '" + getSoBuoi() + "'" +
            "}";
    }

}