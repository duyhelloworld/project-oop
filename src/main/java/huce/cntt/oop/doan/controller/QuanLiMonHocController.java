package huce.cntt.oop.doan.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class QuanLiMonHocController {

    @FXML
    private Button nutLoc;

    @FXML
    private Button nutThem;

    @FXML
    private Button nutSua;

    @FXML
    private Button nutXoa;

    @FXML
    private void initialize(){
        nutLoc.setOnAction(e -> anNutLoc());
        nutSua.setOnAction(e -> anNutSua());
        nutThem.setOnAction(e -> anNutThem());
        nutXoa.setOnAction(e -> anNutXoa());
    }

    @FXML
    private void anNutLoc(){
        System.out.println("Search");
    }

    @FXML
    private void anNutSua(){
       System.out.println("Edit");
    }
    
    @FXML
    private void anNutThem(){
        System.out.println("Add");
    }
    
    @FXML
    private void anNutXoa(){
        System.out.println("Delete");
    }
}
