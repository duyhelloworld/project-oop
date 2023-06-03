package huce.cntt.oop.doan.entities;

import java.util.Objects;

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

    public void setDiemChuyenCan(Float diemChuyenCan) {
        if (diemChuyenCan < 0 || diemChuyenCan > 10) {
            throw new IllegalArgumentException("Giá trị 'điểm chuyên cần' không hợp lệ!");
        }
        this.diemChuyenCan = diemChuyenCan;
    }

    public Float getDiemGiuaKi() {
        return this.diemGiuaKi;
    }

    public void setDiemGiuaKi(Float diemGiuaKi) {
        if (diemGiuaKi < 0 || diemGiuaKi > 10) {
            throw new IllegalArgumentException("Giá trị 'điểm giữa kì' không hợp lệ!");
        }
        this.diemGiuaKi = diemGiuaKi;
    }

    public Float getDiemCuoiKi() {
        return this.diemCuoiKi;
    }

    public void setDiemCuoiKi(Float diemCuoiKi) {
        if (diemCuoiKi < 0 || diemCuoiKi > 10) {
            throw new IllegalArgumentException("Giá trị 'điểm cuối kì' không hợp lệ!");
        }
        this.diemCuoiKi = diemCuoiKi;
    }

    public Float getDiemQuaTrinh() {
        diemQuaTrinh = diemChuyenCan / 10f + diemGiuaKi * 0.7f;
        return Float.parseFloat(String.format("%.1f", diemQuaTrinh));
    }

    public Float getDiemTongKet() {
        diemTongKet = getDiemQuaTrinh() * 0.3f + getDiemCuoiKi() * 0.7f;
        return Float.parseFloat(String.format("%.1f", diemTongKet));
    }

    public Float getDiemTongKetHe4() {
        return Float.parseFloat(String.format("%.1f", getDiemTongKet() * 0.2f));
    }

    public DiemChu getDiemChu(){
        return DiemChu.tuDiemSo(getDiemTongKet());
    }

    public String getDiemChuString() {
        return getDiemChu().toString();
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
                && Objects.equals(diemCuoiKi, diem.diemCuoiKi);
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
            "\tĐiểm Tổng Kết = " + getDiemTongKet() + "\n" +
            "\tĐiểm Chữ = " + getDiemChu() + "\n";
    }
}
