package huce.cntt.oop.doan.entities;

import java.util.Objects;

public class Diem {
    private Float diem_chuyen_can;
    private Float diem_giua_ki;
    private Float diem_cuoi_ki;

    private Float diem_qua_trinh;
    private Float diem_ket_thuc;

    public Diem() {
    }

    public Float getDiemChuyenCan() {
        return this.diem_chuyen_can;
    }

    public void setDiemChuyenCan(Float diem_chuyen_can) {
        if (diem_chuyen_can < 0 || diem_chuyen_can > 10) {
            throw new IllegalArgumentException("Giá trị 'điểm chuyên cần' không hợp lệ!");
        }
        this.diem_chuyen_can = diem_chuyen_can;
    }

    public Float getDiemGiuaKi() {
        return this.diem_giua_ki;
    }

    public void setDiemGiuaKi(Float diem_giua_ki) {
        if (diem_giua_ki < 0 || diem_giua_ki > 10) {
            throw new IllegalArgumentException("Giá trị 'điểm giữa kì' không hợp lệ!");
        }
        this.diem_giua_ki = diem_giua_ki;
    }

    public Float getDiemCuoiKi() {
        return this.diem_cuoi_ki;
    }

    public void setDiemCuoiKi(Float diem_cuoi_ki) {
        if (diem_cuoi_ki < 0 || diem_cuoi_ki > 10) {
            throw new IllegalArgumentException("Giá trị 'điểm cuối kì' không hợp lệ!");
        }
        this.diem_cuoi_ki = diem_cuoi_ki;
    }


    public Float getDiemQuaTrinh() {
        this.diem_qua_trinh = this.diem_chuyen_can / 10 + this.diem_giua_ki * 0.7f;
        return this.diem_qua_trinh;
    }

    public Float getDiemKetThuc() {
        this.diem_ket_thuc = this.diem_qua_trinh * 0.3f + this.diem_cuoi_ki * 0.7f;
        return this.diem_ket_thuc;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Diem)) {
            return false;
        }
        Diem diem = (Diem) o;
        return Objects.equals(diem_giua_ki, diem.diem_giua_ki)
                && Objects.equals(diem_chuyen_can, diem.diem_chuyen_can)
                && Objects.equals(diem_cuoi_ki, diem.diem_cuoi_ki);
    }

    @Override
    public String toString() {
        return "{" +
            "diem_chuyen_can='" + getDiemQuaTrinh() + "'" +
            ", diem_qua_trinh='" + getDiemQuaTrinh() + "'" +
            ", diem_ket_thuc='" + getDiemKetThuc() + "'" +
            "}";
    }
}
