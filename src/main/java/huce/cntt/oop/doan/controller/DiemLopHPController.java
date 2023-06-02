package huce.cntt.oop.doan.controller;

import java.util.ArrayList;
import java.util.List;

import huce.cntt.oop.doan.entities.DiemLopHP;
import huce.cntt.oop.doan.entities.MonHoc;
import huce.cntt.oop.doan.service.DiemLopHPService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class DiemLopHPController {
    @FXML
    private TableView<DiemLopHP> tableView;
    @FXML
    private TableColumn<DiemLopHP, Integer> cotMSSV;
    @FXML
    private TableColumn<DiemLopHP, String> cotHoTen;
    @FXML
    private TableColumn<DiemLopHP, String> cotLopQL;
    @FXML
    private TableColumn<DiemLopHP, Float> cotDiemChuyenCan;
    @FXML
    private TableColumn<DiemLopHP, Float> cotDiemGK;
    @FXML
    private TableColumn<DiemLopHP, Float> cotDiemQT;
    @FXML
    private TableColumn<DiemLopHP, Float> cotKT;
    @FXML
    private TableColumn<DiemLopHP, Float> cotGPA;
    @FXML
    private TableColumn<DiemLopHP, String> cotDiemChu;
    // @FXML
    // private TableColumn<DiemLopHP, > cot;
    @FXML
    private ComboBox<String> timKiemTheo;
    @FXML
    private ChoiceBox<String> lop;
    @FXML
    private TextField monHoc;
    @FXML
    private Button xoa;
    @FXML
    private Button luuThongTin;
    @FXML
    private Button thoat;

    private ObservableList<DiemLopHP> observableList;

    private DiemLopHPService service;
    
    public void initialize(){
        timKiemTheo.getItems().addAll("Mã môn", "Tên môn");
        lop.getItems().addAll();
        monHoc.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String searchOption = timKiemTheo.getValue();
                String searchText = monHoc.getText();
                String searchOption2 = lop.getValue();

                List<DiemLopHP> searchResults = new ArrayList<DiemLopHP>();

                switch (searchOption) {
                    case "Mã môn":
                        try {
                            int maMon = Integer.parseInt(searchText);
                            searchResults = service.layDiemLopHPTheoMaMon(maMon);
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
                        searchResults = service.layDiemLopHPTheoTenMon(searchText, searchOption2);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Hiển thị thông báo lỗi nếu tên môn không hợp lệ
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setContentText("Tên môn không hợp lệ");
                        alert.show();
                    }
                        break;
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
                monHoc.clear();
            }
        });
    }
    public void setItemsForChoiceBox(String tenMon) {
        List<DiemLopHP> danhSachLopHP = service.layDanhSachLopHPTheoMon(tenMon);

        // Xóa items cũ của ChoiceBox (nếu có)
        lop.getItems().clear();

        // Thêm items mới vào ChoiceBox
        for (DiemLopHP diemLopHP : danhSachLopHP) {
            lop.getItems().add(diemLopHP.getTenLopMonHoc());
        }
    }
}
