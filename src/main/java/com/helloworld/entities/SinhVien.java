package com.helloworld.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.helloworld.entities.properties.DiaChi;
import com.helloworld.entities.properties.HoTen;

public class SinhVien {
    private Integer maso;

    private HoTen hoten;

    private Boolean gioi_tinh;
    // 0 = gái

    private LocalDate ngay_sinh;

    private DiaChi dia_chi_hien_tai;

    private DiaChi que_quan;

    private String email;

    private String so_dien_thoai;

    public SinhVien() {
    }

    public SinhVien(HoTen hoten, LocalDate ngay_sinh, Boolean gioi_tinh, DiaChi dia_chi_hien_tai, DiaChi que_quan, String so_dien_thoai) {
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

    public HoTen getHoten() {
        return this.hoten;
    }

    public String getHoTen() {
        return this.hoten.toString();
    }

    public void setHoten(HoTen hoten) {
        this.hoten = hoten;
    }

    public LocalDate getNgaySinh() {
        return this.ngay_sinh;
    }

    public void setNgaySinh(LocalDate ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
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
        return this.getHoten().getTen().toLowerCase() + String.format("%05d", this.getMaso()) + "66" + "@huce.edu.vn";
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SinhVien)) {
            return false;
        }
        SinhVien SinhVien = (SinhVien) o;
        return Objects.equals(maso, SinhVien.maso)
                && Objects.equals(so_dien_thoai, SinhVien.so_dien_thoai)
                && Objects.equals(email, SinhVien.email);
    }

    @Override
    public String toString() {
        return "{" +
                " maso='" + getMaso() + "'" +
                ", hoten='" + getHoten() + "'" +
                ", ngay_sinh='" + getNgaySinh() + "'" +
                ", dia_chi_hien_tai='" + getDiaChiHienTai() + "'" +
                ", que_quan='" + getQueQuan() + "'" +
                ", email='" + getEmail() + "'" +
                ", so_dien_thoai='" + getSoDienThoai() + "'" +
                "}";
    }
}
