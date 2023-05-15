package huce.cntt.oop.doan;

import java.io.IOException;

import huce.cntt.oop.doan.controller.QuanLiSinhVienController;
import huce.cntt.oop.doan.service.SinhVienService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        String fxmlPath = "/huce/cntt/oop/doan/SinhVien.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        
        SinhVienService sinhVienService = new SinhVienService();
        QuanLiSinhVienController quanLiSinhVienController = new QuanLiSinhVienController(sinhVienService);
        loader.setController(quanLiSinhVienController);

        // LoginController loginController = new LoginController();
        // loader.setController(loginController);
        
        Pane root = loader.load();
        
        Scene scene = new Scene(root);
        // String cssFilePath = getClass().getResource("/huce/cntt/oop/doan/bang_quanli_monhoc.css").toExternalForm();
        // scene.getStylesheets().add(cssFilePath);
         
        primaryStage.setScene(scene);
        primaryStage.setTitle("HUCE student management system");
        primaryStage.show();
    }

    public static void main(String[] args) {
        // SinhVien sv = new SinhVien(new HoTen("Alex Ferguson"), LocalDate.of(1965, 12, 12), true, new DiaChi("1, Dark Street, London"), new DiaChi("Manchester"), "0123456789", LocalDate.now(), "IT");
        launch();
    }

}