package huce.cntt.oop.doan.entities.exception;

public class ThieuGiaTriException extends Exception {
    private String viTriThieu;

    public ThieuGiaTriException(String viTriThieu) {
        this.viTriThieu = "Thiếu giá trị ở " + viTriThieu + "\nHãy kiểm tra lại!";
    }

    @Override
    public String getMessage() {
        return this.viTriThieu;
    }
}
