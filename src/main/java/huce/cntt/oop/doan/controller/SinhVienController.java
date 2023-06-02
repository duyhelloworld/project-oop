package huce.cntt.oop.doan.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class SinhVienController {
    @FXML
    private Tab tabThemSinhVien;

    @FXML
    private Tab tabSuaXoaSinhVien;

    private Stage stage;
    public SinhVienController(){}

    public SinhVienController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        try {
            FXMLLoader tabThemSVLoader = new FXMLLoader(getClass().getResource("/view/ThemSinhVien.fxml"));
            FXMLLoader tabXemSVLoader = new FXMLLoader(getClass().getResource("/view/XemSuaXoaSinhVien.fxml"));
            tabThemSVLoader.load();
            tabXemSVLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
