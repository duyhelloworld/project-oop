package huce.cntt.oop.doan.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.VaiTro;
import huce.cntt.oop.doan.entities.exception.CapNhatException;
import huce.cntt.oop.doan.entities.exception.ChuyenDiaChiException;
import huce.cntt.oop.doan.entities.exception.ChuyenHoTenException;
import huce.cntt.oop.doan.entities.exception.ChuyenSoException;
import huce.cntt.oop.doan.entities.exception.EmailException;
import huce.cntt.oop.doan.entities.exception.KhoaLopException;
import huce.cntt.oop.doan.entities.exception.NgayGioException;
import huce.cntt.oop.doan.entities.exception.ThieuGiaTriException;
import huce.cntt.oop.doan.entities.exception.XoaException;
import huce.cntt.oop.doan.interfaces.IKhoaService;
import huce.cntt.oop.doan.interfaces.ILopService;
import huce.cntt.oop.doan.interfaces.ISinhVienService;
import huce.cntt.oop.doan.loader.LoadThemSinhVien;
import huce.cntt.oop.doan.loader.LoadTrangChu;
import huce.cntt.oop.doan.service.KhoaService;
import huce.cntt.oop.doan.service.LopService;
import huce.cntt.oop.doan.service.SinhVienService;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class HomeSinhVienController {

    private final ISinhVienService sinhVienService = SinhVienService.getInstance();
    private final ILopService lopService = LopService.getInstance();
    private final IKhoaService khoaService  = KhoaService.getInstance();
    private Stage stage;
    private GiangVien giangVien;

    public HomeSinhVienController(Stage stage, GiangVien giangVien) {
        this.stage = stage;
        this.giangVien = giangVien;
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
    private TableColumn<SinhVien, String> cotGioiTinh;
    @FXML
    private TableColumn<SinhVien, String> cotQueQuan;
    @FXML
    private TableColumn<SinhVien, String> cotDiaChiThuongTru;
    @FXML
    private TableColumn<SinhVien, String> cotTenKhoa;
    @FXML
    private TableColumn<SinhVien, String> cotEmail;
    @FXML
    private TableColumn<SinhVien, String> cotSoDienThoai;
    @FXML
    private TableColumn<SinhVien, Integer> cotNienKhoa;
    @FXML
    private TableColumn<SinhVien, String> cotTenLopQuanLi;
    @FXML
    private TableColumn<SinhVien, Integer> cotMaLopQuanLi;

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
    private ComboBox<String> lopQuanLiComboBox;
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<String> searchComboBox;
    @FXML
    private Button nutTimKiem;
    @FXML
    private Button nutXoa;
    @FXML
    private Button nutThem;

    private SinhVien sinhVien;
    private ToggleGroup gioiTinhToggle;
    private ObservableList<SinhVien> data;
    private Alert alert;
    private int soLuotTimKiem;

    @FXML
    private Button nutLuu;
    @FXML
    private Button nutQuayLai;

    @FXML
    public void initialize() {
        alert = new Alert(AlertType.INFORMATION);

        data = FXCollections.observableArrayList();
        data.addAll(sinhVienService.layTatCaSinhVien());

        showSinhVienTableView();

        // Set Bool
        gioiTinhToggle = new ToggleGroup();
        nam.setToggleGroup(gioiTinhToggle);
        nu.setToggleGroup(gioiTinhToggle);

        searchComboBox.getItems().addAll("Tên sinh viên", "Mã số", "Email");

        nutLuu.setOnAction(e -> {
            if (!nutLuu.isPressed()) {
                luu();
            }
        });
        nutQuayLai.setOnAction(e -> {
            if (!nutQuayLai.isPressed()) {
                if (chuaThayDoi()) {
                    quayLaiHome();
                } else {
                    if (ok("Bạn có thay đổi chưa lưu.\nLưu ?")) {
                        luu();
                        quayLaiHome();
                    }
                }
        }});
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

        nutXoa.setOnAction(e -> {
            if (!nutXoa.isPressed()) {
                xoa();
            }
        });

        nutThem.setOnAction(e -> {
            if (!nutThem.isPressed()) {
                if (chuaThayDoi()) {
                    doiSceneSangThemSinhVien();
                } else {
                    if (ok("Bạn có thay đổi chưa lưu.\nLưu ?")) {
                        luu();
                        doiSceneSangThemSinhVien();
                    }
                }
            }
        });
    }

    private void luu() {
        sinhVien = layGiaTriDaNhap();
        try {
            sinhVienService.capNhatThongTinSinhVien(sinhVien);            
            lopService.capNhatLopQuanLi(sinhVien);
            clearDataDaNhap();
            loadLaiBang(sinhVienService.layTatCaSinhVien());
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Sinh Viên đã cập nhật : \n" + sinhVien.toString());
            alert.setHeight(400);
            alert.show();
        } catch (CapNhatException e) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    private void timKiem() {
        String searchString = searchTextField.getText();
        soLuotTimKiem++;
        if (searchString.isBlank()) {
            if (soLuotTimKiem >= 3) {
                loadLaiBang(sinhVienService.layTatCaSinhVien());
                return;
            } else {
                alert.setAlertType(AlertType.WARNING);
                alert.setContentText("Ô tìm kiếm trống. Hãy nhập điểu gì đó");
                alert.show();
                return;
            }
        }

        alert.setAlertType(AlertType.ERROR);
        clearDataDaNhap();
        switch (searchComboBox.getSelectionModel().getSelectedItem()) {
            case "Tên sinh viên":
                loadLaiBang(sinhVienService.timKiemSinhVienTheoTen(searchString));
                break;
            case "Mã số":
                try {
                    sinhVien.setMaSo(searchString);
                    int mssv = sinhVien.getMaSo();
                    int soSinhVienHienTai = sinhVienService.tongSoSinhVien();
                    if (mssv < 1 || mssv >= soSinhVienHienTai) {
                        alert.setContentText("Lỗi : vượt khoảng sinh viên đang học tại trường!\nHiện tại có " + soSinhVienHienTai + " sinh viên.");
                        alert.show();
                        return;
                    }
                    loadLaiBang(List.of(sinhVienService.timKiemSinhVienTheoMaSo(mssv)));
                } catch (ChuyenSoException e) {
                    alert.setContentText(e.getMessage());
                    alert.show();
                    return;
                }
            case "Email":
                try {
                    SinhVien sv = new SinhVien();
                    sv.setEmail(searchString);
                    loadLaiBang(List.of(sinhVienService.timKiemSinhVienTheoEmail(searchString)));
                } catch (Exception e) {
                    alert.setContentText(e.getMessage());
                    alert.show();
                    return;
                }
                return;
            }
    }

    private void xoa(){
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
            if (ok("Sinh viên này đang theo học tại " + soLopMonDangTheoHoc + " lớp môn học.\nVẫn tiếp tục?")) {
                lopService.xoaSinhVienKhoiLopMonHoc(mssv);
                boolean xoaThanhCong = sinhVienService.xoaSinhVienTheoMaSo(mssv);
                if (xoaThanhCong) {
                    loadLaiBang(sinhVienService.layTatCaSinhVien());
                    alert.setAlertType(AlertType.INFORMATION);
                    alert.setContentText("Xoá thành công sinh viên mã số " + mssv);
                    alert.show();
                }
            }
        } catch (XoaException e) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    private void showSinhVienTableView() {
        cotMaSoSV.setCellValueFactory(new PropertyValueFactory<SinhVien, Integer>("maSo"));
        cotHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        cotNgaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        cotGioiTinh.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getGioiTinhString()));
        cotQueQuan.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
        cotDiaChiThuongTru.setCellValueFactory(new PropertyValueFactory<>("diaChiThuongTru"));
        cotTenKhoa.setCellValueFactory(new PropertyValueFactory<>("khoa"));
        cotEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cotSoDienThoai.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        cotNienKhoa.setCellValueFactory(new PropertyValueFactory<>("nienKhoa"));
        cotTenLopQuanLi.setCellValueFactory(new PropertyValueFactory<>("tenLopQuanLi"));
        cotMaLopQuanLi.setCellValueFactory(new PropertyValueFactory<>("maLopQuanLi"));

        cotMaLopQuanLi.setVisible(false);
        bangSinhVien.setItems(data);

        bangSinhVien.setRowFactory(tableView -> {
            TableRow<SinhVien> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && !row.isEmpty()) {
                    if (ok("Bạn có muốn thay đổi sinh viên này?")) {
                        sinhVien = row.getItem();
                        showDataCapNhatKhiClick(sinhVien);
                    }
                }
            });
            return row;
        });
    }

    private void clearDataDaNhap() {
        maSoTextField.clear();
        hoTenTextField.clear();
        queQuanTextField.clear();
        diaChiThuongTruTextField.clear();
        emailTextField.clear();
        soDienThoaiTextField.clear();
        gioiTinhToggle.selectToggle(null);
        khoaComboBox.getEditor().clear();
        lopQuanLiComboBox.setValue(null);
        ngaySinhDatePicker.setValue(null);
        ngayVaoTruongDatePicker.setValue(null);
    }

    private SinhVien layGiaTriDaNhap() {
        sinhVien = new SinhVien();
        try {
            String maSo = maSoTextField.getText();
            String hoTen = hoTenTextField.getText();
            String queQuan = queQuanTextField.getText();
            String diaChiThuongTru = diaChiThuongTruTextField.getText();
            String soDienThoai = soDienThoaiTextField.getText();
            String email = emailTextField.getText();
            LocalDate ngaySinh = ngaySinhDatePicker.getValue();
            LocalDate ngayVaoTruong = ngayVaoTruongDatePicker.getValue();
            Boolean gioiTinh = nam.isSelected();
            if (nu.isSelected()) {
                gioiTinh = false;
            }
            String tenLopQuanLi = lopQuanLiComboBox.getValue();
            String tenKhoa = khoaComboBox.getValue();
            Integer maLopQuanLi = lopService.checkKhoa(tenLopQuanLi, tenKhoa);
            if (maLopQuanLi == null) {
                throw new KhoaLopException();
            }
            if (hoTen == null || hoTen.isBlank()) {
                throw new ThieuGiaTriException("họ tên");
            }
            if (ngaySinh == null) {
                throw new ThieuGiaTriException("ngày sinh");
            }
            if (queQuan == null || queQuan.isBlank()) {
                throw new ThieuGiaTriException("quê quán");
            }
            if (diaChiThuongTru == null || diaChiThuongTru.isBlank()) {
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

            sinhVien.setMaSo(maSo);
            sinhVien.setHoTen(hoTen);
            sinhVien.setGioiTinh(gioiTinh);
            sinhVien.setQueQuan(queQuan);
            sinhVien.setDiaChiThuongTru(diaChiThuongTru);
            sinhVien.setSoDienThoai(soDienThoai);
            sinhVien.setEmail(email);
            sinhVien.setNgaySinh(ngaySinh);
            sinhVien.setNgayVaoTruong(ngayVaoTruong);
            sinhVien.setTenLopQuanLi(tenLopQuanLi);
            sinhVien.setKhoa(tenKhoa);
            sinhVien.setMaLopQuanLi(maLopQuanLi);
        } catch (ChuyenDiaChiException | ChuyenHoTenException | ChuyenSoException | NgayGioException | 
        KhoaLopException | EmailException | ThieuGiaTriException e) {
            alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sinhVien;
    }

    private void showDataCapNhatKhiClick(SinhVien sv) {
        maSoTextField.setText(sv.getMaSo().toString());
        hoTenTextField.setText(sv.getHoTen().toString());
        ngaySinhDatePicker.setValue(sv.getNgaySinh());
        queQuanTextField.setText(sv.getQueQuan().toString());
        diaChiThuongTruTextField.setText(sv.getDiaChiThuongTru().toString());
        emailTextField.setText(sv.getEmail());
        soDienThoaiTextField.setText(sv.getSoDienThoai());
        khoaComboBox.setValue(sv.getKhoa());
        lopQuanLiComboBox.setValue(sv.getTenLopQuanLi());
        ngayVaoTruongDatePicker.setValue(sv.getNgayVaoTruong());
        if (sv.getGioiTinh()) {
            gioiTinhToggle.selectToggle(nam);
        } else {
            gioiTinhToggle.selectToggle(nu);
        }

        ObservableList<String> tenCacKhoa = FXCollections.observableArrayList(khoaService.layTenTatCaKhoa());
        khoaComboBox.setItems(tenCacKhoa);

        khoaComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldV, tenKhoa) -> {
            ObservableList<String> tenCacLop = FXCollections.observableArrayList();
            lopQuanLiComboBox.setValue(null);
            tenCacLop.addAll(lopService.layTenCacLopQuanLiTheoKhoa(tenKhoa));
            lopQuanLiComboBox.setItems(tenCacLop);
        });
    }

    private boolean chuaThayDoi() {
        String hoTen = hoTenTextField.getText();
        String queQuan = queQuanTextField.getText();
        String diaChiThuongTru = diaChiThuongTruTextField.getText();
        String soDienThoai = soDienThoaiTextField.getText();
        String email = emailTextField.getText();
        String tenKhoa = khoaComboBox.getSelectionModel().getSelectedItem();
        String tenLopQuanLi = lopQuanLiComboBox.getSelectionModel().getSelectedItem();
        LocalDate ngaySinh = ngaySinhDatePicker.getValue();
        LocalDate ngayVaoTruong = ngaySinhDatePicker.getValue();
        return 
            (hoTen == null || hoTen.isBlank()) ||
            (ngaySinh == null) || 
            (queQuan == null || queQuan.isBlank()) ||
            (diaChiThuongTru == null || diaChiThuongTru.isBlank()) || 
            (soDienThoai == null || soDienThoai.isBlank()) || 
            (email == null || email.isBlank()) ||
            (ngayVaoTruong == null) || 
            (tenKhoa == null || tenKhoa.isBlank()) || 
            (tenLopQuanLi == null || tenLopQuanLi.isBlank());
    }

    private boolean ok(String message){
        alert.setAlertType(AlertType.CONFIRMATION);
        alert.setContentText(message);
        Optional<ButtonType> confirm = alert.showAndWait();
        return confirm.isPresent() && confirm.get() == ButtonType.OK;
    }

    private void doiSceneSangThemSinhVien(){
        if (chuaThayDoi()) {
            Scene themSV = LoadThemSinhVien.loadThemSinhVien(stage, giangVien);
            stage.setScene(themSV);
            stage.show();
        }
    }

    private void quayLaiHome() {
        Scene home = LoadTrangChu.loadTrangChu(stage, VaiTro.NVDT, giangVien);
        stage.setScene(home);
        stage.show();
    }

    private void loadLaiBang(List<SinhVien> list) {
        data.clear();
        data.setAll(list);
    }
}
