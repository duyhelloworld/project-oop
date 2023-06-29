package huce.cntt.oop.doan.controller;

import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.entities.VaiTro;
import huce.cntt.oop.doan.loader.LoadTrangChu;
import huce.cntt.oop.doan.service.LoginService;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

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
    private LoginService loginService;
    private VaiTro vaiTro = VaiTro.NULL;

    private Stage stage;

    public LoginController() {
        this.loginService = LoginService.getInstance();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize(){
        footer.setText("Toàn bộ phần mềm được viết bởi Development By 2 Person!");

        alert = new Alert(AlertType.ERROR);
        nutQuenMatKhau.setOnMouseClicked(e -> {
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Chức năng này chưa được hỗ trợ!\nXin thông cảm, hãy liên hệ với hotline 0333666999.");
            alert.show();
        });

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
            GiangVien giangVienTrongDataBase = loginService
                .checkLogin(giangVien.getMaSo(), giangVien.getPassword());
            if (giangVienTrongDataBase != null) {
                if (loginService.checkAdmin(giangVien.getMaSo())) {
                    vaiTro = VaiTro.NVDT;
                } else {
                    vaiTro = VaiTro.GIANGVIEN;
                }
                Scene home = LoadTrangChu.loadTrangChu(stage, vaiTro, giangVienTrongDataBase);
                stage.setScene(home);
                stage.show();
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
}