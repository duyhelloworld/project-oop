package huce.cntt.oop.doan.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {
    @FXML
    private Label footer;

    public void initialize(){
        footer.setText("Toàn bộ phần mềm được viết bởi @Team Tham Ăn\nAka Công ty TNHH 4 thành viên \"A+ Đồ Án\"!");
    }
}
