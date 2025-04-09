package micsurin.receptkonyv.receptkezeloapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import micsurin.receptkonyv.receptkezeloapp.model.Recept;

public class ReceptController {

    @FXML private ListView<Recept> receptListView;
    @FXML private TextField nevField;
    @FXML private TextArea leirasArea;

    private ObservableList<Recept> receptek = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        receptListView.setItems(receptek);
    }

    @FXML
    public void hozzaadReceptet() {
        String nev = nevField.getText().trim();
        String leiras = leirasArea.getText().trim();
        if (!nev.isEmpty() && !leiras.isEmpty()) {
            receptek.add(new Recept(nev, leiras));
            nevField.clear();
            leirasArea.clear();
        }
    }
}
