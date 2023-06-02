package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.SinhVienController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoadSinhVien {
    private static String path = "/view/SinhVien.fxml";

    public static Scene loadSinhVien(Stage stage) { 
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(path));
            SinhVienController controller = new SinhVienController(stage);
            loader.setController(controller);

            // CSS
            
            Pane root = loader.load();
            return new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
