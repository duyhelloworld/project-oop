package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.DiemCaNhanController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LoadDiemCaNhan {
    private static String path = "/view/DiemCaNhan.fxml";

    public static Scene loadDiemCaNhan() throws IOException { 
        FXMLLoader loader = new FXMLLoader(App.class.getResource(path));
        DiemCaNhanController controller = new DiemCaNhanController();
        loader.setController(controller);

        // CSS
        
        Pane root = loader.load();
        return new Scene(root);
    }
}
