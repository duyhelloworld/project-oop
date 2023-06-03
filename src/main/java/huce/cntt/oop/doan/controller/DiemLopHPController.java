package huce.cntt.oop.doan.controller;

import java.util.List;
import java.util.Optional;

import huce.cntt.oop.doan.entities.DiemLopHP;
import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.entities.VaiTro;
import huce.cntt.oop.doan.loader.LoadTrangChu;
import huce.cntt.oop.doan.service.DiemLopHPService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DiemLopHPController {
    private Stage stage;
    private GiangVien giangVien;

    public DiemLopHPController(Stage stage, GiangVien giangVien){
        this.stage = stage;
        this.giangVien = giangVien;
    }

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
    
    @FXML
    public void initialize(){
        timKiemTheo.getItems().addAll("Mã môn", "Tên môn");
        lop.getItems().addAll();
        cotMSSV.setCellValueFactory(new PropertyValueFactory<>("mssv"));
        cotHoTen.setCellValueFactory(new PropertyValueFactory<>("ho_ten"));
        cotLopQL.setCellValueFactory(new PropertyValueFactory<>("ten_lop_quan_li"));
        cotDiemChuyenCan.setCellValueFactory(new PropertyValueFactory<>("diem_chuyen_can"));
        cotDiemGK.setCellValueFactory(new PropertyValueFactory<>("diem_giua_ki"));
        cotDiemQT.setCellValueFactory(new PropertyValueFactory<>("diem_qt"));
        cotKT.setCellValueFactory(new PropertyValueFactory<>("diem_cuoi_ki"));
        cotGPA.setCellValueFactory(new PropertyValueFactory<>("diem_he_so_4"));
        cotDiemChu.setCellValueFactory(new PropertyValueFactory<>("diem_chu"));

        List<DiemLopHP> diem = service.layTatCaDiem();
        observableList = FXCollections.observableArrayList();
        observableList.addAll(diem);
        tableView.setItems(observableList);
        // monHoc.setOnKeyPressed(event -> {
        //     if (event.getCode() == KeyCode.ENTER) {
        //         String searchOption = timKiemTheo.getValue();
        //         String searchText = monHoc.getText();
        //         String searchOption2 = lop.getValue();

        //         List<DiemLopHP> searchResults = new ArrayList<DiemLopHP>();

        //         switch (searchOption) {
        //             case "Mã môn":
        //                 try {
        //                     int maMon = Integer.parseInt(searchText);
        //                     searchResults = service.layDiemLopHPTheoMaMon(maMon);
        //                 } catch (NumberFormatException e) {
        //                     // Hiển thị thông báo lỗi nếu mã môn không hợp lệ
        //                     Alert alert = new Alert(Alert.AlertType.ERROR);
        //                     alert.setTitle("Lỗi");
        //                     alert.setHeaderText(null);
        //                     alert.setContentText("Mã môn không hợp lệ");
        //                     alert.show();
        //                 }
        //                 break;
        //             case "Tên môn":
        //             try {
        //                 searchResults = service.layDiemLopHPTheoTenMon(searchText, searchOption2);
        //             } catch (Exception e) {
        //                 e.printStackTrace();
        //                 // Hiển thị thông báo lỗi nếu tên môn không hợp lệ
        //                 Alert alert = new Alert(Alert.AlertType.ERROR);
        //                 alert.setTitle("Lỗi");
        //                 alert.setContentText("Tên môn không hợp lệ");
        //                 alert.show();
        //             }
        //                 break;
        //         // Xóa nội dung của TableView trước khi hiển thị kết quả tìm kiếm
        //         tableView.getItems().clear();

        //         if (!searchResults.isEmpty()) {
        //             observableList.clear();
        //             observableList.setAll(searchResults);
        //         } else {
        //             // Hiển thị thông báo nếu không tìm thấy kết quả
        //             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //             alert.setTitle("Thông báo");
        //             alert.setHeaderText(null);
        //             alert.setContentText("Không tìm thấy kết quả");
        //             alert.show();
        //         }

        //         // Xóa nội dung của TextField
        //         monHoc.clear();
        //     }
        // });
        xoa.setOnAction(e -> xoa());

        thoat.setOnAction(e -> {
            if (!thoat.isPressed()) {
                thoat();
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

    private void thoat() {
        Scene trangChu = LoadTrangChu.loadTrangChu(stage, VaiTro.GIANGVIEN, giangVien);
        stage.setScene(trangChu);
        stage.show();
    }

    private void xoa() {
        DiemLopHP diem = tableView.getSelectionModel().getSelectedItem();

        if (diem == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText(null);
        alert.setContentText("Xác nhận xóa điểm?");

        Optional<ButtonType> confirm = alert.showAndWait();

        if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
            try {
                boolean xoaThanhCong = service.xoa(diem.getMaLopHP(), diem.getMSSV());

                if (xoaThanhCong) {
                    tableView.getItems().remove(diem);
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
}
