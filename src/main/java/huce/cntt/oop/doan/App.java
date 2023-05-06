package huce.cntt.oop.doan;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    
    private Scene bangQuanLiMonHoc, bangQuanLiMonHoc_Loc;
    private Stage stageChinh, stagePhu;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // primaryStage = stageChinh;

        // String fxmlPath = "/huce/cntt/oop/doan/bang_quanli_monhoc_locTim.fxml";
        String fxmlPath = "/huce/cntt/oop/doan/profile_sinhvien.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));

        Pane root = loader.load();
        // bangQuanLiMonHoc = new Scene(root);
        // primaryStage.setScene(bangQuanLiMonHoc);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HUCE student management system");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}