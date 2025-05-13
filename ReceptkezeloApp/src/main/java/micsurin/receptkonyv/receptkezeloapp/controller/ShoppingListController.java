package micsurin.receptkonyv.receptkezeloapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ShoppingListController {

    @FXML
    private ListView<CheckBox> shoppingListView;

    @FXML
    private TextField newItemField;

    @FXML
    private TextField quantityField;

    private ObservableList<CheckBox> shoppingList;

    @FXML
    private void initialize() {
        shoppingList = FXCollections.observableArrayList();
        shoppingListView.setItems(shoppingList);
    }

    @FXML
    private void addItem() {
        String newItem = newItemField.getText().trim();
        String quantity = quantityField != null ? quantityField.getText().trim() : "";
        if (!newItem.isEmpty() && !quantity.isEmpty()) {
            CheckBox checkBox = new CheckBox(newItem + " - " + quantity);
            shoppingList.add(checkBox);
            newItemField.clear();
            quantityField.clear();
        }
    }

    @FXML
    private void removeItem() {
        CheckBox selectedItem = shoppingListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            shoppingList.remove(selectedItem);
        }
    }
}