package com.helloworld.entities.properties;

import java.util.Map;
import java.util.HashMap;

enum Pho {
    SO, DUONG, NGO, QUAN, PHO
}

enum Que {
    XA, HUYEN, TINH
}

public class DiaChi {
    private Map<Que, String> dia_chi_o_que = new HashMap<>();

    private Map<Pho, String> dia_chi_o_pho = new HashMap<>();

    public DiaChi(Integer so, String duong, String quan) {
        this.dia_chi_o_pho.put(Pho.SO, so.toString());
        this.dia_chi_o_pho.put(Pho.DUONG, duong);
        this.dia_chi_o_pho.put(Pho.QUAN, quan);
    }

    public DiaChi(Integer so, Integer ngo, String duong, String quan) {
        this(so, duong, quan);
        this.dia_chi_o_pho.put(Pho.NGO, ngo.toString());
    }

    public DiaChi(String xa, String huyen, String tinh) {
        this.dia_chi_o_que.put(Que.XA, xa);
        this.dia_chi_o_que.put(Que.HUYEN, huyen);
        this.dia_chi_o_que.put(Que.TINH, tinh);
    }

    @Override
    public String toString() {
        if(this.dia_chi_o_que != null) {
            return "xã " + this.dia_chi_o_que.get(Que.XA) 
                + ",huyện " + this.dia_chi_o_que.get(Que.HUYEN)
                + ",tỉnh " + this.dia_chi_o_que.get(Que.TINH);
        } 

        if (this.dia_chi_o_pho.get(Pho.NGO) != null) {
            return "số " + this.dia_chi_o_pho.get(Pho.SO)
                + ", ngõ " + this.dia_chi_o_pho.get(Pho.NGO)
                + ", đường " + this.dia_chi_o_pho.get(Pho.DUONG) 
                + ", quận " + this.dia_chi_o_pho.get(Pho.QUAN);
        }
        
        return "số " + this.dia_chi_o_pho.get(Pho.SO)
                + ", đường " + this.dia_chi_o_pho.get(Pho.DUONG) 
                + ", quận " + this.dia_chi_o_pho.get(Pho.QUAN);
    }

}
