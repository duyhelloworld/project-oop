package huce.cntt.oop.doan.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Optional;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class XemSuaXoaSinhVienController {

    private final ISinhVienService sinhVienService;
    private final ILopService lopService;
    private final IKhoaService khoaService;

    public XemSuaXoaSinhVienController(ISinhVienService sinhVienService) {
        this.sinhVienService = sinhVienService;
        this.lopService = new LopService();
        this.khoaService = new KhoaService();
    }

    @FXML
    private TableView<SinhVien> bangSinhVien;

    @FXML
    private TableColumn<SinhVien, Integer> cotMaSoSV;
    @FXML
    private TableColumn<SinhVien, String> cotHoTen;
    @FXML
    private TableColumn<SinhVien, LocalDate> cotNgaySinh;
    @FXML
    private TableColumn<SinhVien, Boolean> cotGioiTinh;
    @FXML
    private TableColumn<SinhVien, String> cotQueQuan;
    @FXML
    private TableColumn<SinhVien, String> cotDiaChiThuongTru;
    @FXML
    private TableColumn<SinhVien, String> cotKhoa;
    @FXML
    private TableColumn<SinhVien, String> cotEmail;
    @FXML
    private TableColumn<SinhVien, String> cotSoDienThoai;
    @FXML
    private TableColumn<SinhVien, Integer> cotNienKhoa;
    @FXML
    private TableColumn<SinhVien, String> cotLopQuanLi;
    @FXML
    private TableColumn<SinhVien, CheckBox> cotXoa;

    @FXML
    private TextField hoTenTextField;

    private ToggleGroup gioiTinhToggle = new ToggleGroup();
    @FXML
    private RadioButton nam;
    @FXML
    private RadioButton nu;

    @FXML
    private DatePicker ngaySinhDatePicker;
    @FXML
    private TextField queQuanTextField;
    @FXML
    private TextField diaChiThuongTruTextField;
    @FXML
    private ComboBox<String> khoaComboBox;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField soDienThoaiTextField;
    @FXML
    private DatePicker ngayVaoTruongDatePicker;
    @FXML
    private ChoiceBox<String> lopQuanLiChoiceBox;

    private Alert alert;

    private final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
            .toFormatter();

    @FXML
    private Button nutLuu;
    @FXML
    private Button nutQuayLai;

    private SinhVien sinhVienTamThoi;

    @FXML
    public void initialize() {

        // Show
        showBangSinhVien();

        // Set Bool
        nam.setToggleGroup(gioiTinhToggle);
        nu.setToggleGroup(gioiTinhToggle);

        SinhVien sinhVienMoi = layGiaTriPromptHoacText();

        nutLuu.setOnAction(e -> luu(sinhVienMoi));

        nutQuayLai.setOnAction(e -> {
            if (nutQuayLai.isPressed()) {
                System.out.println("pressed");
            } 
            else {
                if (sinhVienTamThoi.equals(sinhVienMoi)) {
                    // back !
                } else {
                    alert.setAlertType(AlertType.CONFIRMATION);
                    alert.setContentText("Bạn có thay đổi chưa lưu.\nLưu ?");
                    Optional<ButtonType> confirm = alert.showAndWait();
                    if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                        luu(sinhVienMoi);
                    }
                }
            }
        });
    }

    private void showBangSinhVien() {
        cotMaSoSV.setCellValueFactory(new PropertyValueFactory<SinhVien, Integer>("maSo"));
        cotHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        cotNgaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        cotGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        cotQueQuan.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
        cotDiaChiThuongTru.setCellValueFactory(new PropertyValueFactory<>("diaChiThuongTru"));
        cotKhoa.setCellValueFactory(new PropertyValueFactory<>("khoa"));
        cotEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cotSoDienThoai.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        cotNienKhoa.setCellValueFactory(new PropertyValueFactory<>("nienKhoa"));
        cotLopQuanLi.setCellValueFactory(new PropertyValueFactory<>("tenLopQuanLi"));
        cotMaSoSV.setEditable(false);

        bangSinhVien.setItems(loadDanhSachSinhVien());

        bangSinhVien.setRowFactory(tableView -> {
            TableRow<SinhVien> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 1 && !row.isEmpty()) {
                    alert = new Alert(AlertType.CONFIRMATION);
                    alert.setContentText("Bạn có muốn thay đổi sinh viên này?");
                    Optional<ButtonType> confirm = alert.showAndWait();
                    if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                        SinhVien sv = row.getItem();
                        sinhVienTamThoi = sv;
                        renderSinhVienKhiClick(sinhVienTamThoi);
                    }
                }
            });
            return row;
        });
    }

    private ObservableList<SinhVien> loadDanhSachSinhVien() {
        ObservableList<SinhVien> danhSachSinhVien = FXCollections.observableList(sinhVienService.layTatCaSinhVien());
        return danhSachSinhVien;
    }

    private SinhVien layGiaTriPromptHoacText(){
        HoTen hoTen = new HoTen(
            hoTenTextField.getText().isEmpty() ? hoTenTextField.getPromptText() : hoTenTextField.getText()
        );
        DiaChi queQuan = new DiaChi(
            queQuanTextField.getText().isEmpty() ? queQuanTextField.getPromptText() : queQuanTextField.getText()
        );
        DiaChi diaChiHienTai = new DiaChi(
            diaChiThuongTruTextField.getText().isEmpty() ? diaChiThuongTruTextField.getPromptText() : diaChiThuongTruTextField.getText()
        );
        LocalDate ngaySinh = ngaySinhDatePicker.getValue();
        // if (ngaySinh == null) {
        //     ngaySinh = LocalDate.parse(ngaySinhDatePicker.getPromptText(), formatter);
        // }
        String soDienThoai = soDienThoaiTextField.getText().isEmpty() ? soDienThoaiTextField.getPromptText() : soDienThoaiTextField.getText();
        String email = emailTextField.getText().isEmpty() ? emailTextField.getPromptText() : emailTextField.getText();

        LocalDate ngayVaoTruong = ngayVaoTruongDatePicker.getValue();
        // if (ngayVaoTruong == null) {
        //     ngayVaoTruong = LocalDate.parse(ngayVaoTruongDatePicker.getPromptText(), formatter);
        // }

        Boolean gioiTinh = nam.isSelected();
        String lopQuanLi = lopQuanLiChoiceBox.getValue();
        if (lopQuanLi == null) {
            lopQuanLi = lopQuanLiChoiceBox.getSelectionModel().getSelectedItem();
        }
        String khoa = khoaComboBox.getValue();
        if (khoa == null) {
            khoa = khoaComboBox.getSelectionModel().getSelectedItem();
        }
        
        SinhVien sinhVien = new SinhVien();
        try {
            sinhVien.setHoTen(hoTen);
            sinhVien.setGioiTinh(gioiTinh);
            sinhVien.setQueQuan(queQuan);  
            sinhVien.setDiaChiThuongTru(diaChiHienTai);          
            sinhVien.setSoDienThoai(soDienThoai);
            sinhVien.setEmail(email);
            sinhVien.setNgaySinh(ngaySinh);
            sinhVien.setNgayVaoTruong(ngayVaoTruong);
            sinhVien.setTenLopQuanLi(lopQuanLi);
        } catch (Exception e) {
            alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        return sinhVien;
    }

    private void renderSinhVienKhiClick(SinhVien sv) {
        hoTenTextField.setPromptText(sv.getHoTen().toString());
        ngaySinhDatePicker.setPromptText(sv.getNgaySinh().toString());
        queQuanTextField.setPromptText(sv.getQueQuan().toString());
        diaChiThuongTruTextField.setPromptText(sv.getDiaChiThuongTru().toString());
        emailTextField.setPromptText(sv.getEmail());
        soDienThoaiTextField.setPromptText(sv.getSoDienThoai());
        khoaComboBox.setPromptText(sv.getKhoa());
        lopQuanLiChoiceBox.setValue(sv.getTenLopQuanLi());
        ngayVaoTruongDatePicker.setPromptText(sv.getNgayVaoTruong().toString());
        // Giới tính ko auto-render đc

        List<String> tenCacKhoa = khoaService.layTenTatCaKhoa();
        ObservableList<String> O_khoa = FXCollections.observableArrayList(tenCacKhoa);
        khoaComboBox.setItems(O_khoa);

        khoaComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldV, tenKhoa) -> {
            List<String> tenCacLop = lopService.layTenCacLopQuanLiTheoKhoa(tenKhoa);
            ObservableList<String> O_lopQuanLi = FXCollections.observableArrayList(tenCacLop);
            lopQuanLiChoiceBox.setItems(O_lopQuanLi);
        });
    }

    private void luu(SinhVien sv) {
        alert.setAlertType(AlertType.INFORMATION);
        alert.setHeight(700);
        alert.setContentText(sv.toString());
        alert.show();
    }
}
