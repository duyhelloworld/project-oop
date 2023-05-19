package huce.cntt.oop.doan.controller;

import java.time.LocalDate;
import java.time.format.FormatStyle;
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
import javafx.util.converter.LocalDateStringConverter;

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
    private DatePicker ngayVaoTruongDatePicker;
    @FXML
    private ChoiceBox<String> lopQuanLiChoiceBox;

    private Alert alert;

    @FXML
    private Button nutLuu;
    @FXML
    private Button nutQuayLai;

    private SinhVien giaTriLuuTamSinhVien;

    @FXML
    public void initialize() {

        // Show
        showBangSinhVien();

        // Set Bool
        nam.setToggleGroup(gioiTinhToggle);
        nu.setToggleGroup(gioiTinhToggle);

        SinhVien sinhVienMoi = laySinhVienCapNhat();
        nutLuu.setOnAction(e -> luu(sinhVienMoi));
        nutQuayLai.setOnAction(e -> {
            if (nutQuayLai.isPressed()) {
                System.out.println("pressed");
            } 
            else {
                if (giaTriLuuTamSinhVien.equals(sinhVienMoi)) {
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
                        giaTriLuuTamSinhVien = sv;
                        renderSinhVienKhiClick(sv);
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

    private void renderSinhVienKhiClick(SinhVien sv) {
        hoTenTextField.setPromptText(sv.getHoTen().toString());
        ngaySinhDatePicker.setPromptText(sv.getNgaySinh().toString());
        queQuanTextField.setPromptText(sv.getQueQuan().toString());
        diaChiThuongTruTextField.setPromptText(sv.getDiaChiThuongTru().toString());
        emailTextField.setPromptText(sv.getEmail());
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

    private SinhVien laySinhVienCapNhat() {
        SinhVien sv = new SinhVien();
        try {
            String diaChiThgTru = diaChiThuongTruTextField.getText(),
                    queQuan = queQuanTextField.getText(),
                    hoTen = hoTenTextField.getText(),
                    email = emailTextField.getText();
            LocalDate ngaySinh = ngaySinhDatePicker.getValue(),
                    ngayVaoTruong = ngayVaoTruongDatePicker.getValue();

            if (diaChiThgTru == null) {
                diaChiThgTru = diaChiThuongTruTextField.getPromptText();
            }
            if (queQuan == null) {
                queQuan = queQuanTextField.getPromptText();
            }
            if (email == null) {
                email = emailTextField.getPromptText();
            }
            if (hoTen == null) {
                hoTen = hoTenTextField.getPromptText();
            }
            if (!nam.isSelected() && !nu.isSelected()) {
                throw new IllegalAccessException("Hãy chọn giới tính trước khi lưu");
            }

            LocalDateStringConverter converter = new LocalDateStringConverter(FormatStyle.LONG);
            if (ngaySinh == null) {
                ngaySinh = converter.fromString(ngaySinhDatePicker.getPromptText());
            }
            if (ngayVaoTruong == null) {
                ngayVaoTruong = converter.fromString(ngayVaoTruongDatePicker.getPromptText());
            }

            sv.setHoTen(new HoTen(hoTen));
            sv.setGioiTinh(nam.isSelected());
            sv.setNgaySinh(ngaySinh);
            sv.setQueQuan(new DiaChi(queQuan));
            sv.setDiaChiThuongTru(new DiaChi(diaChiThgTru));
            sv.setEmail(emailTextField.getText());
            sv.setNgayVaoTruong(ngayVaoTruong);
            sv.setKhoa(khoaComboBox.getValue());
            System.out.println(sv.toString());
        } catch (Exception e) {
            alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        return sv;
    }

    private void luu(SinhVien sv) {
        alert.setAlertType(AlertType.INFORMATION);
        alert.setHeight(700);
        alert.setContentText(sv.toString());
        alert.show();
    }
}
