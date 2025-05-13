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
    @FXML
    private Button hozzaadButton;

    // Statikus adattag, hogy minden példány ugyanazt lássa
    private static Map<LocalDate, ObservableList<String>> naptarReceptek = new HashMap<>();
    private String receptNev;

    @FXML
    public void initialize() {
        datePicker.setValue(LocalDate.now());
        datePicker.valueProperty().addListener((obs, oldDate, newDate) -> updateReceptList(newDate));
        updateReceptList(datePicker.getValue());
    }

    public void setReceptNev(String nev) {
        this.receptNev = nev;
    }

    @FXML
    private void hozzaadReceptet() {
        LocalDate date = datePicker.getValue();
        if (receptNev != null && date != null) {
            naptarReceptek.putIfAbsent(date, FXCollections.observableArrayList());
            if (!naptarReceptek.get(date).contains(receptNev)) {
                naptarReceptek.get(date).add(receptNev);
            }
            updateReceptList(date);
        }
    }

    private void updateReceptList(LocalDate date) {
        ObservableList<String> receptek = naptarReceptek.getOrDefault(date, FXCollections.observableArrayList());
        receptListView.setItems(receptek);
    }
}
