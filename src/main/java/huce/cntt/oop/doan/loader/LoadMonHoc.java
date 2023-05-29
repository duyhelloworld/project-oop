package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.MonHocController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LoadMonHoc {
    private static String path = "/view/SinhVien.fxml";

    public static Scene loadMonHoc() throws IOException { 
        FXMLLoader loader = new FXMLLoader(App.class.getResource(path));
        MonHocController controller = new MonHocController();
        loader.setController(controller);

        // CSS
        
        Pane root = loader.load();
        return new Scene(root);
    }
}
