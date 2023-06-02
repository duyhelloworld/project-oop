package huce.cntt.oop.doan.controller;

import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.entities.VaiTro;
import huce.cntt.oop.doan.loader.LoadDiemCaNhan;
import huce.cntt.oop.doan.loader.LoadDiemHocPhan;
import huce.cntt.oop.doan.loader.LoadMonHoc;
import huce.cntt.oop.doan.loader.LoadSinhVien;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private Button dangXuat;

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
    }

    private void loadSinhVien() {
        if (nutSinhVien.isPressed()) {
            return;
        }
        Scene sinhVien = LoadSinhVien.loadSinhVien(stage);
        stage.setScene(sinhVien);
        stage.show();
    }

    private void loadDiemCaNhan() {
        if (nutDiemCaNhan.isPressed()) {
            return;
        }
        Scene diemCaNhan = LoadDiemCaNhan.loadDiemCaNhan(stage);
        stage.setScene(diemCaNhan);
        stage.show();
    }

    private void loadMonHoc() {
        if (nutMonHoc.isPressed()) {
            return;
        }
        Scene monHoc = LoadMonHoc.loadMonHoc();
        stage.setScene(monHoc);
        stage.show();
    }

    private void loadDiemHocPhan() {
        if (nutLopHocPhan.isPressed()) {
            return;
        }
        Scene lopHocPhan = LoadDiemHocPhan.loadDiemLopHP();
        stage.setScene(lopHocPhan);
        stage.show();
    }

}
