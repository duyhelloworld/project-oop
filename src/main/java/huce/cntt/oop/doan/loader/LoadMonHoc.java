package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.QLMHController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LoadMonHoc {
    private static String path = "/view/MonHoc.fxml";

    public static Scene loadMonHoc() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(path));
    
        QLMHController controller = new QLMHController();
        loader.setController(controller);
        // SinhVienService sinhVienService = new SinhVienService();

        // XemSuaXoaSinhVienController xemSuaXoaSinhVienController = new XemSuaXoaSinhVienController(sinhVienService);
        // loader.setController(xemSuaXoaSinhVienController);

        // CSS
    
        Pane root = loader.load();
    
        return new Scene(root);
    }
}
