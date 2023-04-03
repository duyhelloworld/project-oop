package com.helloworld.entities;

public class ThayGiao extends ConNguoi {
    public ThayGiao(){
        
    }

    @Override
    public String getEmail() {
        String ho_ten_viet_tat = this.getHoten().getTen() + this.getHoten().getHo().substring(0) + this.getHoten().getTen_dem();
        return ho_ten_viet_tat.toLowerCase() + "@huce.edu.vn";
    }
}
