package huce.cntt.oop.doan.controller;

import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.service.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

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

    public static Boolean thanhCongDangNhap;

    private Alert alert;
    private LoginService loginService;

    public LoginController() {
        this.loginService = LoginService.getInstance();
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
        thanhCongDangNhap = false;

        nutDangNhap.setOnAction(e -> {
            if (!nutDangNhap.isPressed()) {
                login();
            }
        });

        matKhauField.setOnKeyPressed(ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                login();
            }                
        });
    }

    private void login() {
        try {
            GiangVien giangVien = layThongTin();
            boolean thanhCong = loginService
                .checkLogin(giangVien.getMaSo(), giangVien.getPassword());
            if (thanhCong) {
                thanhCongDangNhap = true;
                if (loginService.checkAdmin(giangVien.getMaSo())) {
                    redirect(true);
                } else {
                    redirect(false);
                }
            } 
            else {
                throw new IllegalArgumentException("Thông tin đăng nhập không chính xác.\nHãy kiểm tra lại!");
            }
        } catch (IllegalArgumentException ex) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    private GiangVien layThongTin() throws IllegalArgumentException {
        GiangVien giangVien = new GiangVien();
        String maGiangVien = maGiangVienField.getText();
        String matKhau = matKhauField.getText();
        if (maGiangVien == null || maGiangVien.isBlank()) {
            throw new IllegalArgumentException("Thiếu mã giảng viên !");
        }
        if (matKhau == null || matKhau.isBlank()) {
            throw new IllegalArgumentException("Thiếu mật khẩu !");
        }

        giangVien.setMaSoString(maGiangVien);
        giangVien.setPassword(matKhau);

        return giangVien;
    }

    public void redirect(boolean isAdmin) {
        System.out.println(isAdmin);
        // if (isAdmin) {
        // }
    }
}