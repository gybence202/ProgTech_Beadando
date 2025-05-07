package micsurin.receptkonyv.receptkezeloapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class ReceptApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/micsurin/receptkonyv/receptkezeloapp/main-view.fxml"));
        Scene scene = new Scene(root, 448, 450);
        stage.setTitle("Receptkezelő");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}