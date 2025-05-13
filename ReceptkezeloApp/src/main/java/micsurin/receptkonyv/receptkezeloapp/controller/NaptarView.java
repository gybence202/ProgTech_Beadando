package micsurin.receptkonyv.receptkezeloapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class NaptarView {

    @FXML
    private DatePicker datePicker;
    @FXML
    private ListView<String> receptListView;

    private Map<LocalDate, ObservableList<String>> naptarReceptek = new HashMap<>();

    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
        datePicker.valueProperty().addListener((obs, oldDate, newDate) -> updateReceptList(newDate));
        updateReceptList(datePicker.getValue());
    }

    public void addReceptToDate(LocalDate date, String receptNev) {
        naptarReceptek.putIfAbsent(date, FXCollections.observableArrayList());
        naptarReceptek.get(date).add(receptNev);
        if (date.equals(datePicker.getValue())) {
            updateReceptList(date);
        }
    }

    private void updateReceptList(LocalDate date) {
        ObservableList<String> receptek = naptarReceptek.getOrDefault(date, FXCollections.observableArrayList());
        receptListView.setItems(receptek);
    }
}
