package huce.cntt.oop.doan.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import huce.cntt.oop.doan.entities.properties.DiaChi;
import huce.cntt.oop.doan.entities.properties.HoTen;

public class GiangVien extends ConNguoi {
    private List<Integer> maLopMonHoc;

    private Integer maNVDT;

    public GiangVien() {
    }

    public GiangVien(HoTen hoten, LocalDate ngay_sinh, Boolean gioi_tinh, DiaChi dia_chi_hien_tai, DiaChi que_quan, String so_dien_thoai, List<Integer> maLopMonHoc, Integer maNVDT) {
        super(hoten, ngay_sinh, gioi_tinh, dia_chi_hien_tai, que_quan, so_dien_thoai);
        this.maLopMonHoc = maLopMonHoc;
        this.maNVDT = maNVDT;
    }

    public List<Integer> getMaLopMonHoc() {
        return this.maLopMonHoc;
    }

    public void setMaLopMonHoc(List<Integer> maLopMonHoc) {
        this.maLopMonHoc = maLopMonHoc;
    }

    public GiangVien maLopMonHoc(List<Integer> maLopMonHoc) {
        setMaLopMonHoc(maLopMonHoc);
        return this;
    }

    public Integer getMaNVDT() {
        return this.maNVDT;
    }

    public void setMaNVDT(Integer maNVDT) {
        this.maNVDT = maNVDT;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GiangVien)) {
            return false;
        }
        GiangVien giangVien = (GiangVien) o;
        return super.equals(giangVien) 
            && Objects.equals(maLopMonHoc, giangVien.maLopMonHoc)
            && Objects.equals(maNVDT, giangVien.maNVDT);
    }

    @Override
    public String toString() {
        return super.toString() +
            ", maLopMonHoc='" + getMaLopMonHoc() + "'" +
            ", maNVDT='" + getMaNVDT() + "'" +
            "}";
    }



}
