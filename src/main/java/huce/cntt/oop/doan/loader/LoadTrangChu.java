package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.TrangChuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LoadTrangChu {
    private static String path = "/view/TrangChu.fxml";
    private static TrangChuController controller;

    public static Scene loadTrangChu() { 
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(path));
            controller = new TrangChuController();
            loader.setController(controller);

            // CSS
        
            Pane root = loader.load();
            return new Scene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
