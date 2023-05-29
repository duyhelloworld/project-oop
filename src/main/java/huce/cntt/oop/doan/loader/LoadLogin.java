package huce.cntt.oop.doan.loader;

import java.io.IOException;

import huce.cntt.oop.doan.App;
import huce.cntt.oop.doan.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LoadLogin {
    private static String fxmlPath = "/view/Login.fxml";

    public static Scene loadLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
        LoginController controller = new LoginController();
        loader.setController(controller);

        // CSS
        
        Pane root = loader.load();
        return new Scene(root);
    }    
}
