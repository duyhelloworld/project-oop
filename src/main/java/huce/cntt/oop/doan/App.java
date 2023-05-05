package huce.cntt.oop.doan;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    
    private Scene home, login;
    private Stage windowHello, primaryStage;

    // private Scene home(){
    //     StackPane pane = new StackPane();
    //     Label welcome = new Label("Welcome !");
    //     pane.getChildren().add(welcome);

    //     home = new Scene(pane, 400, 500);
    //     return home;
    // }

    private Scene login() {
        StackPane pane = new StackPane();
        Button loginBtn = new Button("Login Now");
        loginBtn.setOnAction(e -> {
            System.out.println("Logined in");
            primaryStage.setScene(home);
        });
        Label loginLabel = new Label("Login Page");
        pane.getChildren().addAll(loginLabel, loginBtn);

        login = new Scene(pane, 400, 500);
        return login;
    }   

    @Override
    public void start(Stage primaryStage) throws IOException {
        windowHello.setTitle("HUCE student management system");
        windowHello.setScene(login());
        
        primaryStage = windowHello;
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
        // DiaChi dc1 = new DiaChi("Trung Luong, Binh Luc, Ha Nam");
        // DiaChi dc2 = new DiaChi("9, Binh Luc, Ha Nam");
        // DiaChi dc3 = new DiaChi("9,1 , Binh Luc, Ha Nam");
        // DiaChi dc4 = new DiaChi("3,5 , Binh Luc, Ha Nam");

        // QuanLiSinhVienTheoLop ql = new QuanLiSinhVienTheoLop();
        // // List<Map<St\ring, String>> kq = ql.laySinhVienTheoLopQuanLi("66IT5");
        // List<Map<String, String>> kq = ql.laySinhVienTheoLopMonHoc("66ML1");
        // for (Map<String,String> sinhVien : kq) {
        // System.out.println(sinhVien.toString());
        // }
}