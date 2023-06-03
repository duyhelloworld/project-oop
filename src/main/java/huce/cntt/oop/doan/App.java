package huce.cntt.oop.doan;

import java.io.IOException;

import huce.cntt.oop.doan.loader.LoadLogin;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        Scene login = LoadLogin.getScene(primaryStage);
        primaryStage.setScene(login);

        primaryStage.setTitle("HUCE app");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
        // System.out.println(MonHocService.getInstance().timKiemMonHocTheoTen("Lập trình C++"));
    }
}