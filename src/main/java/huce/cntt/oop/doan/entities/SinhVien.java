package huce.cntt.oop.doan.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import huce.cntt.oop.doan.entities.exception.NgayGioException;
import huce.cntt.oop.doan.entities.parents.ConNguoi;

public class SinhVien extends ConNguoi {

    private LocalDate ngayVaoTruong;

    private String tenLopQuanLi;

    private Integer maLopQuanLi;

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

    public void setNgayVaoTruong(LocalDate ngayVaoTruong) throws NgayGioException {
        LocalDate now = LocalDate.now(), 
        minDate = LocalDate.of(1966, 1, 1);
        if (super.getNgaySinh() == null) {
            throw new NgayGioException("Hãy điền ngày sinh trước!");
        }
        
        if (ngayVaoTruong.isAfter(now)             // ngVaoTrg > now
            || ngayVaoTruong.isBefore(minDate)) {  // ngVaoTrg < minDate 
                throw new NgayGioException("Ngày vào trường trong khoảng " + minDate  + " tới " + now);
        }

        if (!super.getNgaySinh().isBefore(ngayVaoTruong.minusYears(17))) { // ngay sinh >= ngay vao trg
            throw new NgayGioException("Ngày vào trường phải cách ngày sinh ít nhất 18 năm!!!");
        }

        this.ngayVaoTruong = ngayVaoTruong;
    }

    public void setNgayVaoTruong(Date ngayVaoTruong) throws NgayGioException {
        setNgayVaoTruong(ngayVaoTruong.toLocalDate());
    }

    public String getTenLopQuanLi() {
        return this.tenLopQuanLi;
    }

    public void setTenLopQuanLi(String tenLopQuanLi) {
        this.tenLopQuanLi = tenLopQuanLi;
    }

    public Integer getMaLopQuanLi() {
        return this.maLopQuanLi;
    }

    public void setMaLopQuanLi(Integer maLopQuanLi) {
        this.maLopQuanLi = maLopQuanLi;
    }

    public String getKhoa() {
        return this.khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public Integer getNienKhoa() {
        if (getNgayVaoTruong() == null) {
            return 0;
        }
        return getNgayVaoTruong().getYear() - 1966;
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
            && Objects.equals(ngayVaoTruong, sinhVien.ngayVaoTruong)
            && Objects.equals(tenLopQuanLi, sinhVien.tenLopQuanLi)
            && Objects.equals(nienKhoa, sinhVien.nienKhoa)
            && Objects.equals(khoa, sinhVien.khoa);
    }
    
    @Override
    public String toString() {
        return super.toString() +
            "\tNgày vào trường = " + getNgayVaoTruong() + "\n" + 
            "\tLớp quản lí = " + getTenLopQuanLi() + "\n" + 
            "\tKhoa = " + getKhoa() + "\n" + 
            "\tNiên khoá = " + getNienKhoa();
        }
}
