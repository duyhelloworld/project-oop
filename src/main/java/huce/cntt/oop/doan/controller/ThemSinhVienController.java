package huce.cntt.oop.doan.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.exception.ChuyenDiaChiException;
import huce.cntt.oop.doan.entities.exception.ChuyenHoTenException;
import huce.cntt.oop.doan.entities.exception.ChuyenSoException;
import huce.cntt.oop.doan.entities.exception.EmailException;
import huce.cntt.oop.doan.entities.exception.KhoaLopException;
import huce.cntt.oop.doan.entities.exception.NgayGioException;
import huce.cntt.oop.doan.entities.exception.ThieuGiaTriException;
import huce.cntt.oop.doan.interfaces.IKhoaService;
import huce.cntt.oop.doan.interfaces.ILopService;
import huce.cntt.oop.doan.interfaces.ISinhVienService;
import huce.cntt.oop.doan.loader.LoadSinhVien;
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

public class ThemSinhVienController {

    private final ISinhVienService sinhVienService = SinhVienService.getInstance();
    private final ILopService lopService = LopService.getInstance();
    private final IKhoaService khoaService = KhoaService.getInstance();
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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

        hoTenTextField.setPromptText("...   ....   ...");
        queQuanTextField.setPromptText("xã ..., huyện ..., tỉnh ...");
        diaChiHienTaiTextField.setPromptText("số ..., ngõ ..., đường ..., quận ...");
        soDienThoaiTextField.setPromptText("XXX XXX XXXX");
        ngaySinhDatePicker.setPromptText("dd/MM/yyyy");
        ngayVaoTruongDatePicker.setPromptText("dd/MM/yyyy");

        formatDate(ngaySinhDatePicker);
        formatDate(ngayVaoTruongDatePicker);

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
        try {
            SinhVien sinhVien = kiemTraDuLieu();            
            int mssv = sinhVienService.themMoiSinhVien(sinhVien);
            sinhVien.setMaSo(mssv);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Thêm sinh viên thành công!\nMã số sinh viên mới là " + mssv);
            alert.show();
        } catch (NgayGioException | 
                ChuyenDiaChiException | 
                ChuyenHoTenException | 
                ChuyenSoException | 
                EmailException | 
                ThieuGiaTriException | 
                KhoaLopException e) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText(e.getMessage()); 
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
        String hoTen = hoTenTextField.getText();
        String queQuan = queQuanTextField.getText();
        String diaChiHienTai = diaChiHienTaiTextField.getText();
        String soDienThoai = soDienThoaiTextField.getText();
        String email = emailTextField.getText();
        return 
            (hoTen != null && !hoTen.isBlank()) ||
            (queQuan != null && !queQuan.isBlank()) ||
            (diaChiHienTai != null && !diaChiHienTai.isBlank()) || 
            (soDienThoai != null && !soDienThoai.isBlank()) || 
            (email != null && !email.isBlank());
    }

    private SinhVien kiemTraDuLieu() throws NgayGioException, ChuyenHoTenException,
     ChuyenDiaChiException, ChuyenSoException, EmailException, ThieuGiaTriException, KhoaLopException {
        String hoTen = hoTenTextField.getText();
        String queQuan = queQuanTextField.getText();
        String diaChiHienTai = diaChiHienTaiTextField.getText();
        String soDienThoai = soDienThoaiTextField.getText();
        String email = emailTextField.getText();
        LocalDate ngaySinh = ngaySinhDatePicker.getValue();
        LocalDate ngayVaoTruong = ngayVaoTruongDatePicker.getValue();
        Boolean gioiTinh = nam.isSelected();
        String tenLopQuanLi = lopQuanLiComboBox.getValue();
        String tenKhoa = khoaComboBox.getValue();

        if (hoTen == null || hoTen.toString().isBlank()) {
            throw new ThieuGiaTriException("họ tên");
        }
        if (ngaySinh == null) {
            throw new ThieuGiaTriException("Ngày sinh");
        }
        if (queQuan == null || queQuan.toString().isBlank()) {
            throw new ThieuGiaTriException("quê quán");
        }
        if (diaChiHienTai == null || diaChiHienTai.toString().isBlank()) {
            throw new ThieuGiaTriException("địa chỉ thường trú");
        }
        if (soDienThoai == null || soDienThoai.isBlank()) {
            throw new ThieuGiaTriException("số điện thoại");
        }  
        if (email == null || email.isBlank()) {
            throw new ThieuGiaTriException("email");
        }
        if (tenLopQuanLi == null || tenLopQuanLi.isBlank()) {
            throw new ThieuGiaTriException("lớp quản lí");
        }
        if (tenKhoa == null || tenKhoa.isBlank()) {
            throw new ThieuGiaTriException("khoa");
        }
        if (ngayVaoTruong == null) {
            throw new ThieuGiaTriException("ngày vào trường");
        }

        SinhVien sinhVien = new SinhVien();
        sinhVien.setHoTen(hoTen);
        sinhVien.setGioiTinh(gioiTinh);
        sinhVien.setNgaySinh(ngaySinh);
        sinhVien.setQueQuan(queQuan.toString());
        sinhVien.setDiaChiThuongTru(diaChiHienTai.toString());
        sinhVien.setSoDienThoai(soDienThoai);
        sinhVien.setEmail(email);
        sinhVien.setTenLopQuanLi(tenLopQuanLi);
        sinhVien.setNgayVaoTruong(ngayVaoTruong);
        sinhVien.setKhoa(tenKhoa);

        Integer maLop = lopService.checkKhoa(tenLopQuanLi, tenKhoa);
        if (maLop == null) {
            throw new KhoaLopException("Có lỗi ở khoa và lớp quản lí!");
        }
        sinhVien.setMaLopQuanLi(maLop);
        return sinhVien;
    }

    private void formatDate(DatePicker datePicker) {
        datePicker.getEditor().textProperty().addListener((obser, oldValue, newValue) -> {
            if (!newValue.isBlank()) {
                try {
                    LocalDate date = LocalDate.parse(newValue, dateFormatter);
                    datePicker.setValue(date);
                } catch (Exception e) {
                    datePicker.setValue(null);
                    alert.setAlertType(AlertType.ERROR);
                    alert.setContentText("Không thể chuyển đổi giá trị '" + newValue + "' thành ngày/tháng/năm");
                    alert.show();
                }
            }
        });
    }

    private void quayLaiHome() {
        Scene xemSV = LoadSinhVien.loadSinhVien(stage, giangVien);
        stage.setScene(xemSV);
        stage.show();
    }
}
