package huce.cntt.oop.doan.entities;

public enum DiemChu {
    A("A"),
    B_CONG("B+"), B("B"),
    C_CONG("C+"), C("C"),
    D_CONG("D+"), D("D"),
    F("F");
    
    private String diemChu;

    DiemChu(String diemChu) {
        this.diemChu = diemChu;
    }

    @Override
    public String toString() {
        return diemChu;
    }

    public static DiemChu tuDiemSo(Float diem) {
        if (diem >= 8.5f)
            return A;
        else if (diem >= 8.0f)
            return B_CONG;
        else if (diem >= 7.0f)
            return B;
        else if (diem >= 6.5f)
            return C_CONG;
        else if (diem >= 5.5f)
            return C;
        else if (diem >= 5.0f)
            return D_CONG;
        else if (diem >= 4.0f)
            return D;
        else 
            return F;
    }
}
