package com.helloworld;

import java.util.List;
import java.util.Map;

import com.helloworld.service.QuanLiSinhVienTheoLop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        QuanLiSinhVienTheoLop ql = new QuanLiSinhVienTheoLop();
        // List<Map<String, String>> kq = ql.laySinhVienTheoLopQuanLi("66IT5");
        List<Map<String, String>> kq = ql.laySinhVienTheoLopMonHoc("66ML1");
        for (Map<String,String> sinhVien : kq) {
            System.out.println(sinhVien.toString());
        }
        // launch();
    }

    // public static void main(String[] args) {
    //     DiaChi dc1 = new DiaChi("Trung Luong, Binh Luc, Ha Nam");
    //     DiaChi dc2 = new DiaChi("9, Binh Luc, Ha Nam");
    //     DiaChi dc3 = new DiaChi("9,1 , Binh Luc, Ha Nam");
    //     DiaChi dc4 = new DiaChi("3,5 , Binh Luc, Ha Nam");
    // }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Started");
        System.out.println(primaryStage);
        
        /* Add elements */
        Button btn = new Button();
        btn.setText("Click me");
        
        /* Simple layout */
        // StackPane layout = new StackPane();
        // Scene scene = new Scene(layout, 400, 300);

        // ToolBar toolBar = new ToolBar();

        // List<Button> btn_news = new ArrayList<Button>();
        // btn_news.add(new Button("New file"));
        // btn_news.add(new Button("Open file"));
        // btn_news.add(new Button("Save"));
        // toolBar.getItems().addAll(btn_news);
        // toolBar.setLayoutX(0);
        // toolBar.setLayoutY(0);

        // Scene scene = new Scene(toolBar, 400, 300);


        // Button fileAccess = new Button("File");
        // toolBar.getItems().add(fileAccess);
        // fileAccess.setText("Preference");
        // toolBar.getItems().set(1, fileAccess);
        // VBox box = new VBox(toolBar);

        // Scene scene = new Scene(box, 400, 300);

        Rectangle box = new Rectangle(100, 200, Color.AZURE);
        Scene scene = new Scene(new Pane(box), 400, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello Mama");
        primaryStage.setResizable(true);
        
        primaryStage.show();
    }

}