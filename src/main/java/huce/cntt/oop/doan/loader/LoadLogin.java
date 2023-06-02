package huce.cntt.oop.doan.loader;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoadLogin {
    private static String fxmlPath = "/view/Login.fxml";

    public static Scene getScene(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
            LoginController loginController = new LoginController();
            loginController.setStage(stage);
            loader.setController(loginController);

            // CSS            
            Pane root = loader.load();
            Scene sceneLogin = new Scene(root);
            return sceneLogin;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
