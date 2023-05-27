package huce.cntt.oop.doan;

import java.io.IOException;

import huce.cntt.oop.doan.loader.LoadDiemCaNhan;
import huce.cntt.oop.doan.loader.LoadSinhVien;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene QLsinhVien = LoadSinhVien.loadSinhVien();
        primaryStage.setScene(QLsinhVien);

        Scene QLdiemCaNhan = LoadDiemCaNhan.loadDiemCaNhan();
        primaryStage.setScene(QLdiemCaNhan);
        
        primaryStage.setTitle("HUCE student management system");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}