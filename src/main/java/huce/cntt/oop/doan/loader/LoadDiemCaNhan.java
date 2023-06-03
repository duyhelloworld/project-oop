package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.DiemCaNhanController;
import huce.cntt.oop.doan.entities.GiangVien;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoadDiemCaNhan {
    private static String path = "/view/DiemCaNhan.fxml";

    public static Scene loadDiemCaNhan(Stage stage, GiangVien giangVien) { 
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(path));
            DiemCaNhanController controller = new DiemCaNhanController(stage, giangVien);
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
