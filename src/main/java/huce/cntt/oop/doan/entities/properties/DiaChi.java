package huce.cntt.oop.doan.entities.properties;

import java.util.HashMap;
import java.util.Map;

enum Pho {
    SO, DUONG, NGO, QUAN, PHO
}

enum Que {
    XA, HUYEN, TINH
}

public class DiaChi {
    private Map<Que, String> dia_chi_o_que;

    private Map<Pho, String> dia_chi_o_pho;

// Use for convert
    public DiaChi(String diaChi){
        String[] cacThanhPhan = diaChi.split(",");
        int soThanhPhan = cacThanhPhan.length;

        if (soThanhPhan == 3) {
            if (cacThanhPhan[0].matches("\\d+")){
                dia_chi_o_pho = new HashMap<>();
                this.dia_chi_o_pho.put(Pho.SO, cacThanhPhan[0]);
                this.dia_chi_o_pho.put(Pho.DUONG, cacThanhPhan[1]);
                this.dia_chi_o_pho.put(Pho.QUAN, cacThanhPhan[2]);
            } else {
                dia_chi_o_que = new HashMap<>();
                this.dia_chi_o_que.put(Que.XA, cacThanhPhan[0]);
                this.dia_chi_o_que.put(Que.HUYEN, cacThanhPhan[1]);
                this.dia_chi_o_que.put(Que.TINH, cacThanhPhan[2]);
            }
        }
        else if (soThanhPhan == 4) {
            dia_chi_o_pho = new HashMap<>();
            this.dia_chi_o_pho.put(Pho.SO, cacThanhPhan[0]);
            this.dia_chi_o_pho.put(Pho.NGO, cacThanhPhan[1]);
            this.dia_chi_o_pho.put(Pho.DUONG, cacThanhPhan[2]);
            this.dia_chi_o_pho.put(Pho.QUAN, cacThanhPhan[3]);
        } else if (soThanhPhan == 5) {
            dia_chi_o_pho = new HashMap<>();
            this.dia_chi_o_pho.put(Pho.SO, cacThanhPhan[0]);
            this.dia_chi_o_pho.put(Pho.NGO, cacThanhPhan[1]);
            this.dia_chi_o_pho.put(Pho.PHO, cacThanhPhan[2]);
            this.dia_chi_o_pho.put(Pho.DUONG, cacThanhPhan[3]);
            this.dia_chi_o_pho.put(Pho.QUAN, cacThanhPhan[4]);
        } 
        else {
            throw new IllegalArgumentException("DiaChi thieu 1 so truong!!!");
        }
    }

// Use for insert
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
                + ", huyện " + this.dia_chi_o_que.get(Que.HUYEN)
                + ", tỉnh " + this.dia_chi_o_que.get(Que.TINH);
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


    public String formatToSaveDataBase(){
        if (this.dia_chi_o_pho != null) {
            if (this.dia_chi_o_pho.get(Pho.NGO) != null) {
                return this.dia_chi_o_pho.get(Pho.SO)
                        + ", " + this.dia_chi_o_pho.get(Pho.NGO)
                        + ", " + this.dia_chi_o_pho.get(Pho.DUONG)
                        + ", " + this.dia_chi_o_pho.get(Pho.QUAN);
            }
            return this.dia_chi_o_pho.get(Pho.SO)
                    + ", " + this.dia_chi_o_pho.get(Pho.DUONG)
                    + ", " + this.dia_chi_o_pho.get(Pho.QUAN);
        }
        if (this.dia_chi_o_que != null) {
            return this.dia_chi_o_que.get(Que.XA)
                    + ", " + this.dia_chi_o_que.get(Que.HUYEN)
                    + ", " + this.dia_chi_o_que.get(Que.TINH);
        }
        throw new NullPointerException("Missing value at diachi");       
    }
}