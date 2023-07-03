package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.HomeSinhVienController;
import huce.cntt.oop.doan.entities.GiangVien;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoadSinhVien {
    private static String path = "/view/HomeSinhVien.fxml";

    public static Scene loadSinhVien(Stage stage, GiangVien giangVien) { 
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(path));
            HomeSinhVienController homeController = new HomeSinhVienController(stage, giangVien);
            loader.setController(homeController);
            
            // CSS
            
            Pane root = loader.load();
            return new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
