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
    private String diemChu;
    private String tenLopMonHoc;
    private Integer maLopHP;

    public DiemLopHP() {
    }

    public DiemLopHP(Integer MSSV, String hoVaTen, String lopQL, Float diemChuyenCan, Float diemGiuaKi, Float diemQuaTrinh, Float diemCuoiKi, Float diemTongKet, Float diemTongKetHe4, String diemChu, String tenLopMonHoc, Integer maLopHP) {
        this.MSSV = MSSV;
        this.hoVaTen = hoVaTen;
        this.lopQL = lopQL;
        this.diemChuyenCan = diemChuyenCan;
        this.diemGiuaKi = diemGiuaKi;
        this.diemQuaTrinh = diemQuaTrinh;
        this.diemCuoiKi = diemCuoiKi;
        this.diemTongKet = diemTongKet;
        this.diemTongKetHe4 = diemTongKetHe4;
        this.diemChu = diemChu;
        this.tenLopMonHoc = tenLopMonHoc;
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

    public DiemLopHP tenLopMonHoc(String tenLopMonHoc) {
        setTenLopMonHoc(tenLopMonHoc);
        return this;
    }

    public void setTenLopMonHoc(String tenLopMonhoc){
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
        return this.diemChu;
    }

    public void setDiemChu(String diemChu) {
        this.diemChu = diemChu;
    }

    public DiemLopHP MSSV(Integer MSSV) {
        setMSSV(MSSV);
        return this;
    }

    public DiemLopHP hoVaTen(String hoVaTen) {
        setHoVaTen(hoVaTen);
        return this;
    }

    public DiemLopHP lopQL(String lopQL) {
        setLopQL(lopQL);
        return this;
    }

    public DiemLopHP diemChuyenCan(Float diemChuyenCan) {
        setDiemChuyenCan(diemChuyenCan);
        return this;
    }

    public DiemLopHP diemGiuaKi(Float diemGiuaKi) {
        setDiemGiuaKi(diemGiuaKi);
        return this;
    }

    public DiemLopHP diemQuaTrinh(Float diemQuaTrinh) {
        setDiemQuaTrinh(diemQuaTrinh);
        return this;
    }

    public DiemLopHP diemCuoiKi(Float diemCuoiKi) {
        setDiemCuoiKi(diemCuoiKi);
        return this;
    }

    public DiemLopHP diemTongKet(Float diemTongKet) {
        setDiemTongKet(diemTongKet);
        return this;
    }

    public DiemLopHP diemTongKetHe4(Float diemTongKetHe4) {
        setDiemTongKetHe4(diemTongKetHe4);
        return this;
    }

    public DiemLopHP diemChu(String diemChu) {
        setDiemChu(diemChu);
        return this;
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
