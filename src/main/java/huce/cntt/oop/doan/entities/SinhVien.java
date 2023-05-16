package huce.cntt.oop.doan.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import huce.cntt.oop.doan.entities.parents.ConNguoi;

public class SinhVien extends ConNguoi {

    private LocalDate ngayVaoTruong;

    private String tenLopQuanLi;

    private Integer nienKhoa;

    private String khoa;

    public SinhVien() {
    }

    public LocalDate getNgayVaoTruong() {
        return this.ngayVaoTruong;
    }

    public Date getNgayVaoTruongVoiKieuSQL(){
        return Date.valueOf(this.ngayVaoTruong);
    }

    public void setNgayVaoTruong(LocalDate ngayVaoTruong) {
        this.ngayVaoTruong = ngayVaoTruong;
    }

    public String getTenLopQuanLi() {
        return this.tenLopQuanLi;
    }

    public void setTenLopQuanLi(String tenLopQuanLi) {
        this.tenLopQuanLi = tenLopQuanLi;
    }

    public Integer getNienKhoa() {
        if (ngayVaoTruong == null) {
            return 0;
        }
        return this.ngayVaoTruong.getYear() - 1966;
    }

    public String getKhoa() {
        return this.khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
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
            && Objects.equals(nienKhoa, sinhVien.nienKhoa)
            && Objects.equals(khoa, sinhVien.khoa);
    }

    @Override
    public String toString() {
        return super.toString() +
            ", ngày vào trường = '" + getNgayVaoTruong() + "'" +
            ", khoa = '" + getKhoa() + "''" +
            ", niên khoá = '" + getNienKhoa() + "'" +
            "}";
    }
}
