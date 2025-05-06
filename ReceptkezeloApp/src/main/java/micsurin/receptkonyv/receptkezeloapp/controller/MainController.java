package micsurin.receptkonyv.receptkezeloapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
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

    @FXML
    private void loadMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/micsurin/receptkonyv/receptkezeloapp/main-view.fxml"));
            BorderPane mainView = loader.load();
            Node centerContent = mainView.getCenter(); // Get only the center content
            rootPane.setCenter(centerContent); // Replace the center content
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadView(String fxmlPath) {
        try {
            Node view = FXMLLoader.load(getClass().getResource(fxmlPath));
            rootPane.setCenter(view); // Update only the center region
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadKedvencekView() {
        loadView("/micsurin/receptkonyv/receptkezeloapp/kedvencek-view.fxml");
    }


    @FXML
    private void loadShoppingListView() {
        try {
            Parent shoppingListView = FXMLLoader.load(getClass().getResource("/micsurin/receptkonyv/receptkezeloapp/shopping-list-view.fxml"));
            rootPane.setCenter(shoppingListView); // This works as Parent is a subclass of Node
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
