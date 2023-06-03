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
    private static String path = "/view/TrangChu.fxml";

    public static Scene loadTrangChu(Stage stage, VaiTro vaiTro, GiangVien giangVien) { 
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(path));
            TrangChuController trangChuController = new TrangChuController(vaiTro, giangVien, stage);
            loader.setController(trangChuController);

            // CSS
        
            Pane root = loader.load();
            return new Scene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
