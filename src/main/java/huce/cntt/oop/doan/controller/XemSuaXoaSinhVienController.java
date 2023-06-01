package huce.cntt.oop.doan.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
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
import huce.cntt.oop.doan.service.SinhVienService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.input.KeyCode;

public class XemSuaXoaSinhVienController {

    private final ISinhVienService sinhVienService;
    private final ILopService lopService;
    private final IKhoaService khoaService;

    public XemSuaXoaSinhVienController() {
        this.sinhVienService = SinhVienService.getInstance();
        this.lopService = LopService.getInstance();
        this.khoaService = KhoaService.getInstance();
    }

    public XemSuaXoaSinhVienController(ISinhVienService sinhVienService, ILopService lopService,
            IKhoaService khoaService) {
        this.sinhVienService = sinhVienService;
        this.lopService = lopService;
        this.khoaService = khoaService;
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
    private TableColumn<SinhVien, Integer> cotMaLopQuanLi;
    @FXML
    private TableColumn<SinhVien, Integer> cotMaKhoa;

    @FXML
    private TextField maSoTextField;

    @FXML
    private TextField hoTenTextField;

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

    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<String> searchComboBox;
    @FXML
    private Button nutTimKiem;
    @FXML
    private Button nutXoa;

    private SinhVien sinhVien;
    private ToggleGroup gioiTinhToggle;
    private ObservableList<SinhVien> data;
    private Alert alert;
    private int soLuotTimKiem;

    private final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            .toFormatter();

    @FXML
    private Button nutLuu;
    @FXML
    private Button nutQuayLai;

    @FXML
    public void initialize() {
        alert = new Alert(AlertType.NONE);

        data = FXCollections.observableArrayList();
        data.addAll(loadDanhSachSinhVien());

        showSinhVienTableView();

        // Set Bool
        gioiTinhToggle = new ToggleGroup();
        nam.setToggleGroup(gioiTinhToggle);
        nu.setToggleGroup(gioiTinhToggle);

        searchComboBox.getItems().addAll("Tên sinh viên", "Mã số", "Email");

        nutLuu.setOnAction(e -> luu());
        nutQuayLai.setOnAction(e -> quayLai());
        nutTimKiem.setOnAction(e -> {
            if (!nutTimKiem.isPressed()) {
                timKiem();
            }
        });

        searchComboBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                timKiem();
            }
        });
        nutXoa.setOnAction(e -> xoa());
    }

    private void luu() {
        if (nutLuu.isPressed()) {
            return;
        }
        sinhVien = layGiaTriPromptHoacText();
        try {
            sinhVienService.capNhatThongTinSinhVien(sinhVien);
            lopService.capNhatLopQuanLi(sinhVien);
            data.clear();
            clearDataDaNhap();
            data.setAll(loadDanhSachSinhVien());
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Sinh Viên đã cập nhật : \n" + sinhVien.toString());
            alert.setHeight(500);
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText(e.getMessage());
        }
        alert.show();
    }

    private void timKiem() {
        String searchString = searchTextField.getText();
        soLuotTimKiem++;
        if (searchString.isBlank()) {
            if (soLuotTimKiem >= 3) {
                data.clear();
                data.setAll(loadDanhSachSinhVien());
                return;
            } else {
                alert.setAlertType(AlertType.WARNING);
                alert.setContentText("Ô tìm kiếm trống. Hãy nhập điểu gì đó");
                alert.show();
                return;
            }
        }

        alert.setAlertType(AlertType.ERROR);
        switch (searchComboBox.getSelectionModel().getSelectedItem()) {
            case "Tên sinh viên":
                data.clear();
                data.setAll(sinhVienService.timKiemSinhVienTheoTen(searchString));
                break;
            case "Mã số":
                try {
                    int mssv = Integer.parseInt(searchString);
                    int soSinhVienHienTai = sinhVienService.tongSoSinhVien();
                    if (mssv < 1 || mssv >= soSinhVienHienTai) {
                        alert.setContentText("Lỗi : vượt khoảng sinh viên đang học tại trường!\nHiện tại có " + soSinhVienHienTai + " sinh viên.");
                        alert.show();
                        return;
                    }
                    data.clear();
                    data.setAll(sinhVienService.timKiemSinhVienTheoMaSo(mssv));
                } catch (NumberFormatException e) {
                    alert.setContentText("Giá trị không đúng");
                    alert.show();
                    return;
                }
            case "Email":
                try {
                    SinhVien sv = new SinhVien();
                    sv.setEmail(searchString);
                    data.clear();
                    data.addAll(sinhVienService.timKiemSinhVienTheoEmail(searchString));
                } catch (Exception e) {
                    alert.setContentText(e.getMessage());
                    alert.show();
                    return;
                }
                return;
            default:
                return;
            }
            alert.close();
    }

    private void quayLai() {
        if (nutQuayLai.isPressed()) { 
            return;
        }
        sinhVien = layGiaTriPromptHoacText();
        SinhVien sinhVienMoi = bangSinhVien.getSelectionModel().getSelectedItem();
        if (sinhVienMoi.equals(sinhVien)) {
            return;
        }
        alert.setAlertType(AlertType.CONFIRMATION);
        alert.setContentText("Bạn có thay đổi chưa lưu.\nLưu ?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
            luu();
        }
    }

    private void xoa(){
        if (nutXoa.isPressed()) {
            return;
        }
        Optional<ButtonType> confirm;
        sinhVien = bangSinhVien.getSelectionModel().getSelectedItem();
        if (sinhVien == null) {
            return;
        }
        alert.setContentText("Bạn có muốn xoá sinh viên này?");
        confirm = alert.showAndWait();
        if (confirm.isPresent() && confirm.get() == ButtonType.CANCEL) {
            alert.close();
            return;
        }
        
        int mssv = sinhVien.getMaSo();
        try {
            int soLopMonDangTheoHoc = lopService.laySoLopMonHocDangHoc(mssv);
            if (soLopMonDangTheoHoc > 0) {
                // Sinh viên học nhiều hơn 1 môn thì warn ở diemsinhvien
                alert.setContentText("Sinh viên này đang theo học tại " + soLopMonDangTheoHoc + " lớp môn học.\nVẫn tiếp tục?");
                confirm = alert.showAndWait();
                if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                    lopService.xoaSinhVienKhoiLopMonHoc(mssv);
                }
            }
            // Sinh viên học 0 môn thì xoá ngay, ko warn
            lopService.xoaSinhVienKhoiLopQuanLi(mssv);
            boolean xoaThanhCong = sinhVienService.xoaSinhVienTheoMaSo(mssv);
            if (xoaThanhCong) {
                alert.setAlertType(AlertType.INFORMATION);
                alert.setContentText("Xoá thành công sinh viên mã số " + mssv);
                data.clear();
                data.setAll(loadDanhSachSinhVien());
                alert.show();
            }
        } catch (Exception e) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    private void showSinhVienTableView() {
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
        cotMaKhoa.setCellValueFactory(new PropertyValueFactory<>("maKhoa"));
        cotMaLopQuanLi.setCellValueFactory(new PropertyValueFactory<>("maLopQuanLi"));
        cotMaLopQuanLi.setVisible(false);
        cotMaKhoa.setVisible(false);
        bangSinhVien.setItems(data);

        bangSinhVien.setRowFactory(tableView -> {
            TableRow<SinhVien> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && !row.isEmpty()) {
                    Optional<ButtonType> confirm;
                    alert.setAlertType(AlertType.CONFIRMATION);
                    alert.setContentText("Bạn có muốn thay đổi sinh viên này?");
                    confirm = alert.showAndWait();
                    if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                        sinhVien = row.getItem();
                        renderSinhVienKhiClick(sinhVien);
                    }
                }
            });
            return row;
        });
    }

    private void clearDataDaNhap() {
        maSoTextField.clear();
        hoTenTextField.clear();
        hoTenTextField.setPromptText("");
        ngaySinhDatePicker.setPromptText("");
        queQuanTextField.clear();
        queQuanTextField.setPromptText("");
        diaChiThuongTruTextField.clear();
        diaChiThuongTruTextField.setPromptText("");
        emailTextField.clear();
        emailTextField.setPromptText("");
        soDienThoaiTextField.clear();
        soDienThoaiTextField.setPromptText("");
        ngayVaoTruongDatePicker.setPromptText("");
    }

    private SinhVien layGiaTriPromptHoacText() {
        Integer maSo = Integer.valueOf(maSoTextField.getText());

        HoTen hoTen = new HoTen(
                hoTenTextField.getText().isEmpty() ? hoTenTextField.getPromptText() : hoTenTextField.getText());
        DiaChi queQuan = new DiaChi(
                queQuanTextField.getText().isEmpty() ? queQuanTextField.getPromptText() : queQuanTextField.getText());
        DiaChi diaChiHienTai = new DiaChi(
                diaChiThuongTruTextField.getText().isEmpty() ? diaChiThuongTruTextField.getPromptText()
                        : diaChiThuongTruTextField.getText());
        LocalDate ngaySinh = ngaySinhDatePicker.getValue();
        if (ngaySinh == null && ngaySinhDatePicker.getPromptText() != null) {
            ngaySinh = LocalDate.parse(ngaySinhDatePicker.getPromptText(), formatter);
        }
        String soDienThoai = soDienThoaiTextField.getText().isEmpty() ? soDienThoaiTextField.getPromptText()
                : soDienThoaiTextField.getText();
        String email = emailTextField.getText().isEmpty() ? emailTextField.getPromptText() : emailTextField.getText();

        LocalDate ngayVaoTruong = ngayVaoTruongDatePicker.getValue();
        if (ngayVaoTruong == null && ngayVaoTruongDatePicker.getPromptText() != null) {
            ngayVaoTruong = LocalDate.parse(ngayVaoTruongDatePicker.getPromptText(), formatter);
        }

        Boolean gioiTinh = nam.isSelected();
        if (nu.isSelected()) {
            gioiTinh = false;
        }
        String tenLopQuanLi = lopQuanLiChoiceBox.getValue();
        
        String tenKhoa = khoaComboBox.getValue();

        int selectedIndex = bangSinhVien.getSelectionModel().getFocusedIndex();
        SinhVien sv = bangSinhVien.getItems().get(selectedIndex);

        Integer maKhoa = sv.getMaKhoa(), maLopQuanLi = sv.getMaLopQuanLi();

        sinhVien = new SinhVien();
        try {
            sinhVien.setMaSo(maSo);
            sinhVien.setHoTen(hoTen);
            sinhVien.setGioiTinh(gioiTinh);
            sinhVien.setQueQuan(queQuan);
            sinhVien.setDiaChiThuongTru(diaChiHienTai);
            sinhVien.setSoDienThoai(soDienThoai);
            sinhVien.setEmail(email);
            sinhVien.setNgaySinh(ngaySinh);
            sinhVien.setNgayVaoTruong(ngayVaoTruong);
            sinhVien.setTenLopQuanLi(tenLopQuanLi);
            sinhVien.setKhoa(tenKhoa);
            sinhVien.setMaKhoa(maKhoa);
            sinhVien.setMaLopQuanLi(maLopQuanLi);
        } catch (Exception e) {
            alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        return sinhVien;
    }

    private void renderSinhVienKhiClick(SinhVien sv) {
        maSoTextField.setText(sv.getMaSo().toString());
        hoTenTextField.setPromptText(sv.getHoTen().toString());
        ngaySinhDatePicker.setPromptText(sv.getNgaySinh().toString());
        queQuanTextField.setPromptText(sv.getQueQuan().toString());
        diaChiThuongTruTextField.setPromptText(sv.getDiaChiThuongTru().toString());
        emailTextField.setPromptText(sv.getEmail());
        soDienThoaiTextField.setPromptText(sv.getSoDienThoai());
        khoaComboBox.setValue(sv.getKhoa());
        ngayVaoTruongDatePicker.setPromptText(sv.getNgayVaoTruong().toString());
        if (sv.getGioiTinh()) {
            gioiTinhToggle.selectToggle(nam);
        } else {
            gioiTinhToggle.selectToggle(nu);
        }
        lopQuanLiChoiceBox.setValue(sv.getTenLopQuanLi());

        List<String> tenCacKhoa = khoaService.layTenTatCaKhoa();
        ObservableList<String> O_khoa = FXCollections.observableArrayList(tenCacKhoa);
        khoaComboBox.setItems(O_khoa);

        khoaComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldV, tenKhoa) -> {
            List<String> tenCacLop = lopService.layTenCacLopQuanLiTheoKhoa(tenKhoa);
            ObservableList<String> O_lopQuanLi = FXCollections.observableArrayList(tenCacLop);
            lopQuanLiChoiceBox.setItems(O_lopQuanLi);
        });
    }

    private List<SinhVien> loadDanhSachSinhVien() {
        List<SinhVien> list = new ArrayList<>();
        try {
            list = sinhVienService.layTatCaSinhVien();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
