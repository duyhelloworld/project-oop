package huce.cntt.oop.doan.entities.exception;

public class ThemException extends Exception {
    private String message;

    public ThemException(Integer maSo, String doiTuong){
        message = "Có lỗi trong quá trình tạo " + doiTuong + " có mã số " + maSo + ".\nHãy thử lại";
    }

    public ThemException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
