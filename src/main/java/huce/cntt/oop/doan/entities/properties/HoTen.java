package huce.cntt.oop.doan.entities.properties;

import java.util.Arrays;

public class HoTen {
    
    private String ho;

    private String ten_dem;

    private String ten;

    public HoTen(String ho, String ten_dem, String ten) {
        this.ho = ho;
        this.ten_dem = ten_dem;
        this.ten = ten;
    }

    public HoTen(String hoTen) {
        String[] chiaTen = hoTen.split(" ");
        this.ho = chiaTen[0];
        this.ten = chiaTen[chiaTen.length - 1];
        if (chiaTen.length >= 2) {
            this.ten_dem = String.join(" ", Arrays.copyOfRange(chiaTen, 1, chiaTen.length - 1));
        }
        else {
            this.ten_dem = "";
        }
    }

    public String getHo() {
        return this.ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen_dem() {
        return this.ten_dem;
    }

    public void setTen_dem(String ten_dem) {
        this.ten_dem = ten_dem;
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
        return hoten.ten.equals(ten) && hoten.ho.equals(ho) && hoten.ten_dem.equals(ten_dem);
    }

    @Override
    public String toString() {
        return getHo()
                + " " + getTen_dem()
                + " " + getTen();
    }
}
