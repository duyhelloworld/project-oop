package huce.cntt.oop.doan.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import huce.cntt.oop.doan.entities.parents.ConNguoi;

public class SinhVien extends ConNguoi {

    private LocalDate ngayVaoTruong;

    private Integer maLopQuanLi;

    private String tenLopQuanLi;

    private Integer nienKhoa;

    private Integer maKhoa;

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
        LocalDate now = LocalDate.now();

        if (!ngayVaoTruong.isBefore(now.plusDays(1))
            || !ngayVaoTruong.isAfter(LocalDate.of(1966, 1, 1))) {
            throw new IllegalArgumentException("Ngày vào trường trong khoảng 1966 - " + now.getYear());
        }
        this.ngayVaoTruong = ngayVaoTruong;
    }

    public String getTenLopQuanLi() {
        return this.tenLopQuanLi;
    }

    public void setTenLopQuanLi(String tenLopQuanLi) {
        this.tenLopQuanLi = tenLopQuanLi;
    }

    public String getKhoa() {
        return this.khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public void setMaLopQuanLi(Integer maLop) {
        this.maLopQuanLi = maLop;
    }

    public Integer getMaLopQuanLi() {
        return this.maLopQuanLi;
    }

    public void setMaKhoa(Integer maKhoa) {
        this.maKhoa = maKhoa;
    }

    public Integer getMaKhoa() {
        return this.maKhoa;
    }

    public Integer getNienKhoa() {
        if (getNgayVaoTruong() == null) {
            return 0;
        }
        return getNgayVaoTruong().getYear() - 1966;
    }

    public boolean hasNullElement() {
        return super.getHoTen() == null ||
        super.getGioiTinh() == null ||
        super.getEmail() == null ||
        super.getNgaySinh() == null ||
        super.getQueQuan() == null ||
        super.getDiaChiThuongTru() == null ||
        super.getSoDienThoai() == null ||
        khoa == null || 
        ngayVaoTruong == null ||
        maLopQuanLi == null ||
        tenLopQuanLi == null;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SinhVien)) {
            return false;
        }
        SinhVien SinhVien = (SinhVien) o;
        return super.equals(SinhVien) 
            && Objects.equals(ngayVaoTruong, SinhVien.ngayVaoTruong)
            && Objects.equals(maLopQuanLi, SinhVien.maLopQuanLi)
            && Objects.equals(maKhoa, SinhVien.maKhoa);
    }
    
    @Override
    public String toString() {
        return super.toString() +
            ", ngày vào trường = '" + getNgayVaoTruong() + "'" +
            ", lớp quản lí = '" + getTenLopQuanLi() + "'" +
            ", mã lớp quản lí = '" + getMaLopQuanLi() + "'" +
            ", khoa = '" + getKhoa() + "''" +
            ", niên khoá = '" + getNienKhoa() + "'" +
            "}";
        }
}
