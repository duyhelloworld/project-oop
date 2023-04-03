package com.helloworld.entities.properties;

public class HoTen {
    private String ho;

    private String ten_dem;

    private String ten;

    public HoTen(String ho, String ten_dem, String ten) {
        this.ho = ho;
        this.ten_dem = ten_dem;
        this.ten = ten;
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
