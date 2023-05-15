package huce.cntt.oop.doan.entities.parents;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import huce.cntt.oop.doan.entities.properties.DiaChi;
import huce.cntt.oop.doan.entities.properties.HoTen;

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

    public void setEmail(String email) {
        if (!email.matches("^.*@huce\\.edu\\.vn$")) {
            email += "@huce.edu.vn";
        }
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSoDienThoai() {
        return this.so_dien_thoai;
    }

    public void setSoDienThoai(String so_dien_thoai) {
        try {
            String regex = "^0\\d{9}$";
            if (!so_dien_thoai.matches(regex)) {
                throw new NumberFormatException("Invalid 'phone number' format");
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
