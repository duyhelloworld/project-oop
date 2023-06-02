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
        if (!ngay_sinh.isBefore(LocalDate.now().minusYears(10))
            || !ngay_sinh.isAfter(LocalDate.of(1940, 1, 1))) {
            throw new IllegalArgumentException("Lỗi ngày : khoảng cách năm sinh là từ 1940 tới " + (LocalDate.now().getYear() - 10));
        }
        this.ngaySinh = ngay_sinh;
    }

    public void setNgaySinh(Date ngay_sinh) {
        this.ngaySinh = ngay_sinh.toLocalDate();
    }

    public Boolean getGioiTinh() {
        return this.gioiTinh;
    }

    public String getGioiTinhString() {
        return getGioiTinh() ? "nam" : "nữ"; 
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
        if (email == null || email.isBlank()) {
            return;
        }
        
        if (email.matches(".*@.*")) {
            this.email = email;
            return;
        }
        throw new IllegalArgumentException("Email sai định dạng\nViết lại với kí tự '@' !");
    }

    public String getEmail() {
        return this.email;
    }

    public String getSoDienThoai() {
        return this.soDienThoai;
    }

    public void setSoDienThoai(String so_dien_thoai) {
        String regex = "^0\\d{9}$";
        if (so_dien_thoai.matches(regex)) {    
            this.soDienThoai = so_dien_thoai;
            return;
        }
        throw new NumberFormatException("Định dạng 'số điện thoại' không đúng!");
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
        return
                "\tMã số = " + getMaSo() + "\n" +
                "\tHọ tên = " + getHoTen() + "\n" +
                "\tGiới tính = " + getGioiTinhString() + "\n" +
                "\tNgày sinh = " + getNgaySinh() + "\n" +
                "\tĐịa chỉ hiện tại = " + getDiaChiThuongTru() + "\n" +
                "\tQuê quán = " + getQueQuan() + "\n" +
                "\tEmail = " + getEmail() + "\n" +
                "\tSố điện thoại = " + getSoDienThoai() + "\n";
    }
}
