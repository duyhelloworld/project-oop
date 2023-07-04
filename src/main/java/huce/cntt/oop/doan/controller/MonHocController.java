package huce.cntt.oop.doan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.entities.MonHoc;
import huce.cntt.oop.doan.entities.VaiTro;
import huce.cntt.oop.doan.entities.exception.XoaException;
import huce.cntt.oop.doan.loader.LoadTrangChu;
import huce.cntt.oop.doan.service.KhoaService;
import huce.cntt.oop.doan.service.LopService;
import huce.cntt.oop.doan.service.MonHocService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class MonHocController {
    @FXML
    private TableView<MonHoc> tableView;
    @FXML
    private TableColumn<MonHoc, Integer> cotMaMon;
    @FXML
    private TableColumn<MonHoc, String> cotTenMon;
    @FXML
    private TableColumn<MonHoc, Integer> cotSoTinChi;
    @FXML
    private TableColumn<MonHoc, Boolean> cotBatBuoc;
    @FXML
    private TableColumn<MonHoc, String> cotMonTienQuyet;
    @FXML
    private TableColumn<MonHoc, String> cotKhoa;
    @FXML
    private TableColumn<MonHoc, String> cotMoTa;
    @FXML
    private TextField timKiemMonHoc;
    @FXML
    private TextField maMon;
    @FXML
    private TextField tenMon;
    @FXML
    private TextField soTinChi;
    @FXML
    private RadioButton batBuoc;
    @FXML
    private RadioButton koBatBuoc;
    @FXML
    private TextField monTienQuyet;
    @FXML
    private ChoiceBox<String> khoaChoiceBox;
    @FXML
    private TextField moTa;
    @FXML
    private ChoiceBox<String> timKiemTheo;
    @FXML
    private Button themMon;
    @FXML
    private Button xoaMon;
    @FXML
    private Button suaMon;
    @FXML
    private Button luuThayDoi;
    @FXML
    private Button thoat;

    private ToggleGroup group;

    private ObservableList<MonHoc> observableList;

    private MonHocService monHocService = MonHocService.getInstance();
    private KhoaService khoaService = KhoaService.getInstance();
    private LopService lopService = LopService.getInstance();
    private Stage stage;
    private GiangVien giangVien;

    public MonHocController(Stage stage, GiangVien giangVien) {
        this.giangVien = giangVien;
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        group = new ToggleGroup();
        batBuoc.setToggleGroup(group);
        koBatBuoc.setToggleGroup(group);
        // Khởi tạo và cấu hình TableView và các cột
        cotMaMon.setCellValueFactory(new PropertyValueFactory<MonHoc, Integer>("maMon"));
        cotTenMon.setCellValueFactory( new PropertyValueFactory<>("tenMon"));
        cotSoTinChi.setCellValueFactory(new PropertyValueFactory<>("soTinChi"));
        cotBatBuoc.setCellValueFactory(new PropertyValueFactory<>("batBuoc"));
        cotMonTienQuyet.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMonTienQuyet()));
        cotKhoa.setCellValueFactory(new PropertyValueFactory<>("khoa"));
        cotMoTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));

        List<MonHoc> monHocs = monHocService.layTatCaMonHoc();
        observableList = FXCollections.observableArrayList(monHocs);
        tableView.setItems(observableList);

        maMon.setEditable(false);

        thoat.setOnAction(e -> {
            if (!thoat.isPressed()) {
                thoat();
            }
        });

        timKiemMonHoc.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String searchOption = timKiemTheo.getValue();
                String searchText = timKiemMonHoc.getText();

                List<MonHoc> searchResults = new ArrayList<MonHoc>();

                switch (searchOption) {
                    case "Mã môn":
                        try {
                            int maMon = Integer.parseInt(searchText);
                            searchResults.clear();
                            searchResults.add(monHocService.layMonHocTheoMaSo(maMon));
                        } catch (NumberFormatException e) {
                            // Hiển thị thông báo lỗi nếu mã môn không hợp lệ
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Lỗi");
                            alert.setHeaderText(null);
                            alert.setContentText("Mã môn không hợp lệ");
                            alert.show();
                        }
                        break;
                    case "Tên môn":
                    try {
                        searchResults = monHocService.timKiemMonHocTheoTen(searchText);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Hiển thị thông báo lỗi nếu tên môn không hợp lệ
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setContentText("Tên môn không hợp lệ");
                        alert.show();
                    }
                        break;
                    case "Khoa":
                    try {
                        searchResults = monHocService.timKiemMonHocTheoKhoa(searchText);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Hiển thị thông báo lỗi nếu tên khoa không hợp lệ
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setContentText("Tên khoa không hợp lệ");
                        alert.show();
                    }
                        break;
                    case "Tất cả":
                        searchResults = monHocService.layTatCaMonHoc();
                }
                // Xóa nội dung của TableView trước khi hiển thị kết quả tìm kiếm
                tableView.getItems().clear();

                if (!searchResults.isEmpty()) {
                    observableList.clear();
                    observableList.setAll(searchResults);
                } else {
                    // Hiển thị thông báo nếu không tìm thấy kết quả
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Không tìm thấy kết quả");
                    alert.show();
                }

                // Xóa nội dung của TextField
                timKiemMonHoc.clear();
            }
        });

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                MonHoc selectedMonHoc = tableView.getSelectionModel().getSelectedItem();
                if (selectedMonHoc == null) {
                    return;
                }
                // Đổ dữ liệu từ đối tượng được chọn vào các TextField
                maMon.setText(Integer.toString(selectedMonHoc.getMaMon()));
                tenMon.setText(selectedMonHoc.getTenMon());
                soTinChi.setText(Integer.toString(selectedMonHoc.getSoTinChi()));
                if (selectedMonHoc.getBatBuoc()) {
                    group.selectToggle(batBuoc);
                } else
                    group.selectToggle(koBatBuoc);
                monTienQuyet.setText(selectedMonHoc.getMonTienQuyet());

                ObservableList<String> tenCacKhoa = FXCollections.observableArrayList();
                if (tenCacKhoa.addAll(selectedMonHoc.getKhoa())) {
                    khoaChoiceBox.setItems(tenCacKhoa);
                }
                if (!khoaChoiceBox.isPressed()) {
                    tenCacKhoa.clear();
                    tenCacKhoa.addAll(khoaService.layTenTatCaKhoa());
                    khoaChoiceBox.setItems(tenCacKhoa);
                }
                moTa.setText(selectedMonHoc.getMoTa());
            }
        });

        timKiemTheo.getItems().addAll("Mã môn", "Tên môn", "Khoa", "Tất cả");

        // timKiemMonHoc.setOnKeyPressed(e -> timKiemMonHoc);
        // themMon.setOnAction(e -> them());
        xoaMon.setOnAction(e -> xoa());
        // suaMon.setOnAction(e -> sua());
        // luuThayDoi.setOnAction(e -> luu());
        // thoat.setOnAction(e -> thoat());
    }

    // private void them(){
    // String ma = maMon.getText();
    // String ten = tenMon.getText();
    // int soTin = Integer.parseInt(soTinChi.getText());
    // boolean isBatBuoc = Boolean.parseBoolean(batBuoc.getText());
    // String monTienQuyet = monTienQuyet.getText();
    // String tenKhoa = khoa.getValue();
    // String moTa = moTa.getText();

    // MonHoc monHoc = new MonHoc(maMon, tenMon, soTinChi, batBuoc, monTienQuyet,
    // khoa, moTa);

    // monHocModel.themMonHoc(monHoc);
    // }

    private void xoa() {
        MonHoc monHoc = tableView.getSelectionModel().getSelectedItem();
        if (monHoc == null || xoaMon.isPressed()) {
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setContentText("Xác nhận xóa môn này?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
            try {
                int maMon = monHoc.getMaMon();
                khoaService.xoaMonKhoa(maMon);
                List<Integer> cacMaLopMonHoc = lopService.layCacLopMonHocPhuThuoc(maMon);
                if (cacMaLopMonHoc == null) {
                    monHocService.xoaMonHoc(maMon);
                }
                for (Integer maLopMonHoc : cacMaLopMonHoc) {
                    lopService.xoaMonHocKhoiLopMonHoc(maMon);
                    lopService.xoaLopMonHocKhoiDiemSinhVien(maLopMonHoc);
                }

                boolean xoaThanhCong = monHocService.xoaMonHoc(monHoc.getMaMon());
                if (xoaThanhCong) {
                    // Xóa môn học từ TableView
                    tableView.getItems().remove(monHoc);
                    // Hiển thị thông báo xóa thành công
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Thành công");
                    successAlert.setContentText("Xóa thành công");
                    successAlert.show();
                }
            } catch (XoaException e) {
                e.printStackTrace();
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Lỗi");
                errorAlert.setContentText(e.getMessage());
                errorAlert.show();
            }
        }
    }

    private void thoat() {
        Scene trangChu = LoadTrangChu.loadTrangChu(stage, VaiTro.NVDT, giangVien);
        stage.setScene(trangChu);
        stage.show();
    }
}
