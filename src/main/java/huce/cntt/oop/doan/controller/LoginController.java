package huce.cntt.oop.doan.controller;

import java.util.regex.Pattern;

import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.service.GiangVienService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private Label footer;
    @FXML
    private TextField maGiangVienField;
    @FXML
    private PasswordField matKhauField;
    @FXML
    private Button nutDangNhap;
    @FXML
    private Label nutQuenMatKhau;

    private Alert alert;
    private GiangVienService giangVienService;

    public LoginController() {
        this.giangVienService = GiangVienService.getInstance();
    }

    @FXML
    public void initialize(){
        footer.setText("Toàn bộ phần mềm được viết bởi Development By 2 Person!");

        alert = new Alert(AlertType.NONE);
        nutQuenMatKhau.setOnMouseClicked(e -> {
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Chức năng này chưa được hỗ trợ!\nXin thông cảm ");
            alert.show();
        });

        nutDangNhap.setOnAction(e -> {
            if (!nutDangNhap.isPressed()) {
                try {
                    GiangVien giangVien = layThongTin();
                    boolean thanhCong = giangVienService
                        .checkLogin(giangVien.getMaSo(), giangVien.getPassword());
                    if (thanhCong) {
                        chuyenHuong();
                    } else {
                        throw new Exception("Thông tin đăng nhập không chính xác.\nHãy kiểm tra lại!");
                    }
                } catch (Exception ex) {
                    alert.setContentText(ex.getMessage());
                    alert.show();
                    ex.printStackTrace();
                }
            }
        });
    }

    private GiangVien layThongTin() throws Exception {
        GiangVien giangVien = new GiangVien();
        String maGiangVien = maGiangVienField.getText();
        String matKhau = matKhauField.getText();

        giangVien.setMaSoString(maGiangVien);
        if (giangVien.getMaSo() == null) {
            throw new Exception("Dãy nhập vào sai định dạng!");
        }

        giangVien.setPassword(matKhau);
        if (giangVien.getPassword() == null) {
            throw new Exception("Mật khẩu thiếu sức sống (> 8 kí tự, không chứa khoảng trắng)");
        }
        
        return giangVien;
    }

    private void chuyenHuong() {
        System.out.println("Redirect ... ");
    }
}