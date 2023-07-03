package huce.cntt.oop.doan.entities;

public enum DiemChu {
    A_("+"), A("A"),
    B_("B+"), B("B"),
    C_("C+"), C("C"),
    D_("D+"), D("D"),
    F("F");
    
    private String cong;

    DiemChu(String cong) {
        this.cong = cong;
    }

    DiemChu() {
        this.cong = "";
    }

    @Override
    public String toString() {
        return  cong;
    }

    public static DiemChu tuDiemSo(Float diem) {
        if (diem >= 8.5f)
            return A;
        else if (diem >= 8.0f)
            return B_;
        else if (diem >= 7.0f)
            return B;
        else if (diem >= 6.5f)
            return C_;
        else if (diem >= 5.5f)
            return C;
        else if (diem >= 5.0f)
            return D_;
        else if (diem >= 4.0f)
            return D;
        else 
            return F;
        
    }
}
