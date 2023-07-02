package huce.cntt.oop.doan.entities.parents;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import huce.cntt.oop.doan.entities.exception.ChuyenDiaChiException;
import huce.cntt.oop.doan.entities.exception.ChuyenHoTenException;
import huce.cntt.oop.doan.entities.exception.ChuyenSoException;
import huce.cntt.oop.doan.entities.exception.EmailException;
import huce.cntt.oop.doan.entities.exception.NgayGioException;
import huce.cntt.oop.doan.entities.properties.DiaChi;
import huce.cntt.oop.doan.entities.properties.HoTen;

public abstract class ConNguoi {
    private Integer maso;

    private HoTen hoTen;

    private Boolean gioiTinh;

    private LocalDate ngaySinh;

    private DiaChi queQuan;

    private DiaChi diaChiThuongTru;

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

    public void setMaSo(String maso) throws ChuyenSoException {
        if (maso.matches("-?\\d+(\\.\\d+)?")) {
            this.maso = Integer.parseInt(maso);
            return;
        }
        throw new ChuyenSoException("Có lỗi trong quá trình nhận dạng số nhập vào\nHãy thử lại");
    }

    public HoTen getHoTen() {
        return this.hoTen;
    }

    public void setHoTen(String hoTen) throws ChuyenHoTenException {
        this.hoTen = new HoTen(hoTen);
    }

    public LocalDate getNgaySinh() {
        return this.ngaySinh;
    }

    public Date getNgaySinhVoiKieuSQL() {
        return Date.valueOf(this.ngaySinh);
    }

    public void setNgaySinh(LocalDate ngay_sinh) throws NgayGioException {
        LocalDate minDate = LocalDate.of(1940, 1, 1);
        LocalDate maxDate = LocalDate.now().minusYears(18);

        if (ngay_sinh.isAfter(minDate) && ngay_sinh.isBefore(maxDate)) {
            this.ngaySinh = ngay_sinh;
            return;
        }
        int minYear = minDate.getYear(), maxYear = maxDate.getYear();
        throw new NgayGioException("Lỗi ngày : khoảng cách năm sinh là từ " + minYear + " tới " + maxYear);
    }

    public void setNgaySinh(Date ngay_sinh) throws NgayGioException {
        LocalDate ngaySinh =  ngay_sinh.toLocalDate();
        setNgaySinh(ngaySinh);
    }

    public Boolean getGioiTinh() {
        return this.gioiTinh;
    }

    public String getGioiTinhString() {
        if (getGioiTinh() == null) {
            return "ẩn";
        }
        return getGioiTinh() ? "nam" : "nữ"; 
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChiThuongTru() {
        return this.diaChiThuongTru.toString();
    }

    public void setDiaChiThuongTru(String diaChiThuongTru) throws ChuyenDiaChiException {
        this.diaChiThuongTru = new DiaChi(diaChiThuongTru);
    }

    public String getQueQuan() {
        return this.queQuan.toString();
    }

    public void setQueQuan(String que_quan) throws ChuyenDiaChiException {
        this.queQuan = new DiaChi(que_quan);
    }

    public void setEmail(String email) throws EmailException {
        if (email == null || email.isBlank()) {
            throw new EmailException("Email trống!!!\nHãy điền 1 email hợp lệ");
        }
        
        if (email.matches(".*@.*")) {
            this.email = email;
            return;
        }
        throw new EmailException("Email sai định dạng\nHãy viết lại với kí tự '@'!");
    }

    public String getEmail() {
        return this.email;
    }

    public String getSoDienThoai() {
        return this.soDienThoai;
    }

    public void setSoDienThoai(String so_dien_thoai) throws ChuyenSoException {
        String regex = "^0\\d{9}$";
        if (so_dien_thoai.matches(regex)) {    
            this.soDienThoai = so_dien_thoai;
            return;
        }
        throw new ChuyenSoException("\"Số điện thoại\" không đúng định dạng!");
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
                "\tĐịa chỉ  = " + getDiaChiThuongTru() + "\n" +
                "\tQuê quán = " + getQueQuan() + "\n" +
                "\tEmail = " + getEmail() + "\n" +
                "\tSố điện thoại = " + getSoDienThoai() + "\n";
    }
}
