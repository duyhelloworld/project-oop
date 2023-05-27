package huce.cntt.oop.doan.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.properties.DiaChi;
import huce.cntt.oop.doan.entities.properties.HoTen;
import huce.cntt.oop.doan.interfaces.IKhoaService;
import huce.cntt.oop.doan.interfaces.ILopService;
import huce.cntt.oop.doan.interfaces.ISinhVienService;
import huce.cntt.oop.doan.service.KhoaService;
import huce.cntt.oop.doan.service.LopService;
import huce.cntt.oop.doan.service.SinhVienService;
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

    public ThemSinhVienController() {
        this.sinhVienService = SinhVienService.getInstance();
        this.lopService = LopService.getInstance();
        this.khoaService = KhoaService.getInstance();
    }

    public ThemSinhVienController(ISinhVienService sinhVienService, ILopService lopService, IKhoaService khoaService ) {
        this.sinhVienService = sinhVienService;
        this.lopService = lopService;
        this.khoaService = khoaService;
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

        hoTenTextField.setPromptText("..............");
        queQuanTextField.setPromptText("xã ..., huyện..., tỉnh ...");
        diaChiHienTaiTextField.setPromptText("số ..., ngõ ..., đường ..., quận ...");
        soDienThoaiTextField.setPromptText("10 chữ số");
        ngaySinhDatePicker.setPromptText("...");
        ngayVaoTruongDatePicker.setPromptText("...");
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
        nutLuu.setOnAction(e -> {
            if (!nutLuu.isPressed()) {
                themSinhVien();
            }
        });
    }

    // Lấy dữ liệu
    void themSinhVien() {

        // đã try catch các lỗi
        try {
            SinhVien sinhVien = kiemTraDuLieu();
            if (sinhVien.hasNullElement()) {
                System.out.println(sinhVien);
                throw new NullPointerException();
            }
            int mssv = sinhVienService.themMoiSinhVien(sinhVien);
            sinhVien.setMaSo(mssv);
            lopService.themSinhVienVaoLopQuanLi(mssv, sinhVien.getMaLopQuanLi());
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Thêm sinh viên thành công!\nMã số sinh viên mới là " + mssv);
        } catch (NullPointerException e) {
            alert.setAlertType(AlertType.WARNING);
            alert.setContentText("Bạn đang điền thiếu 1 trường nào đó!\nHãy kiểm tra lại");
            e.printStackTrace();
        } catch (SQLException e) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Lỗi thông tin.\n" + e.getMessage());
            e.printStackTrace();
        }
        alert.show();
    }

    // Tab1
    private SinhVien kiemTraDuLieu() throws IllegalArgumentException {
        HoTen hoTen = new HoTen(hoTenTextField.getText());
        DiaChi queQuan = new DiaChi(queQuanTextField.getText());
        DiaChi diaChiHienTai = new DiaChi(diaChiHienTaiTextField.getText());
        String soDienThoai = soDienThoaiTextField.getText();
        String email = emailTextField.getText();
        
        LocalDate ngaySinh = ngaySinhDatePicker.getValue();
        LocalDate ngayVaoTruong = ngayVaoTruongDatePicker.getValue();

        Boolean gioiTinh = nam.isSelected();
        String lopQuanLi = lopQuanLiComboBox.getValue();
        String khoa = khoaComboBox.getValue();

        int maLopQuanLi = khoaComboBox.getSelectionModel().getSelectedIndex();

        SinhVien sinhVien = new SinhVien();
        sinhVien.setMaSo(null);
        sinhVien.setHoTen(hoTen);
        sinhVien.setGioiTinh(gioiTinh);
        sinhVien.setNgaySinh(ngaySinh);
        sinhVien.setQueQuan(queQuan);
        sinhVien.setDiaChiThuongTru(diaChiHienTai);
        sinhVien.setSoDienThoai(soDienThoai);
        sinhVien.setEmail(email);
        sinhVien.setTenLopQuanLi(lopQuanLi);
        sinhVien.setNgayVaoTruong(ngayVaoTruong);
        sinhVien.setKhoa(khoa);
        sinhVien.setMaLopQuanLi(maLopQuanLi);
        return sinhVien;
    }
}
