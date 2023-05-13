package huce.cntt.oop.doan.controller;

import java.time.LocalDate;
import java.util.List;

import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.entities.properties.DiaChi;
import huce.cntt.oop.doan.entities.properties.HoTen;
import huce.cntt.oop.doan.interfaces.ILopService;
import huce.cntt.oop.doan.interfaces.ISinhVienService;
import huce.cntt.oop.doan.service.KhoaService;
import huce.cntt.oop.doan.service.LopService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class QuanLiSinhVienController {

    private final ISinhVienService sinhVienService;
    private final ILopService lopService;
    private final KhoaService khoaService;

    public QuanLiSinhVienController(ISinhVienService sinhVienService) {
        this.sinhVienService = sinhVienService;
        this.lopService = new LopService();
        this.khoaService = new KhoaService();
    }

    @FXML
    private RadioButton nam;
    @FXML
    private RadioButton nu;

    @FXML
    private Button nutLuu;
    @FXML
    private Button nutQuayLai;

    @FXML
    private TextField hoTenTextField;
    @FXML
    private TextField queQuanTextField;   
    @FXML
    private TextField diaChiHienTaiTextField;
    @FXML
    private TextField soDienThoaiTextField;
    @FXML
    private DatePicker ngaySinhDatePicker;
    @FXML
    private DatePicker ngayVaoTruongDatePicker;
    @FXML
    private ComboBox<String> lopQuanLiComboBox;
    @FXML
    private ComboBox<String> khoaComboBox;


    @FXML
    private void initialize(){
        System.out.println("Inited");
        ToggleGroup namHayNu = new ToggleGroup();
        nam.setToggleGroup(namHayNu);
        nu.setToggleGroup(namHayNu);

        hoTenTextField.setText("hoten");
        queQuanTextField.setText("quequan");
        diaChiHienTaiTextField.setText("dcht");
        soDienThoaiTextField.setText("sdt");
        ngaySinhDatePicker.setPromptText("ngaySinh");
        ngayVaoTruongDatePicker.setPromptText("ngay vao truong");

        List<String> tenCacKhoa = khoaService.layTenTatCaKhoa();
        ObservableList<String> O_khoa = FXCollections.observableArrayList(tenCacKhoa);
        khoaComboBox.setItems(O_khoa);
        khoaComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldV, newV) -> {
            capNhatDanhSachLopNgaySauKhiChonKhoa(newV);
        });

        nutLuu.setOnAction(e -> themSinhVien());
    }

    // Tab1
    void themSinhVien(){ 
        SinhVien sinhVien = kiemTraDuLieu();
        if (sinhVien == null) {
            return;
        }
        sinhVienService.themMoiSinhVien(sinhVien);
    }

    void capNhatDanhSachLopNgaySauKhiChonKhoa(String tenKhoa){
        List<String> tenCacLopQuanLi = lopService.layTenTatCaLopQuanLiTheoKhoa(tenKhoa);
        ObservableList<String> O_lopQuanLi = FXCollections.observableArrayList(tenCacLopQuanLi);
        lopQuanLiComboBox.setItems(O_lopQuanLi);
    }

    // Tab2

    private SinhVien kiemTraDuLieu(){
        HoTen hoTen = new HoTen(hoTenTextField.getText());
        DiaChi queQuan = new DiaChi(queQuanTextField.getText());
        DiaChi diaChiHienTai = new DiaChi(diaChiHienTaiTextField.getText());
        LocalDate ngaySinh = ngaySinhDatePicker.getValue();
        LocalDate ngayVaoTruong = ngayVaoTruongDatePicker.getValue();
        String soDienThoai = soDienThoaiTextField.getText();

        Boolean gioiTinh = nam.isSelected();
        if (!gioiTinh) {
            gioiTinh = nu.isSelected();
            if (!gioiTinh) {
                try {
                    throw new IllegalAccessException("GioiTinh is not picked");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        String lopQuanLi = lopQuanLiComboBox.getValue();
        
        System.out.println(lopQuanLi);
        SinhVien sinhVien = new SinhVien(hoTen, ngaySinh, gioiTinh, queQuan, diaChiHienTai, soDienThoai , ngayVaoTruong, lopQuanLi);
        return sinhVien;
    }

}
