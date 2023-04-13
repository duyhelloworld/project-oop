package com.helloworld.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.helloworld.entities.properties.DiaChi;
import com.helloworld.entities.properties.HoTen;

public class SinhVien extends ConNguoi {
    private LocalDate ngay_vao_truong;

    private Integer ma_lop_quan_li;

    private String khoa;

    public SinhVien() {
    }

    public SinhVien(HoTen hoten, LocalDate ngay_sinh, Boolean gioi_tinh, DiaChi dia_chi_hien_tai, DiaChi que_quan, String so_dien_thoai, LocalDate ngay_vao_truong, Integer ma_lop_quan_li, String khoa) {
        super(hoten, ngay_sinh, gioi_tinh, que_quan, dia_chi_hien_tai, so_dien_thoai);
        this.ngay_vao_truong = ngay_vao_truong;
        this.ma_lop_quan_li = ma_lop_quan_li;
        this.khoa = khoa;
    }

    public SinhVien ngayVaoTruong(LocalDate ngay_vao_truong) {
        setNgayVaoTruong(ngay_vao_truong);
        return this;
    }

    public LocalDate getNgayVaoTruong() {
        return this.ngay_vao_truong;
    }

    public void setNgayVaoTruong(LocalDate ngay_vao_truong) {
        this.ngay_vao_truong = ngay_vao_truong;
    }

    public Integer getMaLopQuanLi() {
        return this.ma_lop_quan_li;
    }

    public void setMaLopQuanLi(Integer ma_lop_quan_li) {
        this.ma_lop_quan_li = ma_lop_quan_li;
    }

    public String getKhoa() {
        return this.khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public SinhVien ngay_vao_truong(LocalDate ngay_vao_truong) {
        setNgayVaoTruong(ngay_vao_truong);
        return this;
    }

    public SinhVien ma_lop_quan_li(Integer ma_lop_quan_li) {
        setMaLopQuanLi(ma_lop_quan_li);
        return this;
    }

    public SinhVien khoa(String khoa) {
        setKhoa(khoa);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SinhVien)) {
            return false;
        }
        SinhVien sinhVien = (SinhVien) o;
        return super.equals(sinhVien) 
            && Objects.equals(ngay_vao_truong, sinhVien.ngay_vao_truong)
            && Objects.equals(ma_lop_quan_li, sinhVien.ma_lop_quan_li)
            && Objects.equals(khoa, sinhVien.khoa);
    }

    @Override
    public String toString() {
        return super.toString() +
            ", ngayVaoTruong='" + getNgayVaoTruong() + "'" +
            ", maLopQuanLi=" + getMaLopQuanLi() + "" +
            ", khoa=" + getKhoa() + "" +
            "}";
    }
}
