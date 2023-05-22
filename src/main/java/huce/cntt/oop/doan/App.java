package huce.cntt.oop.doan;

import java.io.IOException;

import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.loader.LoadSinhVien;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene home = LoadSinhVien.loadSinhVien();
        primaryStage.setScene(home);
        primaryStage.setTitle("HUCE student management system");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}