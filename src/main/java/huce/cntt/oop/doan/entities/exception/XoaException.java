package huce.cntt.oop.doan.entities.exception;

public class XoaException extends Exception {
    private String message;

    public XoaException(Integer maSo, String doiTuong){
        message = "Có lỗi trong quá trình xóa " + doiTuong + " có mã số " + maSo + ".\nHãy thử lại";
    }

    public XoaException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
