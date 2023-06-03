package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.ThemSinhVienController;
import huce.cntt.oop.doan.entities.GiangVien;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoadThemSinhVien {
    private static String path = "/view/ThemSinhVien.fxml";

    public static Scene loadThemSinhVien(Stage stage, GiangVien giangVien) { 
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(path));
            ThemSinhVienController homeController = new ThemSinhVienController(stage, giangVien);
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
