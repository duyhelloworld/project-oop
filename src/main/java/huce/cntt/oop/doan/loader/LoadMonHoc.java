package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LoadMonHoc {
    private static String path = "/view/SinhVien.fxml";

    public static Scene loadMonHoc() { 
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(path));
            // CSS
            
            Pane root = loader.load();
            return new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
