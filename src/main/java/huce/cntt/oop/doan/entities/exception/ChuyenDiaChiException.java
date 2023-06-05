package huce.cntt.oop.doan.entities.exception;

public class ChuyenDiaChiException extends Exception {
    private String message;

    public ChuyenDiaChiException(){
        message = "Có lỗi trong quá trình nhận dạng địa chỉ nhập vào\nHãy thử lại";
    }

    public ChuyenDiaChiException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
