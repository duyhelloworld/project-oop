package huce.cntt.oop.doan.entities;

public enum VaiTro {
    NVDT("Nhân viên đào tạo"), GIANGVIEN("Giảng viên"), NULL("Chưa rõ");

    private String tenInRa;
    private VaiTro(String tenInRa){
        this.tenInRa = tenInRa;
    }

    public String in(){
        return this.tenInRa;
    }
}
