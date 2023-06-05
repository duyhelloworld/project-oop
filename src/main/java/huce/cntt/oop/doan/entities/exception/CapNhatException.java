package huce.cntt.oop.doan.entities.exception;

public class CapNhatException extends Exception {
    private String message;

    public CapNhatException(Integer maSo, String doiTuong){
        message = "Có lỗi trong quá trình cập nhật " + doiTuong + " có mã số " + maSo + ".\nHãy thử lại";
    }

    public CapNhatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
