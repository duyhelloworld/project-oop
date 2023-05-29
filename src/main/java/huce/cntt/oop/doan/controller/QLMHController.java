package huce.cntt.oop.doan.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    private TableView<String> tableView;
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
        tableView.getItems();
    }

    private List<MonHoc> fetchDataFromDatabase() {
        List<MonHoc> items = new ArrayList<>();

        try {
            // Kết nối tới cơ sở dữ liệu
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlsinhvien", "hiep","kaizu6789");

            // Tạo câu truy vấn
            String query = "SELECT mh.MaMH, mh.TenMH, mh.SoTinChi, mh.BatBuoc, mh.MonTienQuyet, k.TenKhoa, mh.MoTa " +
                    "FROM monhoc mh " +
                    "INNER JOIN lopquanly lql ON mh.MaMH = lql.MaMH " +
                    "INNER JOIN khoa k ON lql.MaKhoa = k.MaKhoa " +
                    "INNER JOIN sinhvien sv ON lql.MaLop = sv.MaLop " +
                    "INNER JOIN tinhdiem td ON sv.MaSV = td.MaSV";

            // Tạo statement và thực thi truy vấn
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Lặp qua các dòng trong ResultSet
            while (resultSet.next()) {
                String maMon = resultSet.getString("MaMH");
                String tenMon = resultSet.getString("TenMH");
                int soTinChi = resultSet.getInt("SoTinChi");
                boolean batBuoc = resultSet.getBoolean("BatBuoc");
                String monTienQuyet = resultSet.getString("MonTienQuyet");
                String khoa = resultSet.getString("TenKhoa");
                String moTa = resultSet.getString("MoTa");

                // Tạo đối tượng Item từ dữ liệu trong ResultSet
                MonHoc item = new MonHoc(maMon, tenMon, soTinChi, batBuoc, monTienQuyet, khoa, moTa);
                items.add(item);
            }

            // Đóng kết nối và statement
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }
}
