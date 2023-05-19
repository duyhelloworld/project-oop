package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.XemSuaXoaSinhVienController;
import huce.cntt.oop.doan.service.SinhVienService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LoadSinhVien {
    private static String path = "/huce/cntt/oop/doan/SinhVien.fxml";

    public static Scene loadSinhVien() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(path));
    
        SinhVienService sinhVienService = new SinhVienService();

        XemSuaXoaSinhVienController xemSuaXoaSinhVienController = new XemSuaXoaSinhVienController(sinhVienService);
        loader.setController(xemSuaXoaSinhVienController);

        // CSS
    
        Pane root = loader.load();
    
        return new Scene(root);
    }
}
