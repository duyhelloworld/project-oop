package huce.cntt.oop.doan.entities.exception;

public class NgayGioException extends Exception {
    private String message;

    public NgayGioException(){
        message = "Có lỗi trong quá trình nhận dạng ngày nhập vào\nHãy thử lại";
    }

    public NgayGioException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
