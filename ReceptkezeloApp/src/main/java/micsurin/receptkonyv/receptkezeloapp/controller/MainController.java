package micsurin.receptkonyv.receptkezeloapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private void nyitKeresesPanel() {
        loadView("/micsurin/receptkonyv/receptkezeloapp/kereses-view.fxml");
    }

    @FXML
    private void loadHozzaadasView() {
        loadView("/micsurin/receptkonyv/receptkezeloapp/hozzaadas-view.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            Node view = FXMLLoader.load(getClass().getResource(fxmlPath));
            rootPane.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
