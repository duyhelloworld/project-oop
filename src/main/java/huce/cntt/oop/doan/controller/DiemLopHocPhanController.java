package huce.cntt.oop.doan.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import huce.cntt.oop.doan.entities.DiemLopHP;
import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.entities.VaiTro;
import huce.cntt.oop.doan.loader.LoadTrangChu;
import huce.cntt.oop.doan.service.DiemLopHPService;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DiemLopHocPhanController {
    private Stage stage;
    private GiangVien giangVien;
    private final DiemLopHPService service = DiemLopHPService.getInstance();

    public DiemLopHocPhanController(Stage stage, GiangVien giangVien){
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
    private TableColumn<DiemLopHP, String> cotLopMH;
    @FXML
    private TableColumn<DiemLopHP, Float> cotDiemChuyenCan;
    @FXML
    private TableColumn<DiemLopHP, Float> cotDiemGK;
    @FXML
    private TableColumn<DiemLopHP, String> cotDiemQT;
    @FXML
    private TableColumn<DiemLopHP, Float> cotDiemKT;
    @FXML
    private TableColumn<DiemLopHP, String> cotDiemTK;
    @FXML
    private TableColumn<DiemLopHP, String> cotGPA;
    @FXML
    private TableColumn<DiemLopHP, String> cotDiemChu;
    @FXML
    private TableColumn<DiemLopHP, Integer> cotMaLopHP;
    @FXML
    private TableColumn<DiemLopHP, Integer> cotHocKy;
    // @FXML
    // private TableColumn<DiemLopHP, > cot;
    @FXML
    private ComboBox<String> timKiemTheo;
    @FXML
    private ChoiceBox<String> lop;
    @FXML
    private TextField monHoc;
    @FXML
    private TextField hocKy;
    @FXML
    private Button xoa;
    @FXML
    private Button luuThongTin;
    @FXML
    private Button thoat;
    @FXML
    private TextField hoTenTextField;
    @FXML
    private TextField lopQLTextField;
    @FXML
    private TextField diemChuyenCanTextField;
    @FXML
    private TextField diemGiuaKiTextField;
    @FXML
    private TextField diemKetThucTextField;
    @FXML
    private TextField diemQTTextField;
    @FXML
    private TextField diemTongKetTextField;
    @FXML
    private TextField diemHe4TextField;
    @FXML
    private TextField diemChuTextField;

    private ObservableList<DiemLopHP> observableList;

    @FXML
    public void initialize() {
        timKiemTheo.getItems().addAll("Mã môn", "Tên môn");
        lop.getItems().addAll();
        cotMSSV.setCellValueFactory(new PropertyValueFactory<>("MSSV"));
        cotHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        cotLopMH.setCellValueFactory(new PropertyValueFactory<>("tenLopMonHoc"));
        cotDiemChuyenCan.setCellValueFactory(new PropertyValueFactory<>("diemChuyenCan"));
        cotDiemGK.setCellValueFactory(new PropertyValueFactory<>("diemGiuaKi"));
        cotDiemQT.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDiemQuaTrinh().toString()));
        cotDiemKT.setCellValueFactory(new PropertyValueFactory<>("diemCuoiKi"));
        cotDiemTK.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDiemTongKet().toString()));
        cotGPA.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDiemHe4().toString()));
        cotDiemChu.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDiemChu()));
        cotMaLopHP.setCellValueFactory(new PropertyValueFactory<>("maLopHP"));
        cotHocKy.setCellValueFactory(new PropertyValueFactory<>("hocKy"));
        
        List<DiemLopHP> diem = service.layTatCaDiem();
        observableList = FXCollections.observableArrayList();
        observableList.addAll(diem);
        tableView.setItems(observableList);

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                DiemLopHP selectedDiemLopHP = tableView.getSelectionModel().getSelectedItem();
                if (selectedDiemLopHP != null) {
                    // Đổ dữ liệu từ đối tượng được chọn vào các TextField
                    hoTenTextField.setText(selectedDiemLopHP.getHoTen());
                    diemChuTextField.setText(selectedDiemLopHP.getDiemChu());
                    diemChuyenCanTextField.setText(Float.toString(selectedDiemLopHP.getDiemChuyenCan()));
                    diemGiuaKiTextField.setText(Float.toString(selectedDiemLopHP.getDiemGiuaKi()));
                    diemHe4TextField.setText(Float.toString(selectedDiemLopHP.getDiemHe4()));
                    diemKetThucTextField.setText(Float.toString(selectedDiemLopHP.getDiemCuoiKi()));
                    diemQTTextField.setText(Float.toString(selectedDiemLopHP.getDiemQuaTrinh()));
                    diemTongKetTextField.setText(Float.toString(selectedDiemLopHP.getDiemTongKet()));
                    lopQLTextField.setText(selectedDiemLopHP.getTenLopMonHoc());
                }
            }
        });

        xoa.setOnAction(e -> xoa());
        luuThongTin.setOnAction(e -> {
            try {
                luu();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        thoat.setOnAction(e -> {
            if (!thoat.isPressed()) {
                thoat();
            }
        });
    }

    public void setItemsForChoiceBox(String tenMon) {
        List<DiemLopHP> danhSachLopHP = service.layDanhSachLopHPTheoMon(tenMon);

        lop.getItems().clear();

        for (DiemLopHP diemLopHP : danhSachLopHP) {
            lop.getItems().add(diemLopHP.getTenLopMonHoc());
        }
    }

    private void thoat() {
        Scene trangChu = LoadTrangChu.loadTrangChu(stage, VaiTro.GIANGVIEN, giangVien);
        stage.setScene(trangChu);
        stage.show();
    }

    private void luu() throws SQLException {
        DiemLopHP diem = tableView.getSelectionModel().getSelectedItem();
        if (diem == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận lưu thay đổi");
        alert.setHeaderText(null);
        alert.setContentText("Xác nhận lưu thay đổi điểm này?");

        Optional<ButtonType> confirm = alert.showAndWait();

        if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
            try {
                float diemChuyenCan = Float.parseFloat(diemChuyenCanTextField.getText());
                float diemGiuaKi = Float.parseFloat(diemGiuaKiTextField.getText());
                float diemCuoiKi = Float.parseFloat(diemKetThucTextField.getText());
                service.capNhatDiemLopHP(diem.getMaLopHP(), diem.getMSSV(), diemChuyenCan, diemGiuaKi, diemCuoiKi);

                diem.setDiemChuyenCan(diemChuyenCan);
                diem.setDiemGiuaKi(diemGiuaKi);
                diem.setDiemCuoiKi(diemCuoiKi);

                alert.setAlertType(AlertType.INFORMATION);
                alert.setContentText("Lưu dữ liệu thành công!");
                alert.show();
                // Cập nhật dữ liệu trong TableView
                tableView.refresh();

                System.out.println("Lưu dữ liệu thành công!");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Nhập không hợp lệ cho điểm!");
                e.printStackTrace();
            }
        }
    }

    private void xoa() {
        DiemLopHP diem = tableView.getSelectionModel().getSelectedItem();

        if (diem == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText(null);
        alert.setContentText("Xác nhận xóa điểm này?");

        Optional<ButtonType> confirm = alert.showAndWait();

        if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
            try {
                service.xoa(diem.getMaLopHP(), diem.getMSSV());
                diem.setDiemChuyenCan(0f);
                diem.setDiemGiuaKi(0f);
                diem.setDiemCuoiKi(0f);

                    // Hiển thị thông báo xóa thành công
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Thành công");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Xóa thành công");
                    successAlert.show();
                    tableView.refresh();
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
