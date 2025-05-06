package micsurin.receptkonyv.receptkezeloapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import micsurin.receptkonyv.receptkezeloapp.service.ReceptDAO;
import micsurin.receptkonyv.receptkezeloapp.controller.ReceptController.Recept;

import java.sql.SQLException;
import java.util.List;

public class KedvencekController {

    @FXML
    private ListView<String> kedvencekListView;

    private ReceptDAO receptDAO = new ReceptDAO();
    private List<Recept> kedvencekLista;

    @FXML
    private void initialize() {
        try {
            kedvencekLista = receptDAO.getKedvencek();
            ObservableList<String> kedvencekNevek = FXCollections.observableArrayList();
            for (Recept recept : kedvencekLista) {
                kedvencekNevek.add(recept.getNev());
            }
            kedvencekListView.setItems(kedvencekNevek);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void removeFromFavorites() {
        String kivalasztottReceptNev = kedvencekListView.getSelectionModel().getSelectedItem();
        if (kivalasztottReceptNev != null) {
            try {
                for (Recept recept : kedvencekLista) {
                    if (recept.getNev().equals(kivalasztottReceptNev)) {
                        receptDAO.deleteKedvenc(recept);
                        kedvencekLista.remove(recept);
                        kedvencekListView.getItems().remove(kivalasztottReceptNev);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sikeres eltávolítás");
                        alert.setHeaderText(null);
                        alert.setContentText("A recept sikeresen eltávolítva a kedvencekből!");
                        alert.showAndWait();
                        break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hiba");
                alert.setHeaderText(null);
                alert.setContentText("Hiba történt a recept eltávolítása során.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nincs kijelölt recept");
            alert.setHeaderText(null);
            alert.setContentText("Kérlek, válassz ki egy receptet a listából!");
            alert.showAndWait();
        }
    }

    @FXML
    private void showReceptDetails() {
        String selectedReceptName = kedvencekListView.getSelectionModel().getSelectedItem();
        if (selectedReceptName != null) {
            for (Recept recept : kedvencekLista) {
                if (recept.getNev().equals(selectedReceptName)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Recept Részletei");
                    alert.setHeaderText("Recept: " + recept.getNev());

                    StringBuilder details = new StringBuilder("Alapanyagok:\n");
                    for (ReceptController.Alapanyag alapanyag : recept.getAlapanyagok()) {
                        details.append(alapanyag.getNev()).append(" - ").append(alapanyag.getMennyiseg()).append("\n");
                    }

                    alert.setContentText(details.toString());
                    alert.showAndWait();
                    break;
                }
            }
        }
    }
}