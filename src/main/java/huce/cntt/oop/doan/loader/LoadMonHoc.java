package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.MonHocController;
import huce.cntt.oop.doan.entities.GiangVien;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoadMonHoc {
    private static String path = "/view/MonHoc.fxml";

    public static Scene loadMonHoc(Stage stage, GiangVien giangVien) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(path));
        
            MonHocController controller = new MonHocController(stage, giangVien);
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
