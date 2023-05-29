package huce.cntt.oop.doan.controller;

import java.net.URL;
import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.sql.Statement;

import huce.cntt.oop.doan.entities.MonHoc;
import huce.cntt.oop.doan.service.MonHocService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Objects;

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
    private TextField batBuoc;
    @FXML
    private TextField maMon;
    @FXML
    private TextField moTa;
    @FXML
    private TextField tenMon;
    @FXML
    private TextField soTinChi;
    @FXML
    private TextField monTienQuyet;
    @FXML
    private ChoiceBox<String> timKiemTheo;
    @FXML
    private ComboBox<String> khoa;
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

    MonHocService service = new MonHocService();
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // Khởi tạo và cấu hình TableView và các cột
        cotMaMon.setCellValueFactory(new PropertyValueFactory<>("maMon"));
        cotTenMon.setCellValueFactory(new PropertyValueFactory<>("tenMon"));
        cotSoTinChi.setCellValueFactory(new PropertyValueFactory<>("soTinChi"));
        cotBatBuoc.setCellValueFactory(new PropertyValueFactory<>("batBuoc"));
        cotMonTienQuyet.setCellValueFactory(new PropertyValueFactory<>("monTienQuyet"));
        cotKhoa.setCellValueFactory(new PropertyValueFactory<>("khoa"));
        cotMoTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));

        // Gọi phương thức để lấy dữ liệu từ cơ sở dữ liệu và hiển thị lên TableView
        List<MonHoc> items = fetchDataFromDatabase();
        tableView.getItems().setAll(items);
    }

    private List<MonHoc> fetchDataFromDatabase() {
        return service.fetchDataFromDatabase();
    }
}
