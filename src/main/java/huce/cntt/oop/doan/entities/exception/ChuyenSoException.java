package huce.cntt.oop.doan.entities.exception;

public class ChuyenSoException extends Exception {
    private String message;

    public ChuyenSoException(){
        message = "Có lỗi trong quá trình nhận dạng số nhập vào\nHãy thử lại";
    }

    public ChuyenSoException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
