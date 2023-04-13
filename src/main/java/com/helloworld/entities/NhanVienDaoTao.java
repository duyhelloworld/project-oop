package com.helloworld.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.helloworld.entities.properties.DiaChi;
import com.helloworld.entities.properties.HoTen;

public class NhanVienDaoTao extends ConNguoi {
    private List<Integer> maSoGiangVien;

    public NhanVienDaoTao() {
    }

    public NhanVienDaoTao(HoTen hoten, LocalDate ngay_sinh, Boolean gioi_tinh, DiaChi dia_chi_hien_tai, DiaChi que_quan, String so_dien_thoai, List<Integer> maSoGiangVien) {
        super(hoten, ngay_sinh, gioi_tinh, dia_chi_hien_tai, que_quan, so_dien_thoai);
        this.maSoGiangVien = maSoGiangVien;
    }

    public List<Integer> getMaSoGiangVien() {
        return this.maSoGiangVien;
    }

    public void setMaSoGiangVien(List<Integer> maSoGiangVien) {
        this.maSoGiangVien = maSoGiangVien;
    }

    public NhanVienDaoTao maSoGiangVien(List<Integer> maSoGiangVien) {
        setMaSoGiangVien(maSoGiangVien);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof NhanVienDaoTao)) {
            return false;
        }
        NhanVienDaoTao nhanVienDaoTao = (NhanVienDaoTao) o;
        return super.equals(nhanVienDaoTao) && Objects.equals(maSoGiangVien, nhanVienDaoTao.maSoGiangVien);
    }

    @Override
    public String toString() {
        return super.toString() +
            " quanLiCacGiangVien='" + getMaSoGiangVien() + "'" +
            "}";
    }

}
