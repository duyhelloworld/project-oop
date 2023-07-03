package huce.cntt.oop.doan.loader;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.TrangChuController;
import huce.cntt.oop.doan.entities.GiangVien;
import huce.cntt.oop.doan.entities.VaiTro;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoadTrangChu {
    private static String fxmlPath = "/view/TrangChu.fxml";
    private static String cssPath = "/view/css/TrangChu.css";

    public static Scene loadTrangChu(Stage stage, VaiTro vaiTro, GiangVien giangVien) { 
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
            TrangChuController trangChuController = new TrangChuController(vaiTro, giangVien, stage);
            loader.setController(trangChuController);            
        
            Pane root = loader.load();
            Scene scene =  new Scene(root);
            scene.getStylesheets().add(App.class.getResource(cssPath).toExternalForm());
            return scene;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
