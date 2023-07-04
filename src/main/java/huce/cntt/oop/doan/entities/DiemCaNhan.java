package huce.cntt.oop.doan.entities;

import java.time.LocalDate;
import java.util.Objects;

import huce.cntt.oop.doan.entities.exception.DatabaseException;
import huce.cntt.oop.doan.entities.exception.DiemException;

public class DiemCaNhan {
    private Integer maMon;
    private String tenMon;
    private String tenLopMon;
    private Integer soTinChi;
    private Float diemChuyenCan;
    private Float diemGiuaKi;
    private Float diemCuoiKi;
    
    private Float diemQuaTrinh;
    private Float diemTongKet;
    private Float diemTongKetHe4;
    private DiemChu diemChu;
    private Integer hocKi;
    private LocalDate ngayVaoTruong;

    public DiemCaNhan() {
    }

    public Integer getMaMon() {
        return this.maMon;
    }

    public void setMaMon(Integer maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return this.tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getTenLopMon() {
        return this.tenLopMon;
    }

    public void setTenLopMon(String tenLopMon) {
        this.tenLopMon = tenLopMon;
    }

    public Integer getSoTinChi() {
        return this.soTinChi;
    }

    public void setSoTinChi(Integer soTinChi) {
        this.soTinChi = soTinChi;
    }

    public Float getDiemChuyenCan() {
        return this.diemChuyenCan;
    }

    public void setDiemChuyenCan(Float diemChuyenCan) throws DiemException {
        if (diemChuyenCan < 0 || diemChuyenCan > 10) {
            throw new DiemException("Giá trị ở điểm chuyên cần không hợp lệ!");
        }
        this.diemChuyenCan = diemChuyenCan;
    }

    public Float getDiemGiuaKi() {
        return this.diemGiuaKi;
    }

    public void setDiemGiuaKi(Float diemGiuaKi) throws DiemException {
        if (diemGiuaKi < 0 || diemGiuaKi > 10) {
            throw new DiemException("Giá trị ở điểm giữa kì không hợp lệ!");
        }
        this.diemGiuaKi = diemGiuaKi;
    }

    public Float getDiemCuoiKi() {
        return this.diemCuoiKi;
    }

    public void setDiemCuoiKi(Float diemCuoiKi) throws DiemException {
        if (diemCuoiKi < 0 || diemCuoiKi > 10) {
            throw new DiemException("Giá trị ở điểm cuối kì không hợp lệ!");
        }
        this.diemCuoiKi = diemCuoiKi;
    }

    // public Float getDiemQuaTrinh() {
    //     diemQuaTrinh = (diemChuyenCan + diemGiuaKi) / 2.0f;
    //     return Float.parseFloat(String.format("%.1f", diemQuaTrinh));
    //     // return 0.0f;
    // }

    public Float getDiemQuaTrinh() {
        float diemChuyenCan = getDiemChuyenCan();
        float diemGiuaKi = getDiemGiuaKi();
        Float diemQuaTrinh = Math.round((diemChuyenCan * 0.5f + diemGiuaKi * 0.5f) * 10.0f) / 10.0f;
        return diemQuaTrinh;
    }


    // public Float getDiemTongKet() {
    //     diemTongKet = getDiemQuaTrinh() * 0.3f + getDiemCuoiKi() * 0.7f;
    //     return Float.parseFloat(String.format("%.1f", diemTongKet));
    //     // return 0.0f;
    // }

    public Float getDiemTongKet() {
        float diemQuaTrinh = getDiemQuaTrinh();
        float diemCuoiKi = getDiemCuoiKi();
        float diemTongKet = Math.round((diemQuaTrinh * 0.3f + diemCuoiKi * 0.7f) * 10.0f) / 10.0f;
        return diemTongKet;
    }

    public Float getDiemTongKetHe4() {
        return Float.parseFloat(String.format("%.1f", getDiemTongKet() * 0.2f));
        // return 0.0f;
    }

    public DiemChu getDiemChu(){
        return DiemChu.tuDiemSo(getDiemTongKet());
    }

    public String getDiemChuString() {
        return getDiemChu().toString();
    }

    public Integer getHocKi() {
        return hocKi;
    }

    public void setHocKi(Integer hocKi) throws DatabaseException {
        if (hocKi >= 10 || hocKi <= 0) {
            throw new DatabaseException("Giá trị của học kì ngoài khoảng quy định!");
        }
        this.hocKi = hocKi;
    }

    public void setNgayVaoTruong(LocalDate ngayVaoTruong) {
        this.ngayVaoTruong = ngayVaoTruong;
    }    

    public LocalDate getNgayVaoTruong() {
        return ngayVaoTruong;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DiemCaNhan)) {
            return false;
        }
        DiemCaNhan diem = (DiemCaNhan) o;
        return Objects.equals(diemGiuaKi, diem.diemGiuaKi)
                && Objects.equals(diemChuyenCan, diem.diemChuyenCan)
                && Objects.equals(diemCuoiKi, diem.diemCuoiKi)
                && Objects.equals(diemTongKetHe4, diem.diemTongKetHe4)
                && Objects.equals(diemChu, diem.diemChu)
                && Objects.equals(hocKi, diem.hocKi);
    }

    @Override
    public String toString() {
        return "ĐIỂM CÁ NHÂN\t:\n" +
            "\tMã Môn = " + getMaMon() +  "\n" +
            "\tTên Môn = '" + getTenMon() + "'" + "\n" +
            "\tLớp Môn Học = '" + getTenLopMon() + "'" + "\n" +
            "\tĐiểm Chuyên Cần = " + getDiemChuyenCan() + "\n" +
            "\tĐiểm Giữa Kì = " + getDiemGiuaKi() + "\n" +
            "\tĐiểm Cuối Kì = " + getDiemCuoiKi() + "\n" +
            "\tĐiểm Quá Trình = " + getDiemQuaTrinh() + "\n" +
            "\tHọc Kì = '" + getHocKi() + "'" + "\n" +
            "\tĐiểm Tổng Kết = " + getDiemTongKet() + "\n" +
            "\tĐiểm Chữ = " + getDiemChu() + "\n";
    }
}
