package huce.cntt.oop.doan.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

import huce.cntt.oop.doan.entities.MonHoc;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class QLMHController {
    @FXML
    private TableView<Integer> tableView;
    @FXML
    private TableColumn<MonHoc, String> cotMaMon;
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
    private ChoiceBox timKiemTheo;
    @FXML
    private ComboBox khoa;
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
    @FXML
    private void initialize() {
        cotMaMon.setCellValueFactory(new PropertyValueFactory<>("maMon"));
        cotTenMon.setCellValueFactory(new PropertyValueFactory<>("tenMon"));
        cotSoTinChi.setCellValueFactory(new PropertyValueFactory<>("soTinChi"));
        cotBatBuoc.setCellValueFactory(new PropertyValueFactory<>("batBuoc"));
        cotMonTienQuyet.setCellValueFactory(new PropertyValueFactory<>("monTienQuyet"));
        cotKhoa.setCellValueFactory(new PropertyValueFactory<>("khoa"));
        cotMoTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));

        // Gọi phương thức để lấy dữ liệu từ cơ sở dữ liệu và hiển thị nó trong
        // TableView
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        List<MonHoc> itemList = new ArrayList<>();
    
        // Kết nối cơ sở dữ liệu
        Connection connection = DriverManager.getConnection("1670603812210@@127.0.0.1@3306@qlsinhvien", "hiep", "kaizu6789");
    
        // Chuẩn bị và thực thi truy vấn
        String query = "SELECT mh.MaMH, mh.TenMH, mh.SoTinChi, mh.BatBuoc, mh.MonTienQuyet, k.TenKhoa"+
        "FROM monhoc mh"+
        "INNER JOIN lopquanly lql"+
        "INNER JOIN khoa k"+
        "INNER JOIN sinhvien sv"+
        "INNER JOIN tinhdiem td"+
        "GROUP BY mh.`MaMH`";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
    
        // Lặp qua kết quả truy vấn và tạo đối tượng Item cho mỗi hàng
        while (resultSet.next()) {
            MonHoc item = new MonHoc();
            item.setMaMonHoc(resultSet.getString("ma_mon"));
            item.setTenMon(resultSet.getString("ten_mon"));
            // Thiết lập các thuộc tính khác của Item
    
            // Thêm Item vào danh sách
            itemList.add(item);
        }
    
        // Đóng kết nối và gán danh sách Item cho TableView
        connection.close();
        tableView.getItems().setAll(itemList);
    }
}
