package huce.cntt.oop.doan.controller;

import java.util.List;

import huce.cntt.oop.doan.entities.DiemCaNhan;
import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.VaiTro;
import huce.cntt.oop.doan.entities.exception.DatabaseException;
import huce.cntt.oop.doan.interfaces.IDiemCaNhanService;
import huce.cntt.oop.doan.interfaces.ISinhVienService;
import huce.cntt.oop.doan.loader.LoadTrangChu;
import huce.cntt.oop.doan.service.DiemCaNhanService;
import huce.cntt.oop.doan.service.SinhVienService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class DiemCaNhanController {
    private IDiemCaNhanService diemCaNhanService = DiemCaNhanService.getInstance();
    private ISinhVienService sinhVienService = SinhVienService.getInstance();
    private Stage stage;
    private GiangVien giangVien;

    public DiemCaNhanController(Stage stage, GiangVien giangVien) {
        this.stage = stage;
        this.giangVien = giangVien;
    }

    @FXML
    private TextField hoTenTextField;
    @FXML
    private TextField maSVTextField;
    @FXML
    private TextField khoaTextField;
    @FXML
    private TextField diemTBTextField;
    @FXML
    private TextField diemTBhe4TextField;
    @FXML
    private TextField diemTBTLTextField;
    @FXML
    private TextField diemTBTLhe4TextField;
    @FXML
    private TextField hocLucTextField;
    @FXML
    private TextField hocLucTLTextField;
    @FXML
    private Button nutRefresh;
    @FXML
    private Button nutQuayLai;
    @FXML
    private TextField lopQuanLiTextField;
    @FXML 
    private Button nutXemDiem;
    @FXML
    private TableView<DiemCaNhan> bangDiemCaNhan;
    @FXML
    private TableColumn<DiemCaNhan, Integer> cotMaMon;
    @FXML
    private TableColumn<DiemCaNhan, String> cotTenMon;
    @FXML
    private TableColumn<DiemCaNhan, String> cotTenLopMon;
    @FXML
    private TableColumn<DiemCaNhan, Integer> cotSoTinChi;
    @FXML
    private TableColumn<DiemCaNhan, Float> cotDiemChuyenCan;
    @FXML
    private TableColumn<DiemCaNhan, Float> cotDiemQuaTrinh;
    @FXML
    private TableColumn<DiemCaNhan, Float> cotDiemGiuaKi;  
    @FXML
    private TableColumn<DiemCaNhan, Float> cotDiemCuoiKi;
    @FXML
    private TableColumn<DiemCaNhan, Float> cotDiemTongKet;
    @FXML
    private TableColumn<DiemCaNhan, Float> cotDiemTongKetHe4;
    @FXML
    private TableColumn<DiemCaNhan, String> cotDiemChu;
    @FXML
    private Label hocKi;

    private Alert alert;
    private ObservableList<DiemCaNhan> data;

    @FXML
    public void initialize() {
        alert = new Alert(AlertType.INFORMATION);
        data = FXCollections.observableArrayList();

        showBangDiemCaNhan();
        renderDiemTrungBinhTichLuy();
        nutXemDiem.setOnAction(e -> {
            if (!nutXemDiem.isPressed()) {
                timKiem();
                renderDiemTrungBinhTichLuy();
            }
        });

        maSVTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                timKiem();
                renderDiemTrungBinhTichLuy();
            }
        });

        nutRefresh.setOnAction(e -> {
            if (!nutRefresh.isPressed()) {
                data.clear();
                hoTenTextField.clear();
                khoaTextField.clear();
                lopQuanLiTextField.clear();
                maSVTextField.clear();
            }
        });

        nutQuayLai.setOnAction(e -> {
            if (!nutQuayLai.isPressed()) {
                quayLaiHome();
            }
        });
        layScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                quayLaiHome();
            }
        });

        hoTenTextField.setEditable(false);
        khoaTextField.setEditable(false);
        lopQuanLiTextField.setEditable(false);
        diemTBTextField.setEditable(false);
        diemTBTLTextField.setEditable(false);
        diemTBhe4TextField.setEditable(false);
        diemTBTLhe4TextField.setEditable(false);
        hocLucTextField.setEditable(false);
        hocLucTLTextField.setEditable(false);
    }

    private void showBangDiemCaNhan(){
        cotMaMon.setCellValueFactory(new PropertyValueFactory<DiemCaNhan, Integer>("maMon"));
        cotTenMon.setCellValueFactory(new PropertyValueFactory<DiemCaNhan, String>("tenMon"));
        cotTenLopMon.setCellValueFactory(new PropertyValueFactory<DiemCaNhan, String>("tenLopMon"));
        cotSoTinChi.setCellValueFactory(new PropertyValueFactory<DiemCaNhan, Integer>("soTinChi"));
        cotDiemChuyenCan.setCellValueFactory(new PropertyValueFactory<DiemCaNhan, Float>("diemChuyenCan"));
        cotDiemGiuaKi.setCellValueFactory(new PropertyValueFactory<DiemCaNhan, Float>("diemGiuaKi"));
        cotDiemCuoiKi.setCellValueFactory(new PropertyValueFactory<DiemCaNhan, Float>("diemCuoiKi"));
        cotDiemTongKet.setCellValueFactory(new PropertyValueFactory<DiemCaNhan, Float>("diemTongKet"));
        cotDiemTongKetHe4.setCellValueFactory(new PropertyValueFactory<DiemCaNhan, Float>("diemTongKetHe4"));
        cotDiemChu.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiemChuString()));
        bangDiemCaNhan.setItems(data);

        bangDiemCaNhan.setRowFactory(tv -> {
            TableRow<DiemCaNhan> row = new TableRow<>();
            row.setOnMouseClicked(ev -> {
                if (ev.getClickCount() == 1 && !row.isEmpty()) {
                    DiemCaNhan diemCaNhan = row.getItem();
                    renderDiemTrungBinhMon(diemCaNhan);
                }
            });
            return row;
        });
    }

    private void timKiem(){
        Integer mssv = layMaSoSV();
        if (mssv == null) {
            return;
        }
        try {
            SinhVien sinhVien = sinhVienService.timKiemSinhVienTheoMaSo(mssv);
            if (sinhVien == null) {
                throw new IllegalArgumentException("Không tồn tại sinh viên này!");
            }
            hoTenTextField.setText(sinhVien.getHoTen().toString());
            khoaTextField.setText(sinhVien.getKhoa());
            lopQuanLiTextField.setText(sinhVien.getTenLopQuanLi());
            List<DiemCaNhan> listDCN = diemCaNhanService.layDiemCaNhanTheoMaSo(mssv);
            data.clear();
            data.setAll(listDCN);
        } catch (IllegalArgumentException |DatabaseException  e) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (NullPointerException e) {
            e.printStackTrace();
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Có lỗi xảy ra!");
            alert.show();
        }
    }

    private Integer layMaSoSV() {
        String string = maSVTextField.getText();
        if (string.isBlank()) {
            return null;
        }
        try {
            Integer mssv = Integer.parseInt(string);
            return mssv;
        } catch (NumberFormatException e) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Mã số sai định dạng!!!");
            alert.show();
        }
        return null;
    }

    private void renderDiemTrungBinhMon(DiemCaNhan diemCaNhan) {
        if (diemCaNhan == null) {
            return;
        }
        Float diemTb = diemCaNhan.getDiemTongKet();
        diemTBTextField.setText(diemTb.toString());
        diemTBhe4TextField.setText(diemCaNhan.getDiemTongKetHe4().toString());

        hocLucTextField.setText(xepLoaiHocLuc(diemTb));
    }

    private void renderDiemTrungBinhTichLuy() {
        if (data.isEmpty()) {
            return;
        }

        Float diemTBTL = 0f;
        for (DiemCaNhan diemCaNhan : data) {
            diemTBTL += diemCaNhan.getDiemTongKet();
        }
        diemTBTL = diemTBTL / data.size();
        diemTBTLTextField.setText(String.format("%.1f", diemTBTL));
        Float diemTBTLHe4 = diemTBTL * 0.2f;
        diemTBTLhe4TextField.setText(String.format("%.1f", diemTBTLHe4));

        hocLucTLTextField.setText(xepLoaiHocLuc(diemTBTL));
    }

    private String xepLoaiHocLuc(Float diem) {
        if (diem >= 8.5f) {
            return "Xuất sắc";
        } else if (diem >= 7.5f) {
            return "Giỏi";
        } else if (diem >= 6.5f) {
            return "Khá";
        } else if (diem >= 5.5f) {
            return "Trung bình";
        } else if (diem >= 4.0f) {
            return "Yếu";
        } else {
            return "Kém";
        }
    }

    private Scene layScene(){
        return LoadTrangChu.loadTrangChu(stage, VaiTro.NVDT, giangVien);
    }

    private void quayLaiHome() {
        Scene home = layScene();
        stage.setScene(home);
        stage.show();
    }
}