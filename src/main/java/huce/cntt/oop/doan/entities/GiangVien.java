package huce.cntt.oop.doan.entities;

import java.util.Objects;
import java.util.regex.Pattern;

import huce.cntt.oop.doan.entities.parents.ConNguoi;


public class GiangVien extends ConNguoi {

    private String password;

    public void setMaSoString(String maSoGV) {
        if (maSoGV.matches("^\\d+$")) {
            this.setMaSo(Integer.parseInt(maSoGV));
        }
    }

    public GiangVien() {
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        if (Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", password)) {
            this.password = password;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GiangVien)) {
            return false;
        }
        GiangVien giangVien = (GiangVien) o;
        return super.equals(giangVien) 
            && Objects.equals(password, giangVien.password);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
