package huce.cntt.oop.doan.controller;

import java.time.LocalDate;
import java.util.List;

import huce.cntt.oop.doan.entities.dto.DTOSinhVien;
import huce.cntt.oop.doan.entities.properties.DiaChi;
import huce.cntt.oop.doan.entities.properties.HoTen;
import huce.cntt.oop.doan.interfaces.IKhoaService;
import huce.cntt.oop.doan.interfaces.ILopService;
import huce.cntt.oop.doan.interfaces.ISinhVienService;
import huce.cntt.oop.doan.service.KhoaService;
import huce.cntt.oop.doan.service.LopService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ThemSinhVienController {

    private final ISinhVienService sinhVienService;
    private final ILopService lopService;
    private final IKhoaService khoaService;

    public ThemSinhVienController(ISinhVienService sinhVienService) {
        this.sinhVienService = sinhVienService;
        this.lopService = new LopService();
        this.khoaService = new KhoaService();
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
        alert = new Alert(AlertType.NONE);
        ToggleGroup namHayNu = new ToggleGroup();
        nam.setToggleGroup(namHayNu);
        nu.setToggleGroup(namHayNu);

        hoTenTextField.setPromptText("...");
        queQuanTextField.setPromptText("xã ..., huyện..., tỉnh ...");
        diaChiHienTaiTextField.setPromptText("số ..., ngõ ..., đường ..., quận");
        soDienThoaiTextField.setPromptText("10 chữ số");
        ngaySinhDatePicker.setPromptText("Chọn ...");
        ngayVaoTruongDatePicker.setPromptText("Chọn ...");
        emailTextField.setPromptText("Đã hỗ trợ tự điền domain '@huce.edu.vn'");

        List<String> tenCacKhoa = khoaService.layTenTatCaKhoa();
        ObservableList<String> O_khoa = FXCollections.observableArrayList(tenCacKhoa);
        khoaComboBox.setItems(O_khoa);

        khoaComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldV, tenKhoa) -> {
            List<String> tenCacLop = lopService.layTenCacLopQuanLiTheoKhoa(tenKhoa);
            ObservableList<String> O_lopQuanLi = FXCollections.observableArrayList(tenCacLop);
            lopQuanLiComboBox.setItems(O_lopQuanLi);
        });

        // Cấm xoá lớp quản lí : để mã lớp quản lí trong db luôn trích ra là chỉ số ở
        // đây
        // --> hàm get của tôi đỡ phải SELECT 2 trường
        nutLuu.setOnAction(e -> themSinhVien());
    }

    // Lấy dữ liệu
    void themSinhVien() {
        DTOSinhVien sinhVien = kiemTraDuLieu();
        int maLopQuanLi = khoaComboBox.getSelectionModel().getSelectedIndex();
        try {
            int mssv = sinhVienService.themMoiSinhVien(sinhVien);
            sinhVien.setMaSo(mssv);
            lopService.themSinhVienVaoLopQuanLi(mssv, maLopQuanLi);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Thêm sinh viên thành công!\nMã số sinh viên mới là " + mssv);
        } catch (Exception e) {
            alert.setAlertType(AlertType.WARNING);
            alert.setContentText(e.getMessage());
        }
        alert.show();
    }

    // Tab1
    private DTOSinhVien kiemTraDuLieu() {
        HoTen hoTen = new HoTen(hoTenTextField.getText());
        DiaChi queQuan = new DiaChi(queQuanTextField.getText());
        DiaChi diaChiHienTai = new DiaChi(diaChiHienTaiTextField.getText());
        LocalDate ngaySinh = ngaySinhDatePicker.getValue();
        String soDienThoai = soDienThoaiTextField.getText();
        String email = emailTextField.getText();

        LocalDate ngayVaoTruong = ngayVaoTruongDatePicker.getValue();

        Boolean gioiTinh = nam.isSelected();
        String lopQuanLi = lopQuanLiComboBox.getValue();
        String khoa = khoaComboBox.getValue();

        DTOSinhVien dtoSinhVien = new DTOSinhVien();
        try {
            dtoSinhVien.setMaSo(null);
            dtoSinhVien.setHoTen(hoTen);
            dtoSinhVien.setGioiTinh(gioiTinh);
            dtoSinhVien.setNgaySinh(ngaySinh);
            dtoSinhVien.setQueQuan(queQuan);
            dtoSinhVien.setDiaChiThuongTru(diaChiHienTai);
            dtoSinhVien.setSoDienThoai(soDienThoai);
            dtoSinhVien.setEmail(email);
            dtoSinhVien.setTenLopQuanLi(lopQuanLi);
            dtoSinhVien.setNgayVaoTruong(ngayVaoTruong);
            dtoSinhVien.setKhoa(khoa);
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
        }
        return dtoSinhVien;
    }
}
