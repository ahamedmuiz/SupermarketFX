package lk.ijse.supermarketfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AppInitializer extends Application {
    @Override

    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

    }

}
