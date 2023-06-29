package huce.cntt.oop.doan.controller;

import java.util.Optional;

import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.entities.VaiTro;
import huce.cntt.oop.doan.loader.LoadDiemCaNhan;
import huce.cntt.oop.doan.loader.LoadDiemHocPhan;
import huce.cntt.oop.doan.loader.LoadLogin;
import huce.cntt.oop.doan.loader.LoadMonHoc;
import huce.cntt.oop.doan.loader.LoadSinhVien;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TrangChuController {
    private VaiTro vaiTro;
    private GiangVien giangVien;
    private Stage stage;

    public TrangChuController(){
        vaiTro = VaiTro.NULL;
        giangVien = new GiangVien();
    }

    public TrangChuController(VaiTro vaiTro, GiangVien giangVien, Stage stage) {
        this.vaiTro = vaiTro;
        this.giangVien = giangVien;
        this.stage = stage;
    }

    public void setVaiTro(VaiTro vaiTro){
        this.vaiTro = vaiTro;
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
    private Button nutDangXuat;

    @FXML
    public void initialize() {
        tenTKTextField.setText(giangVien.getHoTen().toString());
        chucVuTextField.setText(vaiTro.in());
        emailTextField.setText(giangVien.getEmail());
        sdtTextField.setText(giangVien.getSoDienThoai());

        if (vaiTro == VaiTro.GIANGVIEN) {
            nutSinhVien.setDisable(true);
            nutMonHoc.setDisable(true);
            nutDiemCaNhan.setDisable(true);
        } else if (vaiTro == VaiTro.NVDT) {
            nutLopHocPhan.setDisable(true);
        }

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
        nutDangXuat.setOnAction(e -> {
            loadDangXuat();
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
        Scene lopHocPhan = LoadDiemHocPhan.loadDiemHocPhan(stage, giangVien);
        stage.setScene(lopHocPhan);
        stage.show();
    }

    private void loadDangXuat() {
        if (nutDangXuat.isPressed()) {
            return;
        }
        String duongDanAnhDangXuat = getClass().getResource("/dang-xuat-khoi-trai-dat.jpg").toExternalForm();
        Image image = new Image(duongDanAnhDangXuat);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("");
        alert.getDialogPane().setGraphic(imageView);
        alert.setHeight(340);
        Optional<ButtonType> dangXuat = alert.showAndWait();
        if (dangXuat.isPresent() && dangXuat.get() == ButtonType.OK) {
            Scene loginn = LoadLogin.loadLoginScreen(stage);
            stage.setScene(loginn);
            stage.show();
        }
    }
}
