package huce.cntt.oop.doan.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Optional;

import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.VaiTro;
import huce.cntt.oop.doan.entities.properties.DiaChi;
import huce.cntt.oop.doan.entities.properties.HoTen;
import huce.cntt.oop.doan.interfaces.IKhoaService;
import huce.cntt.oop.doan.interfaces.ILopService;
import huce.cntt.oop.doan.interfaces.ISinhVienService;
import huce.cntt.oop.doan.loader.LoadSinhVien;
import huce.cntt.oop.doan.loader.LoadTrangChu;
import huce.cntt.oop.doan.service.KhoaService;
import huce.cntt.oop.doan.service.LopService;
import huce.cntt.oop.doan.service.SinhVienService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ThemSinhVienController {

    private final ISinhVienService sinhVienService = SinhVienService.getInstance();
    private final ILopService lopService = LopService.getInstance();
    private final IKhoaService khoaService = KhoaService.getInstance();
    private Stage stage;
    private GiangVien giangVien;

    public ThemSinhVienController(Stage stage, GiangVien giangVien) {
        this.stage = stage;
        this.giangVien = giangVien;
    }

    @FXML
    private RadioButton nam;
    @FXML
    private RadioButton nu;

    @FXML
    private Button nutLuu;
    @FXML
    private Button nutQuayLai;

    @FXML
    private TextField hoTenTextField;
    @FXML
    private TextField queQuanTextField;
    @FXML
    private TextField diaChiHienTaiTextField;
    @FXML
    private TextField soDienThoaiTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private DatePicker ngaySinhDatePicker;
    @FXML
    private DatePicker ngayVaoTruongDatePicker;
    @FXML
    private ComboBox<String> lopQuanLiComboBox;
    @FXML
    private ComboBox<String> khoaComboBox;

    private Alert alert;

    @FXML
    private void initialize() {
        alert = new Alert(AlertType.INFORMATION);
        ToggleGroup namHayNu = new ToggleGroup();
        nam.setToggleGroup(namHayNu);
        nu.setToggleGroup(namHayNu);

        hoTenTextField.setPromptText("ít nhất 3 từ...");
        queQuanTextField.setPromptText("xã ..., huyện..., tỉnh ...");
        diaChiHienTaiTextField.setPromptText("số ..., ngõ ..., đường ..., quận ...");
        soDienThoaiTextField.setPromptText("10 chữ số");

        ngaySinhDatePicker.setPromptText("dd/MM/yyyy");
        ngayVaoTruongDatePicker.setPromptText("dd/MM/yyyy");

        formatDate(ngaySinhDatePicker, "dd/MM/yyyy", "dd/MM/yy");
        formatDate(ngayVaoTruongDatePicker, "dd/MM/yyyy", "dd/MM/yy");

        emailTextField.setPromptText("...");

        List<String> tenCacKhoa = khoaService.layTenTatCaKhoa();
        ObservableList<String> O_khoa = FXCollections.observableArrayList(tenCacKhoa);
        khoaComboBox.setItems(O_khoa);

        khoaComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldV, tenKhoa) -> {
            List<String> tenCacLop = lopService.layTenCacLopQuanLiTheoKhoa(tenKhoa);
            ObservableList<String> O_lopQuanLi = FXCollections.observableArrayList(tenCacLop);
            lopQuanLiComboBox.setItems(O_lopQuanLi);
        });

        nutLuu.setOnAction(e -> {
            if (!nutLuu.isPressed()) {
                themSinhVien();
            }
        });

        nutQuayLai.setOnAction(e -> {
            if (!nutQuayLai.isPressed()) {
                if (coThayDoiChuaLuu()) {
                    canhBaoChuaLuu();
                } else {
                    quayLaiHome();
                }
            }
        });
        stage.setOnCloseRequest(e -> {
            if (coThayDoiChuaLuu()) {
                canhBaoChuaLuu();
            } else {
                quayLaiHome();
            }
        });
    }

    void themSinhVien() {
        alert.setAlertType(AlertType.ERROR);
        try {
            SinhVien sinhVien = kiemTraDuLieu();            
            int mssv = sinhVienService.themMoiSinhVien(sinhVien);
            sinhVien.setMaSo(mssv);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Thêm sinh viên thành công!\nMã số sinh viên mới là " + mssv);
            alert.show();
        } catch (NullPointerException e) {
            alert.setContentText(e.getMessage()); 
            alert.show();
        } catch (IllegalArgumentException e) {
            alert.setContentText("Lỗi thông tin.\n" + e.getMessage());
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void canhBaoChuaLuu(){
        alert.setAlertType(AlertType.CONFIRMATION);
        alert.setContentText("Bạn có thay đổi chưa lưu. Tiếp tục thoát?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
            quayLaiHome();
        }
    }

    private boolean coThayDoiChuaLuu() {
        HoTen hoTen = new HoTen(hoTenTextField.getText());
        DiaChi queQuan = new DiaChi(queQuanTextField.getText());
        DiaChi diaChiHienTai = new DiaChi(diaChiHienTaiTextField.getText());
        String soDienThoai = soDienThoaiTextField.getText();
        String email = emailTextField.getText();
        return 
            (hoTen != null || !hoTen.toString().isBlank()) ||
            (queQuan != null || !queQuan.toString().isBlank()) ||
            (diaChiHienTai != null || !diaChiHienTai.toString().isBlank()) || 
            (soDienThoai != null || !soDienThoai.isBlank()) || 
            (email != null || !email.isBlank());
    }

    private SinhVien kiemTraDuLieu() throws Exception {
        HoTen hoTen = new HoTen(hoTenTextField.getText());
        DiaChi queQuan = new DiaChi(queQuanTextField.getText());
        DiaChi diaChiHienTai = new DiaChi(diaChiHienTaiTextField.getText());
        String soDienThoai = soDienThoaiTextField.getText();
        String email = emailTextField.getText();
        
        LocalDate ngaySinh = ngaySinhDatePicker.getValue();
        LocalDate ngayVaoTruong = ngayVaoTruongDatePicker.getValue();

        if (hoTen == null || hoTen.toString().isBlank() ||
            queQuan == null || queQuan.toString().isBlank() ||
            diaChiHienTai == null || diaChiHienTai.toString().isBlank() || 
            soDienThoai == null || soDienThoai.isBlank() || 
            email == null || email.isBlank()) {
            throw new NullPointerException("Bạn đang điền thiếu 1 trường nào đó!\nHãy kiểm tra lại");
        }
        if (ngaySinh == null || ngayVaoTruong == null) {
            throw new IllegalArgumentException("Format ngày không thành công!");
        }

        Boolean gioiTinh = nam.isSelected();
        String tenLopQuanLi = lopQuanLiComboBox.getValue();
        String tenKhoa = khoaComboBox.getValue();

        SinhVien sinhVien = new SinhVien();
        sinhVien.setMaSo(null);
        sinhVien.setHoTen(hoTen);
        sinhVien.setGioiTinh(gioiTinh);
        sinhVien.setNgaySinh(ngaySinh);
        sinhVien.setQueQuan(queQuan);
        sinhVien.setDiaChiThuongTru(diaChiHienTai);
        sinhVien.setSoDienThoai(soDienThoai);
        sinhVien.setEmail(email);
        sinhVien.setTenLopQuanLi(tenLopQuanLi);
        sinhVien.setNgayVaoTruong(ngayVaoTruong);
        sinhVien.setKhoa(tenKhoa);
        Integer maLop = lopService.checkKhoa(tenLopQuanLi, tenKhoa);
        if (maLop == null) {
            throw new IllegalArgumentException("Có lỗi ở khoa và lớp quản lí!");
        }
        sinhVien.setMaLopQuanLi(maLop);
        return sinhVien;
    }

    private void formatDate(DatePicker datePicker, String shortFormat, String longFormat) {
        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                .appendPattern(shortFormat)
                .optionalStart()
                .appendPattern(longFormat)
                .toFormatter();

        datePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? dateFormatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty()) ? LocalDate.parse(string, dateFormatter) : null;
            }
        });
    }

    private void quayLaiHome() {
        Scene xemSV = LoadSinhVien.loadSinhVien(stage, giangVien);
        stage.setScene(xemSV);
        stage.show();
    }
}
