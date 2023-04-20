package com.helloworld.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.helloworld.entities.properties.DiaChi;
import com.helloworld.entities.properties.HoTen;

public class SinhVien extends ConNguoi {
    private LocalDate ngay_vao_truong;

    private String ten_lop_quan_li;

    

    public SinhVien() {
    }

    public SinhVien(HoTen hoten, LocalDate ngay_sinh, Boolean gioi_tinh, DiaChi dia_chi_hien_tai, DiaChi que_quan, String so_dien_thoai, LocalDate ngay_vao_truong, String ten_lop_quan_li) {
        super(hoten, ngay_sinh, gioi_tinh, que_quan, dia_chi_hien_tai, so_dien_thoai);
        this.ngay_vao_truong = ngay_vao_truong;
        this.ten_lop_quan_li = ten_lop_quan_li;
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

    public String getTenLopQuanLi() {
        return this.ten_lop_quan_li;
    }

    public void setTenLopQuanLi(String ten_lop_quan_li) {
        this.ten_lop_quan_li = ten_lop_quan_li;
    }

    public SinhVien ngay_vao_truong(LocalDate ngay_vao_truong) {
        setNgayVaoTruong(ngay_vao_truong);
        return this;
    }

    public SinhVien ten_lop_quan_li(String ten_lop_quan_li) {
        setTenLopQuanLi(ten_lop_quan_li);
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
            && Objects.equals(ten_lop_quan_li, sinhVien.ten_lop_quan_li);
    }

    @Override
    public String toString() {
        return super.toString() +
            ", ngày vào trường = '" + getNgayVaoTruong() + "'" +
            ", lớp quản lí = " + getTenLopQuanLi() + "" +
            "}";
    }
}
