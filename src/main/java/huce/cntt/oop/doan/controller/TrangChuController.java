package huce.cntt.oop.doan.controller;

import java.util.Optional;

import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.entities.VaiTro;
import huce.cntt.oop.doan.loader.LoadDiemCaNhan;
import huce.cntt.oop.doan.loader.LoadDiemHP;
import huce.cntt.oop.doan.loader.LoadLogin;
import huce.cntt.oop.doan.loader.LoadMonHoc;
import huce.cntt.oop.doan.loader.LoadSinhVien;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TrangChuController {
    private VaiTro vaiTro;
    private GiangVien giangVien;
    private Stage stage;

    public TrangChuController(VaiTro vaiTro, GiangVien giangVien, Stage stage) {
        this.vaiTro = vaiTro;
        this.giangVien = giangVien;
        this.stage = stage;
    }

    @FXML
    private TextField tenTKTextField;
    @FXML
    private TextField chucVuTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField sdtTextField;
    @FXML
    private Button nutSinhVien;
    @FXML
    private Button nutDiemCaNhan;
    @FXML
    private Button nutMonHoc;
    @FXML
    private Button nutLopHocPhan;
    @FXML
    private Label dangXuat;
    @FXML
    private ImageView anhTaiKhoan;

    @FXML
    public void initialize() {
        tenTKTextField.setText(giangVien.getHoTen().toString());
        chucVuTextField.setText(vaiTro.getTenIn());
        emailTextField.setText(giangVien.getEmail());
        sdtTextField.setText(giangVien.getSoDienThoai());
        
        if (vaiTro == VaiTro.GIANGVIEN) {
            nutSinhVien.setDisable(true);
            nutMonHoc.setDisable(true);
            nutDiemCaNhan.setDisable(true);
            anhTaiKhoan.setImage(new Image("anhthe_giangvien.jpg"));
        } else if (vaiTro == VaiTro.NVDT) {
            nutLopHocPhan.setDisable(true);
            anhTaiKhoan.setImage(new Image("anhthe_nvdt.jpg"));
        }
    
        dangXuat.setOnMouseClicked(e -> {
            loadDangXuat();
        });
        nutSinhVien.setOnAction(e -> {
            loadSinhVien();
        });
        nutLopHocPhan.setOnAction(e -> {
            loadDiemHocPhan();
        });
        nutMonHoc.setOnAction(e -> {
            loadMonHoc();
        });
        nutDiemCaNhan.setOnAction(e -> {
            loadDiemCaNhan();
        });
    }

    private void loadSinhVien() {
        if (nutSinhVien.isPressed()) {
            return;
        }
        Scene sinhVien = LoadSinhVien.loadSinhVien(stage, giangVien);
        stage.setScene(sinhVien);
        stage.show();
    }

    private void loadDiemCaNhan() {
        if (nutDiemCaNhan.isPressed()) {
            return;
        }
        Scene diemCaNhan = LoadDiemCaNhan.loadDiemCaNhan(stage, giangVien);
        stage.setScene(diemCaNhan);
        stage.show();
    }

    private void loadMonHoc() {
        if (nutMonHoc.isPressed()) {
            return;
        }
        Scene monHoc = LoadMonHoc.loadMonHoc(stage, giangVien);
        stage.setScene(monHoc);
        stage.show();
    }

    private void loadDiemHocPhan() {
        if (nutLopHocPhan.isPressed()) {
            return;
        }
        Scene lopHocPhan = LoadDiemHP.loadDiemHocPhan(stage, giangVien);
        stage.setScene(lopHocPhan);
        stage.show();
    }

    @FXML
    private void loadDangXuat() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Bạn có chắc muốn đăng xuất?");
        Optional<ButtonType> dangXuat = alert.showAndWait();
        if (dangXuat.isPresent() && dangXuat.get() == ButtonType.OK) {
            Scene login = LoadLogin.loadLoginScreen(stage);
            stage.setScene(login);
            stage.show();
        }
    }
}
