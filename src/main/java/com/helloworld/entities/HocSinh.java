package com.helloworld.entities;

public class HocSinh extends ConNguoi {
    
    @Override
    public String getEmail(){
        return this.getHoten().getTen().toLowerCase() + String.format("%05d", this.getMaso()) + "66" + "@huce.edu.vn";
    }

}
