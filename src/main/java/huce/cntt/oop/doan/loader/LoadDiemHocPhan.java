package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.DiemLopHP;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LoadDiemHocPhan {
    private static String path = "/view/DiemLopHP.fxml";

    public static Scene loadDiemLopHP() { 
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(path));
            DiemLopHP controller = new DiemLopHP();
            loader.setController(controller);

            // CSS
            
            Pane root = loader.load();
            return new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}