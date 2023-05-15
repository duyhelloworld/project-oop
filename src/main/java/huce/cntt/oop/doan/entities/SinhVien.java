package huce.cntt.oop.doan.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import huce.cntt.oop.doan.entities.parents.ConNguoi;

public class SinhVien extends ConNguoi {

    private LocalDate ngay_vao_truong;

    private String ten_lop_quan_li;

    public SinhVien() {
    }

    public LocalDate getNgayVaoTruong() {
        return this.ngay_vao_truong;
    }

    public Date getNgayVaoTruongVoiKieuSQL(){
        return Date.valueOf(this.ngay_vao_truong);
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


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SinhVien)) {
            return false;
        }
        SinhVien sinhVien = (SinhVien) o;
        return super.equals(sinhVien) 
            && Objects.equals(ngay_vao_truong, sinhVien.ngay_vao_truong);
    }

    @Override
    public String toString() {
        return super.toString() +
            ", ngày vào trường = '" + getNgayVaoTruong() + "'" +
            "}";
    }
}
