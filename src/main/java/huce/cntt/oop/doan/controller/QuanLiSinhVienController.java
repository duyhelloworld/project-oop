package huce.cntt.oop.doan.controller;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class QuanLiSinhVienController {

    private final ISinhVienService sinhVienService;
    private final ILopService lopService;
    private final IKhoaService khoaService;

    public QuanLiSinhVienController(ISinhVienService sinhVienService) {
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

    @FXML
    private void initialize() {
        System.out.println("Loaded " + this.getClass().getName());
        ToggleGroup namHayNu = new ToggleGroup();
        nam.setToggleGroup(namHayNu);
        nu.setToggleGroup(namHayNu);

        hoTenTextField.setText("Pham Hoang Nam");
        queQuanTextField.setText("1, 1, Ha Nam, Ha Nam");
        diaChiHienTaiTextField.setText("2, 4, Ha Dong");
        soDienThoaiTextField.setText("0123232318");
        ngaySinhDatePicker.setPromptText("ngay sinh");
        ngayVaoTruongDatePicker.setPromptText("ngay vao truong");
        emailTextField.setText("nam1233");

        List<String> tenCacKhoa = khoaService.layTenTatCaKhoa();
        ObservableList<String> O_khoa = FXCollections.observableArrayList(tenCacKhoa);
        khoaComboBox.setItems(O_khoa);

        khoaComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldV, tenKhoa) -> {
            List<String> tenCacLop = lopService.layTenCacLopQuanLiTheoKhoa(tenKhoa);
            ObservableList<String> O_lopQuanLi = FXCollections.observableArrayList(tenCacLop);
            lopQuanLiComboBox.setItems(O_lopQuanLi);
        });

        // Cấm xoá lớp quản lí : để mã lớp quản lí trong db luôn trích ra là chỉ số ở đây
        // --> hàm get của tôi đỡ phải SELECT 2 trường 
        nutLuu.setOnAction(e -> themSinhVien());
    }

    // Tab1
    void themSinhVien() {
        SinhVien sinhVien = kiemTraDuLieu();
        int maLopQuanLi = khoaComboBox.getSelectionModel().getSelectedIndex();
        int mssv = sinhVienService.themMoiSinhVien(sinhVien);
        System.out.println("Da Them vao 'sinhvien' voi mssv = " + mssv);
        sinhVien.setMaSo(mssv);
        lopService.themSinhVienVaoLopQuanLi(mssv, maLopQuanLi);
        System.out.println("Da Them vao 'lopquanli'");
    }

    // Tab1
    private SinhVien kiemTraDuLieu() {
        HoTen hoTen = new HoTen(hoTenTextField.getText());
        DiaChi queQuan = new DiaChi(queQuanTextField.getText());
        DiaChi diaChiHienTai = new DiaChi(diaChiHienTaiTextField.getText());
        LocalDate ngaySinh = ngaySinhDatePicker.getValue();
        String soDienThoai = soDienThoaiTextField.getText();
        String email = emailTextField.getText();

        LocalDate ngayVaoTruong = ngayVaoTruongDatePicker.getValue();

        Boolean gioiTinh = nam.isSelected();
        if (!gioiTinh) {
            gioiTinh = nu.isSelected();
            if (!gioiTinh) {
                try {
                    throw new IllegalArgumentException("GioiTinh is not picked");
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        String lopQuanLi = lopQuanLiComboBox.getValue();

        SinhVien sinhVien = new SinhVien();
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sinhVien;
    }
}
