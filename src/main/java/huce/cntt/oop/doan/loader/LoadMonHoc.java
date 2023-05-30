package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.service.SinhVienService;
import huce.cntt.oop.doan.controller.MonHocController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LoadMonHoc {
    private static String path = "/huce/cntt/oop/doan/QLMH.fxml";

    public static Scene loadMonHoc() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(path));
    
        // SinhVienService sinhVienService = new SinhVienService();

        // XemSuaXoaSinhVienController xemSuaXoaSinhVienController = new XemSuaXoaSinhVienController(sinhVienService);
        // loader.setController(xemSuaXoaSinhVienController);

        // CSS
    
        Pane root = loader.load();
    
        return new Scene(root);
    }
}
