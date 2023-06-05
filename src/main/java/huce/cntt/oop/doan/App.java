package huce.cntt.oop.doan;

import java.io.IOException;

import huce.cntt.oop.doan.entities.SinhVien;
import huce.cntt.oop.doan.loader.LoadLogin;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        Scene login = LoadLogin.loadLoginScreen(primaryStage);
        primaryStage.setScene(login);

        primaryStage.setTitle("HUCE app");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
        SinhVien sinhVien = new SinhVien();
        try {
            sinhVien.setDiaChiThuongTru("1, 1, Hà Nam");
            System.out.println(sinhVien);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}