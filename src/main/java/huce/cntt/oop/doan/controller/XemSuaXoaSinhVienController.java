package huce.cntt.oop.doan.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private TableView<DTOSinhVien> bangSinhVien;

    @FXML
    private TableColumn<DTOSinhVien, Integer> cotMaSoSV;
    @FXML
    private TableColumn<DTOSinhVien, String> cotHoTen;
    @FXML
    private TableColumn<DTOSinhVien, LocalDate> cotNgaySinh;
    @FXML
    private TableColumn<DTOSinhVien, Boolean> cotGioiTinh;
    @FXML
    private TableColumn<DTOSinhVien, String> cotQueQuan;
    @FXML
    private TableColumn<DTOSinhVien, String> cotDiaChiThuongTru;
    @FXML
    private TableColumn<DTOSinhVien, String> cotKhoa;
    @FXML
    private TableColumn<DTOSinhVien, String> cotEmail;
    @FXML
    private TableColumn<DTOSinhVien, String> cotSoDienThoai;
    @FXML
    private TableColumn<DTOSinhVien, Integer> cotNienKhoa;
    @FXML
    private TableColumn<DTOSinhVien, String> cotLopQuanLi;
    @FXML
    private TableColumn<DTOSinhVien, CheckBox> cotXoa;
    @FXML
    private TableColumn<DTOSinhVien, Integer> cotMaLopQuanLi;
    @FXML
    private TableColumn<DTOSinhVien, Integer> cotMaKhoa;

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

    private DTOSinhVien dtoSinhVien;
    private ToggleGroup gioiTinhToggle;
    private ObservableList<DTOSinhVien> data;
    private Alert alert;

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

        nutLuu.setOnAction(e -> luu());
        nutQuayLai.setOnAction(e -> quayLai());
    }

    private void quayLai() {
        if (!nutQuayLai.isPressed()) {
            dtoSinhVien = layGiaTriPromptHoacText();
            alert.setAlertType(AlertType.CONFIRMATION);
            alert.setContentText("Bạn có thay đổi chưa lưu.\nLưu ?");
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                luu();
            }
        }
    }

    private void luu() {
        if (!nutLuu.isPressed()) {
            dtoSinhVien = layGiaTriPromptHoacText();
            try {
                sinhVienService.capNhatThongTinSinhVien(dtoSinhVien);
                lopService.capNhatLopQuanLi(dtoSinhVien);
                data.clear();
                clearDataDaNhap();
                data.setAll(loadDanhSachSinhVien());
                alert.setAlertType(AlertType.INFORMATION);
                alert.setContentText("Sinh Viên đã cập nhật : \n" + dtoSinhVien.toString());
                alert.setHeight(500);
                alert.show();
            } catch (Exception e) {
                e.printStackTrace();    
                alert.setAlertType(AlertType.ERROR);
                alert.setContentText(e.getLocalizedMessage());
                alert.show();
            }
        }
    }

    private void showSinhVienTableView() {
        cotMaSoSV.setCellValueFactory(new PropertyValueFactory<DTOSinhVien, Integer>("maSo"));
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
            TableRow<DTOSinhVien> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() >= 2) {
                    alert.setAlertType(AlertType.WARNING);
                    alert.setContentText("Bạn sống hơi nhanh. Hãy sống chậm lại");
                    alert.show();
                }

                if (e.getClickCount() == 1 && !row.isEmpty()) {
                    alert.setAlertType(AlertType.CONFIRMATION);
                    alert.setContentText("Bạn có muốn thay đổi sinh viên này?");
                    Optional<ButtonType> confirm = alert.showAndWait();
                    if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                        dtoSinhVien = row.getItem();
                        renderSinhVienKhiClick(dtoSinhVien);
                    }
                }
            });
            return row;
        });
    }

    private void clearDataDaNhap(){
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

    private DTOSinhVien layGiaTriPromptHoacText() {
        Integer maSo = Integer.valueOf(maSoTextField.getText());

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
        if (ngaySinh == null && ngaySinhDatePicker.getPromptText() != null) {
            ngaySinh = LocalDate.parse(ngaySinhDatePicker.getPromptText(), formatter);
        }
        String soDienThoai = soDienThoaiTextField.getText().isEmpty() ? soDienThoaiTextField.getPromptText() : soDienThoaiTextField.getText();
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
        DTOSinhVien sv = bangSinhVien.getItems().get(selectedIndex);

        Integer maKhoa = sv.getMaKhoa(), maLopQuanLi = sv.getMaLopQuanLi();

        dtoSinhVien = new DTOSinhVien();
        try {
            dtoSinhVien.setMaSo(maSo);
            dtoSinhVien.setHoTen(hoTen);
            dtoSinhVien.setGioiTinh(gioiTinh);
            dtoSinhVien.setQueQuan(queQuan);  
            dtoSinhVien.setDiaChiThuongTru(diaChiHienTai);          
            dtoSinhVien.setSoDienThoai(soDienThoai);
            dtoSinhVien.setEmail(email);
            dtoSinhVien.setNgaySinh(ngaySinh);
            dtoSinhVien.setNgayVaoTruong(ngayVaoTruong);
            dtoSinhVien.setTenLopQuanLi(tenLopQuanLi);
            dtoSinhVien.setKhoa(tenKhoa);
            dtoSinhVien.setMaKhoa(maKhoa);
            dtoSinhVien.setMaLopQuanLi(maLopQuanLi);
        } catch (Exception e) {
            alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        return dtoSinhVien;
    }

    private void renderSinhVienKhiClick(DTOSinhVien sv) {
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

    private List<DTOSinhVien> loadDanhSachSinhVien() {
        List<DTOSinhVien> list = new ArrayList<>();
        try {
            list = sinhVienService.layTatCaSinhVien();
        } catch (Exception e) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
        return list;
    }
}
