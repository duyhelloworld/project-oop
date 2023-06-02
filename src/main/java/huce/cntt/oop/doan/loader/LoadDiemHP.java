package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.DiemLopHPController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LoadDiemHP {
    private static String path = "/view/DiemLopHP.fxml";

    public static Scene loadDiemHP() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(path));
    
        DiemLopHPController controller = new DiemLopHPController();
        loader.setController(controller);
        // SinhVienService sinhVienService = new SinhVienService();

        // XemSuaXoaSinhVienController xemSuaXoaSinhVienController = new XemSuaXoaSinhVienController(sinhVienService);
        // loader.setController(xemSuaXoaSinhVienController);

        // CSS
    
        Pane root = loader.load();
    
        return new Scene(root);
    }
}