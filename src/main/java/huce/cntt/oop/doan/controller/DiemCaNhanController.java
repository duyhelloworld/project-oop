package huce.cntt.oop.doan.controller;

import java.util.List;
import java.util.regex.Pattern;

import huce.cntt.oop.doan.entities.DiemCaNhan;
import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.interfaces.IDiemCaNhanService;
import huce.cntt.oop.doan.interfaces.ISinhVienService;
import huce.cntt.oop.doan.service.DiemCaNhanService;
import huce.cntt.oop.doan.service.SinhVienService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

public class DiemCaNhanController {
    private IDiemCaNhanService diemCaNhanService;
    private ISinhVienService sinhVienService;

    public DiemCaNhanController() {
        this.diemCaNhanService = DiemCaNhanService.getInstance();
        this.sinhVienService = SinhVienService.getInstance();
    }

    public DiemCaNhanController(IDiemCaNhanService diemCaNhanService, ISinhVienService sinhVienService){
        this.diemCaNhanService = diemCaNhanService;
        this.sinhVienService = sinhVienService;
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
    private Button nutLoadLai;
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

    private Alert alert;
    private ObservableList<DiemCaNhan> data;

    @FXML
    public void initialize() {
        alert = new Alert(AlertType.NONE);
        data = FXCollections.observableArrayList();
        // Khởi tạo thì lấy điểm cá nhân của mọi sinh viên
        data.addAll(diemCaNhanService.layTatCaDiemCaNhan());

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

        nutLoadLai.setOnAction(e -> {
            if (!nutLoadLai.isPressed()) {
                data.clear();
                data.addAll(diemCaNhanService.layTatCaDiemCaNhan());
                hoTenTextField.clear();
                khoaTextField.clear();
                lopQuanLiTextField.clear();
                maSVTextField.clear();
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
                throw new Exception("Không tồn tại sinh viên này!");
            }
            hoTenTextField.setText(sinhVien.getHoTen().toString());
            khoaTextField.setText(sinhVien.getKhoa());
            lopQuanLiTextField.setText(sinhVien.getTenLopQuanLi());
            List<DiemCaNhan> listDCN = diemCaNhanService.layDiemCaNhanTheoMaSo(mssv);
            data.clear();
            data.setAll(listDCN);
        } catch (Exception e) {
            e.printStackTrace();
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    private Integer layMaSoSV(){
        String string = maSVTextField.getText();
        if (string.isBlank()) {
            return null;
        }
        try {
            if (Pattern.matches("^\\d+$", string)) {
                Integer mssv = Integer.parseInt(string);
                return mssv;
            }
        } catch (Exception e) {
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
}