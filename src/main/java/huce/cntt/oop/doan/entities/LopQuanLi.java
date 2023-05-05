package huce.cntt.oop.doan.entities;

import java.util.Objects;

public class LopQuanLi {
    private String ten_lop;

    private Integer si_so;

    private String khoa;

    public LopQuanLi() {
    }

    public LopQuanLi(String ten_lop, Integer si_so, String khoa) {
        this.ten_lop = ten_lop;
        this.si_so = si_so;
        this.khoa = khoa;
    }

    public String getTenLop() {
        return this.ten_lop;
    }

    public void setTenLop(String ten_lop) {
        this.ten_lop = ten_lop;
    }

    public Integer getSiSo() {
        return this.si_so;
    }

    public void setSiSo(Integer si_so) {
        this.si_so = si_so;
    }

    public String getKhoa() {
        return this.khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public LopQuanLi ten_lop(String ten_lop) {
        setTenLop(ten_lop);
        return this;
    }

    public LopQuanLi si_so(Integer si_so) {
        setSiSo(si_so);
        return this;
    }

    public LopQuanLi khoa(String khoa) {
        setKhoa(khoa);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LopQuanLi)) {
            return false;
        }
        LopQuanLi lopMonHoc = (LopQuanLi) o;
        return Objects.equals(ten_lop, lopMonHoc.ten_lop) 
            && Objects.equals(si_so, lopMonHoc.si_so) 
            && Objects.equals(khoa, lopMonHoc.khoa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ten_lop, si_so, khoa);
    }

    @Override
    public String toString() {
        return "tên lớp : " + getTenLop() + "\n"
             + "sĩ số : " + getSiSo() + "\n"
             + "khoa : " + getKhoa() + "\n";
    }
}
