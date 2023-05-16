package huce.cntt.oop.doan;

import java.io.IOException;

import huce.cntt.oop.doan.controller.XemSuaXoaSinhVienController;
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
        
        // QuanLiSinhVienController quanLiSinhVienController = new QuanLiSinhVienController(sinhVienService);
        // loader.setController(quanLiSinhVienController);

        XemSuaXoaSinhVienController xemSuaXoaSinhVienController = new XemSuaXoaSinhVienController(sinhVienService);
        loader.setController(xemSuaXoaSinhVienController);

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
        launch();
    }

}