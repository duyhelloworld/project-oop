package huce.cntt.oop.doan.entities.properties;

import java.util.Arrays;

import huce.cntt.oop.doan.entities.exception.ChuyenHoTenException;

public class HoTen {
    
    private String ho;

    private String tenDem;

    private String ten;

    public HoTen(String hoTen) throws ChuyenHoTenException {
        try {
            String[] chiaTen = hoTen.split(" ");
            this.ho = chiaTen[0];
            this.ten = chiaTen[chiaTen.length - 1];
            if (chiaTen.length >= 2) {
                this.tenDem = String.join(" ", Arrays.copyOfRange(chiaTen, 1, chiaTen.length - 1));
            }
            else {
                this.tenDem = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ChuyenHoTenException();
        }
    }

    public String getHo() {
        return this.ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTenDem() {
        return this.tenDem;
    }

    public void setTenDem(String tenDem) {
        this.tenDem = tenDem;
    }

    public String getTen() {
        return this.ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof HoTen)) {
            return false;
        }
        HoTen hoten = (HoTen) obj;
        return hoten.ten.equals(ten) 
            && hoten.ho.equals(ho) 
            && hoten.tenDem.equals(tenDem);
    }

    @Override
    public String toString() {
        return getHo()
                + " " + getTenDem()
                + " " + getTen();
    }
}
