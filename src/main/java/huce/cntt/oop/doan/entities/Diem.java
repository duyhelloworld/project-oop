package huce.cntt.oop.doan.entities;

import java.util.Objects;

public class Diem {
    private Float so_buoi_diem_danh;
    private Float diem_giua_ki;
    private Float diem_cuoi_ki;
    private Float diem_qua_trinh;
    private Float diem_ket_thuc;

    public Diem() {
    }

    public Diem(Float so_buoi_diem_danh, Float diem_giua_ki, Float diem_cuoi_ki) {
        this.so_buoi_diem_danh = so_buoi_diem_danh;
        this.diem_giua_ki = diem_giua_ki;
        this.diem_cuoi_ki = diem_cuoi_ki;
    }

    public Float getSoBuoiDiemDanh() {
        return this.so_buoi_diem_danh;
    }

    public void setSoBuoiDiemDanh(Float so_buoi_diem_danh) {
        this.so_buoi_diem_danh = so_buoi_diem_danh;
    }

    public Float getDiemGiuaKi() {
        return this.diem_giua_ki;
    }

    public void setDiemGiuaKi(Float diem_giua_ki) {
        this.diem_giua_ki = diem_giua_ki;
    }

    public Float getDiemCuoiKi() {
        return this.diem_cuoi_ki;
    }

    public void setDiemCuoiKi(Float diem_cuoi_ki) {
        this.diem_cuoi_ki = diem_cuoi_ki;
    }

    public Float getDiemQuaTrinh() {
        return this.so_buoi_diem_danh / 10 + this.diem_giua_ki * 0.7f;
    }

    public Float getDiemKetThuc() {
        return this.diem_qua_trinh * 0.3f + this.diem_cuoi_ki * 0.7f;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Diem)) {
            return false;
        }
        Diem diem = (Diem) o;
        return Objects.equals(so_buoi_diem_danh, diem.so_buoi_diem_danh) && Objects.equals(diem_giua_ki, diem.diem_giua_ki) && Objects.equals(diem_cuoi_ki, diem.diem_cuoi_ki) && Objects.equals(diem_qua_trinh, diem.diem_qua_trinh) && Objects.equals(diem_ket_thuc, diem.diem_ket_thuc);
    }

    @Override
    public String toString() {
        return "{" +
            ", diem_qua_trinh='" + getDiemQuaTrinh() + "'" +
            ", diem_ket_thuc='" + getDiemKetThuc() + "'" +
            "}";
    }
    

}
