package huce.cntt.oop.doan.entities;

import java.util.Objects;

public class Khoa {
    private Integer maKhoa;

    private String tenKhoa;

    public Khoa() {
    }

    public Integer getMaKhoa() {
        return this.maKhoa;
    }

    public void setMaKhoa(Integer maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return this.tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Khoa)) {
            return false;
        }
        Khoa khoa = (Khoa) o;
        return Objects.equals(maKhoa, khoa.maKhoa) 
            && Objects.equals(tenKhoa, khoa.tenKhoa);
    }

    @Override
    public String toString() {
        return
            "\tMã khoa = " + getMaKhoa() + "\n" +
            "\tTên khoa = " + getTenKhoa();
    }
    

}
