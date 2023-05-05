package huce.cntt.oop.doan;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application implements EventHandler<ActionEvent> {
    
    private static Scene scene;
    private Button btn1;

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btn1) {
            btn1.setText("Ohhh, you clicked!");
            
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        btn1 = new Button("Click me!");

        StackPane simpleLayout = new StackPane();
        simpleLayout.getChildren().add(btn1);
        scene = new Scene(simpleLayout, 780, 560);

        stage.setTitle("HUCE student management system");
        stage.setScene(scene);
        stage.show();
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