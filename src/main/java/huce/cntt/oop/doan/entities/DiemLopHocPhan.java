package huce.cntt.oop.doan.entities;
import java.util.Objects;

public class DiemLopHocPhan {
    private Integer MSSV;
    private String hoTen;
    private String lopQL;
    private Float diemChuyenCan;
    private Float diemGiuaKi;
    private Float diemQuaTrinh;
    private Float diemCuoiKi;
    private Float diemTongKet;
    private Float diemHe4;
    private DiemChu diemChu;
    private String tenLopMonHoc;
    private Integer maLopHP;

    public DiemLopHocPhan() {
    }

    public Integer getMaLopHP() {
        return this.maLopHP;
    }

    public void setMaLopHP(Integer maLopHP) {
        this.maLopHP = maLopHP;
    }

    public String getTenLopMonHoc(){
        return this.tenLopMonHoc;
    }

    public void setTenLopMonHoc(String tenLopMonHoc){
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

    public String getLopQL() {
        return this.lopQL;
    }

    public void setLopQL(String lopQL) {
        this.lopQL = lopQL;
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
        diemQuaTrinh = diemChuyenCan / 10f + diemGiuaKi * 0.7f;
        return Float.parseFloat(String.format("%.1f", diemQuaTrinh));
    }

    public void setDiemQuaTrinh(Float diemQuaTrinh) {
        this.diemQuaTrinh = diemQuaTrinh;
    }

    public Float getDiemCuoiKi() {
        return this.diemCuoiKi;
    }

    public void setDiemCuoiKi(Float diemCuoiKi) {
        this.diemCuoiKi = diemCuoiKi;
    }

    public Float getDiemTongKet() {
        diemTongKet = getDiemQuaTrinh() * 0.3f + getDiemCuoiKi() * 0.7f;
        return Float.parseFloat(String.format("%.1f", diemTongKet));
    }

    public void setDiemTongKet(Float diemTongKet) {
        this.diemTongKet = diemTongKet;
    }

    public Float getDiemHe4() {
        return Float.parseFloat(String.format("%.1f", getDiemTongKet() * 0.2f));
    }

    public void setDiemHe4(Float diemHe4) {
        this.diemHe4 = diemHe4;
    }

    public String getDiemChu() {
        return DiemChu.tuDiemSo(diemTongKet).toString();
    }

    public void setDiemChu(DiemChu diemChu) {
        this.diemChu = diemChu;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DiemLopHocPhan)) {
            return false;
        }
        DiemLopHocPhan diemLopHP = (DiemLopHocPhan) o;
        return Objects.equals(MSSV, diemLopHP.MSSV) && Objects.equals(hoTen, diemLopHP.hoTen) && Objects.equals(lopQL, diemLopHP.lopQL) && Objects.equals(diemChuyenCan, diemLopHP.diemChuyenCan) && Objects.equals(diemGiuaKi, diemLopHP.diemGiuaKi) && Objects.equals(diemQuaTrinh, diemLopHP.diemQuaTrinh) && Objects.equals(diemCuoiKi, diemLopHP.diemCuoiKi) && Objects.equals(diemTongKet, diemLopHP.diemTongKet) && Objects.equals(diemHe4, diemLopHP.diemHe4) && Objects.equals(diemChu, diemLopHP.diemChu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MSSV, hoTen, lopQL, diemChuyenCan, diemGiuaKi, diemQuaTrinh, diemCuoiKi, diemTongKet, diemHe4, diemChu);
    }

    @Override
    public String toString() {
        return "{" +
            " MSSV = '" + getMSSV() + "'" +
            "Họ Tên = '" + getHoTen() + "'" +
            ", lopQL='" + getLopQL() + "'" +
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
