package huce.cntt.oop.doan.entities;

import java.util.Objects;

public class Khoa {
    private Integer ma_khoa;

    private String ten_khoa;

    public Khoa() {
    }

    public Integer getMaKhoa() {
        return this.ma_khoa;
    }

    public void setMaKhoa(Integer ma_khoa) {
        this.ma_khoa = ma_khoa;
    }

    public String getTenKhoa() {
        return this.ten_khoa;
    }

    public void setTenKhoa(String ten_khoa) {
        this.ten_khoa = ten_khoa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Khoa)) {
            return false;
        }
        Khoa khoa = (Khoa) o;
        return Objects.equals(ma_khoa, khoa.ma_khoa) 
        && Objects.equals(ten_khoa, khoa.ten_khoa);
    }

    @Override
    public String toString() {
        return "{" +
            " mã khoa ='" + getMaKhoa() + "'" +
            ", tên khoa='" + getTenKhoa() + "'" +
            "}";
    }
    

}
