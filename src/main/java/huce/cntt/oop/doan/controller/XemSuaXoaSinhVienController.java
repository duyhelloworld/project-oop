package huce.cntt.oop.doan.controller;

import java.time.LocalDate;
import java.util.Optional;

import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.interfaces.ISinhVienService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class XemSuaXoaSinhVienController extends TableView<SinhVien> {

    private final ISinhVienService sinhVienService;
    // private final ILopService lopService;
    // private final IKhoaService khoaService;

    public XemSuaXoaSinhVienController(ISinhVienService sinhVienService){
        this.sinhVienService = sinhVienService;
        // this.lopService = new LopService();
        // this.khoaService = new KhoaService();
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
    private TableColumn<SinhVien, Boolean> cotGioiTinh;
    @FXML
    private TableColumn<SinhVien, String> cotQueQuan;
    @FXML
    private TableColumn<SinhVien, String> cotDiaChiThuongTru;
    @FXML
    private TableColumn<SinhVien, String> cotKhoa;
    @FXML
    private TableColumn<SinhVien, String> cotEmail;
    @FXML
    private TableColumn<SinhVien, Integer> cotNienKhoa;
    @FXML
    private TableColumn<SinhVien, String> cotLopQuanLi;
    @FXML
    private TableColumn<SinhVien, CheckBox> cotXoa;

    private ObservableList<SinhVien> data;

    @FXML
    public void initialize(){
        cotMaSoSV.setCellValueFactory(new PropertyValueFactory<SinhVien, Integer>("maSo"));  
        cotHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        cotNgaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        cotGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        cotQueQuan.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
        cotDiaChiThuongTru.setCellValueFactory(new PropertyValueFactory<>("diaChiThuongTru"));
        cotKhoa.setCellValueFactory(new PropertyValueFactory<>("khoa"));
        cotEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cotNienKhoa.setCellValueFactory(new PropertyValueFactory<>("nienKhoa"));
        cotLopQuanLi.setCellValueFactory(new PropertyValueFactory<>("tenLopQuanLi"));
        cotMaSoSV.setEditable(false);

        data = loadDanhSachSinhVien();
        bangSinhVien.setItems(data);

        bangSinhVien.setRowFactory(tableView -> {
            TableRow<SinhVien> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                if (e.getClickCount() == 1 && !row.isEmpty()) {
                    alert.setContentText("Bạn có muốn thay đổi sinh viên này?");
                    Optional<ButtonType> confirm = alert.showAndWait();
                    if (!alert.isShowing()) {
                        if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                            SinhVien sv = row.getItem();
                            renderSinhVienKhiClick(sv);
                        }
                    }
                }                
            });
            return row;
        });
    }

    private ObservableList<SinhVien> loadDanhSachSinhVien(){
        ObservableList<SinhVien> danhSachSinhVien = FXCollections.observableList(sinhVienService.layTatCaSinhVien());
        return danhSachSinhVien;
    }

    private void renderSinhVienKhiClick(SinhVien sv){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Rendering " + sv.toString());
        alert.show();
    }
}
