package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.DiemLopHPController;
import huce.cntt.oop.doan.entities.GiangVien;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoadDiemHP {
    private static String path = "/view/DiemLopHP.fxml";

    public static Scene loadDiemHP(Stage stage, GiangVien giangVien) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(path));
    
        DiemLopHPController controller = new DiemLopHPController(stage, giangVien);
        loader.setController(controller);

        // CSS
    
        Pane root = loader.load();
    
        return new Scene(root);
    }
}
