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

    private ObservableList<CheckBox> shoppingList;

    @FXML
    private void initialize() {
        shoppingList = FXCollections.observableArrayList();
        shoppingListView.setItems(shoppingList);
    }

    @FXML
    private void addItem() {
        String newItem = newItemField.getText().trim();
        if (!newItem.isEmpty()) {
            CheckBox checkBox = new CheckBox(newItem);
            shoppingList.add(checkBox);
            newItemField.clear();
        }
    }
}