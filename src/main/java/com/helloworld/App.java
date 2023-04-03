package com.helloworld;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

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