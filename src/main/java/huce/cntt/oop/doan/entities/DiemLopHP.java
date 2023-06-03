package huce.cntt.oop.doan.entities;
import java.util.Objects;

public class DiemLopHP {
    private Integer MSSV;
    private String hoVaTen;
    private String lopQL;
    private Float diemChuyenCan;
    private Float diemGiuaKi;
    private Float diemQuaTrinh;
    private Float diemCuoiKi;
    private Float diemTongKet;
    private Float diemTongKetHe4;
    private DiemChu diemChu;
    private String tenLopMonHoc;
    private Integer maLopHP;

    public DiemLopHP() {
    }


    public Integer getMaLopHP() {
        return this.maLopHP;
    }

    public void setMaLopHP(Integer maLopHP) {
        this.maLopHP = maLopHP;
    }

    public DiemLopHP maLopHP(Integer maLopHP) {
        setMaLopHP(maLopHP);
        return this;
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

    public String getHoVaTen() {
        return this.hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
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
        return this.diemQuaTrinh;
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
        return this.diemTongKet;
    }

    public void setDiemTongKet(Float diemTongKet) {
        this.diemTongKet = diemTongKet;
    }

    public Float getDiemTongKetHe4() {
        return this.diemTongKetHe4;
    }

    public void setDiemTongKetHe4(Float diemTongKetHe4) {
        this.diemTongKetHe4 = diemTongKetHe4;
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
        if (!(o instanceof DiemLopHP)) {
            return false;
        }
        DiemLopHP diemLopHP = (DiemLopHP) o;
        return Objects.equals(MSSV, diemLopHP.MSSV) && Objects.equals(hoVaTen, diemLopHP.hoVaTen) && Objects.equals(lopQL, diemLopHP.lopQL) && Objects.equals(diemChuyenCan, diemLopHP.diemChuyenCan) && Objects.equals(diemGiuaKi, diemLopHP.diemGiuaKi) && Objects.equals(diemQuaTrinh, diemLopHP.diemQuaTrinh) && Objects.equals(diemCuoiKi, diemLopHP.diemCuoiKi) && Objects.equals(diemTongKet, diemLopHP.diemTongKet) && Objects.equals(diemTongKetHe4, diemLopHP.diemTongKetHe4) && Objects.equals(diemChu, diemLopHP.diemChu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MSSV, hoVaTen, lopQL, diemChuyenCan, diemGiuaKi, diemQuaTrinh, diemCuoiKi, diemTongKet, diemTongKetHe4, diemChu);
    }

    @Override
    public String toString() {
        return "{" +
            " MSSV='" + getMSSV() + "'" +
            ", hoVaTen='" + getHoVaTen() + "'" +
            ", lopQL='" + getLopQL() + "'" +
            ", diemChuyenCan='" + getDiemChuyenCan() + "'" +
            ", diemGiuaKi='" + getDiemGiuaKi() + "'" +
            ", diemQuaTrinh='" + getDiemQuaTrinh() + "'" +
            ", diemCuoiKi='" + getDiemCuoiKi() + "'" +
            ", diemTongKet='" + getDiemTongKet() + "'" +
            ", diemTongKetHe4='" + getDiemTongKetHe4() + "'" +
            ", diemChu='" + getDiemChu() + "'" +
            "}";
    }
    
}
