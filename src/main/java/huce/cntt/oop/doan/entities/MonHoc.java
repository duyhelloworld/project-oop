package huce.cntt.oop.doan.entities;

import java.util.Objects;

public class MonHoc {
    // private static final int MAX_SO_TIN_CHI = 3;

    private Integer maMon;

    private String tenMon;

    private Integer soTinChi;
    
    private String monTienQuyet;

    private Boolean batBuoc;

    private String khoa;

    private String moTa;

    public MonHoc() {
    }

    public MonHoc(Integer maMon, String tenMon, Integer soTinChi, String monTienQuyet, Boolean batBuoc, String khoa, String moTa) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.soTinChi = soTinChi;
        this.monTienQuyet = monTienQuyet;
        this.batBuoc = batBuoc;
        this.khoa = khoa;
        this.moTa = moTa;
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

    public Integer getSoTinChi() {
        return this.soTinChi;
    }

    public void setSoTinChi(Integer soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getMonTienQuyet() {
        return this.monTienQuyet;
    }

    public void setMonTienQuyet(String monTienQuyet) {
        this.monTienQuyet = monTienQuyet;
    }

    public Boolean isBatBuoc() {
        return this.batBuoc;
    }

    public Boolean getBatBuoc() {
        return this.batBuoc;
    }

    public void setBatBuoc(Boolean batBuoc) {
        this.batBuoc = batBuoc;
    }

    public String getKhoa() {
        return this.khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getMoTa() {
        return this.moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public MonHoc maMon(Integer maMon) {
        setMaMon(maMon);
        return this;
    }

    public MonHoc tenMon(String tenMon) {
        setTenMon(tenMon);
        return this;
    }

    public MonHoc soTinChi(Integer soTinChi) {
        setSoTinChi(soTinChi);
        return this;
    }

    public MonHoc monTienQuyet(String monTienQuyet) {
        setMonTienQuyet(monTienQuyet);
        return this;
    }

    public MonHoc batBuoc(Boolean batBuoc) {
        setBatBuoc(batBuoc);
        return this;
    }

    public MonHoc khoa(String khoa) {
        setKhoa(khoa);
        return this;
    }

    public MonHoc moTa(String moTa) {
        setMoTa(moTa);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MonHoc)) {
            return false;
        }
        MonHoc monHoc = (MonHoc) o;
        return Objects.equals(maMon, monHoc.maMon) && Objects.equals(tenMon, monHoc.tenMon) && Objects.equals(soTinChi, monHoc.soTinChi) && Objects.equals(monTienQuyet, monHoc.monTienQuyet) && Objects.equals(batBuoc, monHoc.batBuoc) && Objects.equals(khoa, monHoc.khoa) && Objects.equals(moTa, monHoc.moTa);
    }


    

    

    @Override
    public String toString() {
        return "{" +
            " mã môn học = '" + getMaMon() + "'" +
            ", tên môn học = '" + getTenMon() + "'" +
            ", số tín chỉ = '" + getSoTinChi() + "'" +
            ", môn tiên quyết = '" + getMonTienQuyet() + "'" +
            ", bắt buộc = '" + getBatBuoc() + "'" +
            ", khoa = '" + getKhoa() + "'" +
            ", mô tả = '" + getMoTa() + "'" +
            "}";
    }

}