package com.helloworld.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import com.helloworld.entities.properties.DiaChi;
import com.helloworld.entities.properties.HoTen;

public abstract class ConNguoi {
    private Integer maso;

    private HoTen hoten;

    private Boolean gioi_tinh;

    private LocalDate ngay_sinh;

    private DiaChi dia_chi_hien_tai;

    private DiaChi que_quan;

    private String email;

    private String so_dien_thoai;

    public ConNguoi() {
    }

    public ConNguoi(HoTen hoten, LocalDate ngay_sinh, Boolean gioi_tinh, DiaChi dia_chi_hien_tai, DiaChi que_quan, String so_dien_thoai) {
        this.hoten = hoten;
        this.ngay_sinh = ngay_sinh;
        this.gioi_tinh = gioi_tinh;
        this.dia_chi_hien_tai = dia_chi_hien_tai;
        this.que_quan = que_quan;
        this.so_dien_thoai = so_dien_thoai;
    }

    public Integer getMaso() {
        return this.maso;
    }

    public void setMaso(Integer maso) {
        this.maso = maso;
    }

    public HoTen getHoTen() {
        return this.hoten;
    }

    public void setHoTen(HoTen hoten) {
        this.hoten = hoten;
    }

    public LocalDate getNgaySinh() {
        return this.ngay_sinh;
    }

    public Date getNgaySinhVoiKieuSQL() {
        return Date.valueOf(this.ngay_sinh);
    }

    public void setNgaySinh(LocalDate ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public void setNgaySinh(Date ngay_sinh) {
        this.ngay_sinh = ngay_sinh.toLocalDate();
    }

    public Boolean getGioiTinh() {
        return this.gioi_tinh;
    }

    public void setGioiTinh(Boolean gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public DiaChi getDiaChiHienTai() {
        return this.dia_chi_hien_tai;
    }

    public void setDiaChiHienTai(DiaChi dia_chi_hien_tai) {
        this.dia_chi_hien_tai = dia_chi_hien_tai;
    }

    public DiaChi getQueQuan() {
        return this.que_quan;
    }

    public void setQueQuan(DiaChi que_quan) {
        this.que_quan = que_quan;
    }

    public String getEmail() {
        return this.getHoTen().getTen().toLowerCase() + String.format("%05d", this.getMaso()) + "66" + "@huce.edu.vn";
    }

    public String getSoDienThoai() {
        return this.so_dien_thoai;
    }

    public void setSoDienThoai(String so_dien_thoai) {
        try {
            String regex = "^0\\d{9}$";
            if (!so_dien_thoai.matches(regex)) {
                throw new NumberFormatException("Invalid phone number format");
            }
            this.so_dien_thoai = so_dien_thoai;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public ConNguoi maso(Integer maso) {
        setMaso(maso);
        return this;
    }

    public ConNguoi hoten(HoTen hoten) {
        setHoTen(hoten);
        return this;
    }

    public ConNguoi gioi_tinh(Boolean gioi_tinh) {
        setGioiTinh(gioi_tinh);
        return this;
    }

    public ConNguoi ngay_sinh(LocalDate ngay_sinh) {
        setNgaySinh(ngay_sinh);
        return this;
    }

    public ConNguoi dia_chi_hien_tai(DiaChi dia_chi_hien_tai) {
        setDiaChiHienTai(dia_chi_hien_tai);
        return this;
    }

    public ConNguoi que_quan(DiaChi que_quan) {
        setQueQuan(que_quan);
        return this;
    }

    public ConNguoi so_dien_thoai(String so_dien_thoai) {
        setSoDienThoai(so_dien_thoai);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ConNguoi)) {
            return false;
        }
        ConNguoi nguoi = (ConNguoi) o;
        return Objects.equals(maso, nguoi.maso)
                && Objects.equals(so_dien_thoai, nguoi.so_dien_thoai)
                && Objects.equals(email, nguoi.email);
    }

    @Override
    public String toString() {
        return "{" +
                " mã số = '" + getMaso() + "'" +
                ", họ tên = '" + getHoTen() + "'" +
                ", ngày sinh = '" + getNgaySinh() + "'" +
                ", địa chỉ hiện tại = '" + getDiaChiHienTai() + "'" +
                ", quê quán = '" + getQueQuan() + "'" +
                ", email = '" + getEmail() + "'" +
                ", số điện thoại = '" + getSoDienThoai()
                ;
    }
}
