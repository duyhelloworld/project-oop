package huce.cntt.oop.doan.entities.exception;

public class KhoaLopException extends Exception {
    private String message;

    public KhoaLopException(){
        message = "Khoa và Lớp xảy ra xung đột!!!\nHãy thử lại";
    }

    public KhoaLopException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
