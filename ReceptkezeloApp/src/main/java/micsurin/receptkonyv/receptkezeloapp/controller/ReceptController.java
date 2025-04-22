package micsurin.receptkonyv.receptkezeloapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ReceptController {

    @FXML private TextField nevField;
    @FXML private TextArea leirasArea;
    @FXML private TextField alapanyagNevField;
    @FXML private TextField mennyisegField;
    @FXML private ListView<String> receptListView;
    @FXML private TextField keresesField;
    @FXML private ComboBox<String> rendezesCombo;

    private List<Recept> receptLista = new ArrayList<>();

    @FXML
    private void initialize() {
        // Alapanyagok és receptek listájának inicializálása
        receptLista.add(new Recept("Pörkölt", "Finom magyar étel", new ArrayList<>()));
        receptLista.get(0).getAlapanyagok().add(new Alapanyag("Hús", "1 kg"));
        receptLista.get(0).getAlapanyagok().add(new Alapanyag("Hagyma", "3 db"));

        receptListView.setItems(FXCollections.observableArrayList(
                "Pörkölt", "Lecsó", "Túró Rudi"
        ));
    }

    @FXML
    private void hozzaadReceptet(ActionEvent event) {
        String nev = nevField.getText();
        String leiras = leirasArea.getText();
        if (!nev.isEmpty() && !leiras.isEmpty()) {
            receptLista.add(new Recept(nev, leiras, new ArrayList<>()));
            receptListView.getItems().add(nev);
        }
    }

    @FXML
    private void hozzadAlapanyagot(ActionEvent event) {
        String alapanyagNev = alapanyagNevField.getText();
        String mennyiseg = mennyisegField.getText();

        if (!alapanyagNev.isEmpty() && !mennyiseg.isEmpty()) {
            Alapanyag alapanyag = new Alapanyag(alapanyagNev, mennyiseg);
            // Hozzáadjuk az alapanyagot az utolsó recepthez
            receptLista.get(receptLista.size() - 1).getAlapanyagok().add(alapanyag);
        }
    }

    @FXML
    private void kereses() {
        String keresettNev = keresesField.getText().toLowerCase();
        ObservableList<String> szuresLista = FXCollections.observableArrayList();
        for (Recept recept : receptLista) {
            if (recept.getNev().toLowerCase().contains(keresettNev)) {
                szuresLista.add(recept.getNev());
            }
        }
        receptListView.setItems(szuresLista);
    }

    @FXML
    private void receptKivalasztas() {
        String kivalasztottRecept = receptListView.getSelectionModel().getSelectedItem();
        for (Recept recept : receptLista) {
            if (recept.getNev().equals(kivalasztottRecept)) {
                showReceptDetails(recept);
                break;
            }
        }
    }

    private void showReceptDetails(Recept recept) {
        // Alert ablakot hozunk létre
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Recept Részletei");
        alert.setHeaderText("Recept: " + recept.getNev());

        // Alapanyagok táblázat
        StringBuilder alapanyagokText = new StringBuilder("Alapanyagok:\n");
        for (Alapanyag alapanyag : recept.getAlapanyagok()) {
            alapanyagokText.append(alapanyag.getNev()).append(" - ").append(alapanyag.getMennyiseg()).append("\n");
        }
        alert.setContentText(alapanyagokText.toString());

        alert.showAndWait();
    }

    @FXML
    private void nyitKeresesPanel() {
        // Létrehozzuk a keresés panelt egy új Stage-en
        Stage keresesStage = new Stage();
        VBox keresesLayout = new VBox();
        TextField keresesInput = new TextField();
        keresesInput.setPromptText("Írj be egy recept nevet!");

        Button keresesButton = new Button("Keresés");
        keresesButton.setOnAction(e -> kereses());

        keresesLayout.getChildren().addAll(keresesInput, keresesButton);

        keresesStage.setScene(new javafx.scene.Scene(keresesLayout, 300, 200));
        keresesStage.setTitle("Keresés");
        keresesStage.show();
    }

    @FXML
    private void rendez(ActionEvent event) {
        // A combo box alapján rendezzük a listát
        String kivantRend = rendezesCombo.getSelectionModel().getSelectedItem();

        if ("Név szerint növekvő".equals(kivantRend)) {
            receptLista.sort((r1, r2) -> r1.getNev().compareTo(r2.getNev()));
        } else if ("Név szerint csökkenő".equals(kivantRend)) {
            receptLista.sort((r1, r2) -> r2.getNev().compareTo(r1.getNev()));
        }

        // Frissítjük a ListView-t
        ObservableList<String> frissLista = FXCollections.observableArrayList();
        for (Recept recept : receptLista) {
            frissLista.add(recept.getNev());
        }
        receptListView.setItems(frissLista);
    }

    public class Recept {
        private String nev;
        private String leiras;
        private List<Alapanyag> alapanyagok;

        public Recept(String nev, String leiras, List<Alapanyag> alapanyagok) {
            this.nev = nev;
            this.leiras = leiras;
            this.alapanyagok = alapanyagok;
        }

        public String getNev() { return nev; }
        public String getLeiras() { return leiras; }
        public List<Alapanyag> getAlapanyagok() { return alapanyagok; }
    }

    public class Alapanyag {
        private String nev;
        private String mennyiseg;

        public Alapanyag(String nev, String mennyiseg) {
            this.nev = nev;
            this.mennyiseg = mennyiseg;
        }

        public String getNev() { return nev; }
        public String getMennyiseg() { return mennyiseg; }
    }
}
