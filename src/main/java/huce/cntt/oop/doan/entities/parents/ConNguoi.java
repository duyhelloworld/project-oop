package huce.cntt.oop.doan.entities.parents;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import huce.cntt.oop.doan.entities.properties.DiaChi;
import huce.cntt.oop.doan.entities.properties.HoTen;

public abstract class ConNguoi {

    private Integer maso;

    private HoTen hoTen;

    private Boolean gioiTinh;

    private LocalDate ngaySinh;

    private DiaChi diaChiThuongTru;

    private DiaChi queQuan;

    private String email;

    private String soDienThoai;

    public ConNguoi() {
    }

    public Integer getMaSo() {
        return this.maso;
    }

    public void setMaSo(Integer maso) {
        this.maso = maso;
    }

    public HoTen getHoTen() {
        return this.hoTen;
    }

    public void setHoTen(HoTen hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return this.ngaySinh;
    }

    public Date getNgaySinhVoiKieuSQL() {
        return Date.valueOf(this.ngaySinh);
    }

    public void setNgaySinh(LocalDate ngay_sinh) {
        this.ngaySinh = ngay_sinh;
    }

    public void setNgaySinh(Date ngay_sinh) {
        this.ngaySinh = ngay_sinh.toLocalDate();
    }

    public Boolean getGioiTinh() {
        return this.gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public DiaChi getDiaChiThuongTru() {
        return this.diaChiThuongTru;
    }

    public void setDiaChiThuongTru(DiaChi diaChiThuongTru) {
        this.diaChiThuongTru = diaChiThuongTru;
    }

    public DiaChi getQueQuan() {
        return this.queQuan;
    }

    public void setQueQuan(DiaChi que_quan) {
        this.queQuan = que_quan;
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
        return this.soDienThoai;
    }

    public void setSoDienThoai(String so_dien_thoai) {
        String regex = "^0\\d{9}$";
        if (!so_dien_thoai.matches(regex)) {
            throw new NumberFormatException("Định dạng 'số điện thoại' không đúng!");
        }
        this.soDienThoai = so_dien_thoai;
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
                && Objects.equals(soDienThoai, nguoi.soDienThoai)
                && Objects.equals(email, nguoi.email);
    }

    @Override
    public String toString() {
        return "{" +
                " mã số = '" + getMaSo() + "'" +
                ", họ tên = '" + getHoTen() + "'" +
                ", ngày sinh = '" + getNgaySinh() + "'" +
                ", địa chỉ hiện tại = '" + getDiaChiThuongTru() + "'" +
                ", quê quán = '" + getQueQuan() + "'" +
                ", email = '" + getEmail() + "'" +
                ", số điện thoại = '" + getSoDienThoai()
                ;
    }
}
