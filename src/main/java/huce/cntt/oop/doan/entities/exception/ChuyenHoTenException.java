package huce.cntt.oop.doan.entities.exception;

public class ChuyenHoTenException extends Exception{
    private String message;

    public ChuyenHoTenException(){
        message = "Có lỗi trong quá trình nhận dạng họ tên nhập vào\nHãy thử lại";
    }

    public ChuyenHoTenException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
