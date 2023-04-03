package com.helloworld.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.helloworld.entities.properties.DiaChi;
import com.helloworld.entities.properties.HoTen;
import com.helloworld.entities.properties.WrongFormatException;

public abstract class ConNguoi {
    private Long maso;

    private HoTen hoten;
    
    private LocalDate ngay_sinh;

    private DiaChi dia_chi_hien_tai;

    private DiaChi que_quan;

    private String email;

    private String so_dien_thoai;

    public ConNguoi() {
    }

    public ConNguoi(HoTen hoten, LocalDate ngay_sinh, DiaChi dia_chi_hien_tai, DiaChi que_quan, String so_dien_thoai) {
        this.hoten = hoten;
        this.ngay_sinh = ngay_sinh;
        this.dia_chi_hien_tai = dia_chi_hien_tai;
        this.que_quan = que_quan;
        this.so_dien_thoai = so_dien_thoai;
    }

    public Long getMaso() {
        return this.maso;
    }

    public void setMaso(Long maso) {
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

    public LocalDate getNgay_sinh() {
        return this.ngay_sinh;
    }

    public void setNgay_sinh(LocalDate ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public DiaChi getDia_chi_hien_tai() {
        return this.dia_chi_hien_tai;
    }

    public void setDia_chi_hien_tai(DiaChi dia_chi_hien_tai) {
        this.dia_chi_hien_tai = dia_chi_hien_tai;
    }

    public DiaChi getQue_quan() {
        return this.que_quan;
    }

    public void setQue_quan(DiaChi que_quan) {
        this.que_quan = que_quan;
    }

    // Not used, just to override
    public String getEmail() {
        return "unknown@huce.edu.vn";
    }

    public String getSo_dien_thoai() {
        return this.so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        try {
            String regex = "^0\\d{9}$";
            if (!so_dien_thoai.matches(regex)) {
                throw new WrongFormatException("Invalid phone number format");
            }
            this.so_dien_thoai = so_dien_thoai;
        } catch (WrongFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ConNguoi)) {
            return false;
        }
        ConNguoi conNguoi = (ConNguoi) o;
        return Objects.equals(maso, conNguoi.maso)
        && Objects.equals(so_dien_thoai, conNguoi.so_dien_thoai)
        && Objects.equals(email, conNguoi.email);
    }

    @Override
    public String toString() {
        return "{" +
            " maso='" + getMaso() + "'" +
            ", hoten='" + getHoten() + "'" +
            ", ngay_sinh='" + getNgay_sinh() + "'" +
            ", dia_chi_hien_tai='" + getDia_chi_hien_tai() + "'" +
            ", que_quan='" + getQue_quan() + "'" +
            ", email='" + getEmail() + "'" +
            ", so_dien_thoai='" + getSo_dien_thoai() + "'" +
            "}";
    }
}
