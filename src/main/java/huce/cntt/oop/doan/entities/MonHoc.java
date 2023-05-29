package huce.cntt.oop.doan.entities;

import java.util.Objects;

public class MonHoc {
    private static final int MAX_SO_TIN_CHI = 3;

    private Integer ma_mon_hoc;

    private String ten_mon_hoc;

    private Integer so_tin_chi;
    
    private Integer ma_mon_tien_quyet;

    private Boolean mon_bat_buoc;

    private String ten_khoa;

    private String mo_ta;

    public MonHoc() {
    }

    public Integer getMaMonHoc() {
        return this.ma_mon_hoc;
    }

    public void setMaMonHoc(Integer ma_mon_hoc) {
        this.ma_mon_hoc = ma_mon_hoc;
    }

    public String getTenMonHoc() {
        return this.ten_mon_hoc;
    }

    public void setTenMonHoc(String ten_mon_hoc) {
        this.ten_mon_hoc = ten_mon_hoc;
    }

    public Integer getSoTinChi() {
        return this.so_tin_chi;
    }

    public void setSoTinChi(Integer so_tin_chi) {
        if (so_tin_chi < 0 || so_tin_chi > MAX_SO_TIN_CHI) {
            throw new IllegalArgumentException("number 'so_tin_chi' < 0");            
        }
        this.so_tin_chi = so_tin_chi;
    }

    public Integer getMaMonTienQuyet() {
        return this.ma_mon_tien_quyet;
    }

    public void setMonTienQuyet(Integer ma_mon_tien_quyet) {
        this.ma_mon_tien_quyet = ma_mon_tien_quyet;
    }
    public Boolean getMonBatBuoc() {
        return this.mon_bat_buoc;
    }

    public void setMonBatBuoc(Boolean mon_bat_buoc) {
        this.mon_bat_buoc = mon_bat_buoc;
    }

    public String getTenKhoa() {
        return this.ten_khoa;
    }

    public void setTenKhoa(String ten_khoa) {
        this.ten_khoa = ten_khoa;
    }

    public String getMoTa(){
        return mo_ta;
    }

    public void setMoTa(String mo_ta){
        this.mo_ta = mo_ta;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MonHoc)) {
            return false;
        }
        MonHoc monHoc = (MonHoc) o;
        return Objects.equals(ma_mon_hoc, monHoc.ma_mon_hoc) 
            && Objects.equals(ten_mon_hoc, monHoc.ten_mon_hoc) 
            && Objects.equals(so_tin_chi, monHoc.so_tin_chi) 
            && Objects.equals(ma_mon_tien_quyet, monHoc.ma_mon_tien_quyet) 
            && Objects.equals(mon_bat_buoc, monHoc.mon_bat_buoc) 
            && Objects.equals(ten_khoa, monHoc.ten_khoa) 
            && Objects.equals(mo_ta, monHoc.mo_ta);
    }

    @Override
    public String toString() {
        return "{" +
            " mã môn học = '" + getMaMonHoc() + "'" +
            ", tên môn học = '" + getTenMonHoc() + "'" +
            ", số tín chỉ = '" + getSoTinChi() + "'" +
            ", môn tiên quyết = '" + getMaMonTienQuyet() + "'" +
            ", môn bắt buộc = '" + getMonBatBuoc() + "'" +
            ", khoa = '" + getTenKhoa() + "'" +
            ", mô tả = '" + getMoTa() + "'" +
            "}";
    }

}