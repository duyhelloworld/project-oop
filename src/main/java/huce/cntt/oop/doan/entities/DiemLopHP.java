package huce.cntt.oop.doan.entities;

import java.util.Objects;

public class DiemLopHP {
    private Integer MSSV;
    private String hoTen;
    private String lopQL;
    private Float diemChuyenCan;
    private Float diemGiuaKi;
    private Float diemCuoiKi;
    private String tenLopMonHoc;
    private Integer maLopHP;
    private Integer hocKy;

    public DiemLopHP() {
    }

    public Integer getMaLopHP() {
        return this.maLopHP;
    }

    public void setMaLopHP(Integer maLopHP) {
        this.maLopHP = maLopHP;
    }

    public String getTenLopMonHoc() {
        return this.tenLopMonHoc;
    }

    public void setTenLopMonHoc(String tenLopMonHoc) {
        this.tenLopMonHoc = tenLopMonHoc;
    }

    public Integer getMSSV() {
        return this.MSSV;
    }

    public void setMSSV(Integer MSSV) {
        this.MSSV = MSSV;
    }

    public String getHoTen() {
        return this.hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Integer getHocKy() {
        return this.hocKy;
    }

    public void setHocKy(Integer hocKy) {
        this.hocKy = hocKy;
    }

    public Float getDiemChuyenCan() {
        return this.diemChuyenCan;
    }

    public void setDiemChuyenCan(Float diemChuyenCan) {
        this.diemChuyenCan = diemChuyenCan;
    }

    public Float getDiemGiuaKi() {
        return this.diemGiuaKi;
    }

    public void setDiemGiuaKi(Float diemGiuaKi) {
        this.diemGiuaKi = diemGiuaKi;
    }

    public Float getDiemQuaTrinh() {
        float diemChuyenCan = getDiemChuyenCan();
        float diemGiuaKi = getDiemGiuaKi();
        Float diemQuaTrinh = Math.round((diemChuyenCan * 0.5f + diemGiuaKi * 0.5f) * 10.0f) / 10.0f;
        return diemQuaTrinh;
    }

    public Float getDiemCuoiKi() {
        return this.diemCuoiKi;
    }

    public void setDiemCuoiKi(Float diemCuoiKi) {
        this.diemCuoiKi = diemCuoiKi;
    }

    public Float getDiemTongKet() {
        float diemQuaTrinh = getDiemQuaTrinh();
        float diemCuoiKi = getDiemCuoiKi();
        float diemTongKet = Math.round((diemQuaTrinh * 0.3f + diemCuoiKi * 0.7f) * 10.0f) / 10.0f;
        return diemTongKet;
    }

    public Float getDiemHe4() {
        float diemtk = getDiemTongKet();
        if (diemtk >= 8.5f)
            return 4f;
        else if (diemtk >= 8.0f)
            return 3.5f;
        else if (diemtk >= 7.0f)
            return 3.0f;
        else if (diemtk >= 6.5f)
            return 2.5f;
        else if (diemtk >= 5.5f)
            return 2.0f;
        else if (diemtk >= 5.0f)
            return 1.5f;
        else if (diemtk >= 4.0f)
            return 1.0f;
        else
            return 0f;
    }

    public String getDiemChu() {
        float diemtk = getDiemTongKet();
        return DiemChu.tuDiemSo(diemtk).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DiemLopHP)) {
            return false;
        }
        DiemLopHP diemLopHP = (DiemLopHP) o;
        return Objects.equals(MSSV, diemLopHP.MSSV) && Objects.equals(hoTen, diemLopHP.hoTen)
                && Objects.equals(lopQL, diemLopHP.lopQL) && Objects.equals(diemChuyenCan, diemLopHP.diemChuyenCan)
                && Objects.equals(diemGiuaKi, diemLopHP.diemGiuaKi) && Objects.equals(diemCuoiKi, diemLopHP.diemCuoiKi);
    }

    @Override
    public String toString() {
        return "{" +
                " MSSV = '" + getMSSV() + "'" +
                " Họ Tên = '" + getHoTen() + "'" +
                ", lopMH='" + getTenLopMonHoc() + "'" +
                ", diemChuyenCan='" + getDiemChuyenCan() + "'" +
                ", diemGiuaKi='" + getDiemGiuaKi() + "'" +
                ", diemQuaTrinh='" + getDiemQuaTrinh() + "'" +
                ", diemCuoiKi='" + getDiemCuoiKi() + "'" +
                ", diemTongKet='" + getDiemTongKet() + "'" +
                ", diemTongKetHe4='" + getDiemHe4() + "'" +
                ", diemChu='" + getDiemChu() + "'" +
                "}";
    }

}
