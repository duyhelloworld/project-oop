package huce.cntt.oop.doan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import huce.cntt.oop.doan.entities.MonHoc;
import huce.cntt.oop.doan.service.KhoaService;
import huce.cntt.oop.doan.service.MonHocService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

public class QLMHController {
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
    private ComboBox<String> khoa;
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

    private MonHoc monHoc;
    private Alert alert;
    private ToggleGroup group;

    private ObservableList<MonHoc> observableList;

    private MonHocService service;
    private KhoaService khoaService;

    public QLMHController() {
        this.service = MonHocService.getInstance();
        this.khoaService = KhoaService.getInstance();
    }

    @FXML
    public void initialize() {
        group = new ToggleGroup();
        batBuoc.setToggleGroup(group);
        koBatBuoc.setToggleGroup(group);
        // Khởi tạo và cấu hình TableView và các cột
        cotMaMon.setCellValueFactory(new PropertyValueFactory<MonHoc, Integer>("maMon"));
        cotTenMon.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTenMon()));
        cotSoTinChi.setCellValueFactory(new PropertyValueFactory<>("soTinChi"));
        cotBatBuoc.setCellValueFactory(new PropertyValueFactory<>("batBuoc"));
        cotMonTienQuyet.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMonTienQuyet()));
        cotKhoa.setCellValueFactory(new PropertyValueFactory<>("khoa"));
        cotMoTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));

        // Gọi phương thức để lấy dữ liệu từ cơ sở dữ liệu và hiển thị lên TableView
        List<MonHoc> monHocs = layTatCaMonHoc();
        observableList = FXCollections.observableArrayList();
        observableList.addAll(monHocs);
        tableView.setItems(observableList);

        timKiemMonHoc.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String searchOption = timKiemTheo.getValue();
                String searchText = timKiemMonHoc.getText();

                List<MonHoc> searchResults = new ArrayList<MonHoc>();

                switch (searchOption) {
                    case "Mã môn":
                        try {
                            int maMon = Integer.parseInt(searchText);
                            searchResults = service.layMonHocTheoMaSo(maMon);
                        } catch (NumberFormatException e) {
                            // Hiển thị thông báo lỗi nếu mã môn không hợp lệ
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Lỗi");
                            alert.setHeaderText(null);
                            alert.setContentText("Mã môn không hợp lệ");
                            alert.show();
                        }
                        break;
                    case "Tên môn":
                    try {
                        searchResults = service.timKiemMonHocTheoTen(searchText);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Hiển thị thông báo lỗi nếu tên môn không hợp lệ
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setContentText("Tên môn không hợp lệ");
                        alert.show();
                    }
                        break;
                    case "Khoa":
                    try {
                        searchResults = service.timKiemMonHocTheoKhoa(searchText);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Hiển thị thông báo lỗi nếu tên khoa không hợp lệ
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setContentText("Tên khoa không hợp lệ");
                        alert.show();
                    }
                        break;
                    case "Tất cả":
                        searchResults = service.layTatCaMonHoc();
                }
                // Xóa nội dung của TableView trước khi hiển thị kết quả tìm kiếm
                tableView.getItems().clear();

                if (!searchResults.isEmpty()) {
                    observableList.clear();
                    observableList.setAll(searchResults);
                } else {
                    // Hiển thị thông báo nếu không tìm thấy kết quả
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
                if (selectedMonHoc != null) {
                    // Đổ dữ liệu từ đối tượng được chọn vào các TextField
                    maMon.setText(Integer.toString(selectedMonHoc.getMaMon()));
                    tenMon.setText(selectedMonHoc.getTenMon());
                    soTinChi.setText(Integer.toString(selectedMonHoc.getSoTinChi()));
                    if (selectedMonHoc.getBatBuoc()) {
                        group.selectToggle(batBuoc);
                    } else
                        group.selectToggle(koBatBuoc);
                    monTienQuyet.setText(selectedMonHoc.getMonTienQuyet());
                    khoa.setPromptText(selectedMonHoc.getKhoa());
                    moTa.setText(selectedMonHoc.getMoTa());
                }
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

        if (monHoc == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText(null);
        alert.setContentText("Xác nhận xóa môn này?");

        Optional<ButtonType> confirm = alert.showAndWait();

        if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
            try {
                khoaService.xoaMonKhoa(monHoc.getMaMon());
                boolean xoaThanhCong = service.xoaMonHoc(monHoc.getMaMon());

                if (xoaThanhCong) {
                    // Xóa môn học từ TableView
                    tableView.getItems().remove(monHoc);
                    // Hiển thị thông báo xóa thành công
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Thành công");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Xóa thành công");
                    successAlert.show();
                }
            } catch (Exception e) {
                // Hiển thị thông báo lỗi nếu có lỗi xảy ra
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Lỗi");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText(e.getMessage());
                errorAlert.show();
                e.printStackTrace();
            }
        }
    }

    private List<MonHoc> layTatCaMonHoc() {
        return service.layTatCaMonHoc();
    }
}