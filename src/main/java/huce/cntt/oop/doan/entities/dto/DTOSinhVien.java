package huce.cntt.oop.doan.entities.dto;

import java.util.Objects;

import huce.cntt.oop.doan.entities.SinhVien;

public class DTOSinhVien extends SinhVien {
    private Integer maLopQuanLi;

    private String tenLopQuanLi;

    private Integer nienKhoa;

    private Integer maKhoa;

    private String khoa;

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

    public Integer getMaKhoa() {
        return this.maKhoa;
    }

    public void setMaKhoa(Integer maKhoa) {
        this.maKhoa = maKhoa;
    }

    public Integer getNienKhoa() {
        if (super.getNgayVaoTruong() == null) {
            return 0;
        }
        return super.getNgayVaoTruong().getYear() - 1966;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DTOSinhVien)) {
            return false;
        }
        DTOSinhVien dtoSinhVien = (DTOSinhVien) o;
        return super.equals(dtoSinhVien) 
            && Objects.equals(nienKhoa, dtoSinhVien.nienKhoa)
            && Objects.equals(khoa, dtoSinhVien.khoa);
    }
    
    @Override
    public String toString() {
        return super.toString() +
            ", ngày vào trường = '" + getNgayVaoTruong() + "'" +
            ", lớp quản lí = '" + getTenLopQuanLi() + "'" +
            ", lớp quản lí = '" + getTenLopQuanLi() + "'" +
            ", khoa = '" + getKhoa() + "''" +
            ", niên khoá = '" + getNienKhoa() + "'" +
            "}";
        }
}
