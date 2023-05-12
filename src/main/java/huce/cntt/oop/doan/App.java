package huce.cntt.oop.doan;

import java.io.IOException;

import huce.cntt.oop.doan.controller.QuanLiMH_Ctl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        // String fxmlPath = "/huce/cntt/oop/doan/bang_quanli_monhoc_locTim.fxml";
        // String fxmlPath = "/huce/cntt/oop/doan/profile_sinhvien.fxml";
        String fxmlPath = "/huce/cntt/oop/doan/bang_quanli_monhoc.fxml";
        // String fxmlPath = "/huce/cntt/oop/doan/test.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        String cssFilePath = getClass().getResource("/huce/cntt/oop/doan/bang_quanli_monhoc.css").toExternalForm();
        
        QuanLiMH_Ctl ctl = new QuanLiMH_Ctl();
        loader.setController(ctl);

        Pane root = loader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(cssFilePath);
         
        primaryStage.setScene(scene);
        primaryStage.setTitle("HUCE student management system");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}