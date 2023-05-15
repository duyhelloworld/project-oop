package huce.cntt.oop.doan.entities;

import java.util.Objects;

import huce.cntt.oop.doan.entities.parents.ConNguoi;

public class NhanVienDaoTao extends ConNguoi {
    private String password;

    public NhanVienDaoTao() {
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("'password' length is not enough!");
        }
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof NhanVienDaoTao)) {
            return false;
        }
        NhanVienDaoTao nhanVienDaoTao = (NhanVienDaoTao) o;
        return super.equals(nhanVienDaoTao) && Objects.equals(password, nhanVienDaoTao.password);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
