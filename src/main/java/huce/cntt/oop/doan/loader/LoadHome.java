package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.TrangChuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LoadHome {
    private static String path = "/view/TrangChu.fxml";

    public static Scene loadTrangChu() throws IOException { 
        FXMLLoader loader = new FXMLLoader(App.class.getResource(path));
        TrangChuController controller = new TrangChuController();
        loader.setController(controller);

        // CSS
        
        Pane root = loader.load();
        return new Scene(root);
    }
}
