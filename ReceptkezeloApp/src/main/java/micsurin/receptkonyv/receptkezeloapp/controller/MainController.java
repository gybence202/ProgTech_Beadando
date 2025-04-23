package micsurin.receptkonyv.receptkezeloapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private void loadKeresesView() {
        loadView("kereses-view.fxml");
    }

    @FXML
    private void loadHozzaadasView() {
        loadView("hozzaadas-view.fxml");
    }

    private void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/micsurin/receptkonyv/receptkezeloapp/" + fxmlFile));
            rootPane.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
