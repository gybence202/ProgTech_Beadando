package micsurin.receptkonyv.receptkezeloapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import micsurin.receptkonyv.receptkezeloapp.service.ReceptDAO;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.sql.SQLException;
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

    private ReceptDAO receptDAO = new ReceptDAO();
    private List<Recept> receptLista = new ArrayList<>();

    @FXML
    private void initialize() {
        try {
            receptLista = receptDAO.getAllReceptek();
            ObservableList<String> receptNevek = FXCollections.observableArrayList();
            for (Recept recept : receptLista) {
                receptNevek.add(recept.getNev());
            }
            receptListView.setItems(receptNevek);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void hozzaadReceptet(ActionEvent event) {
        int id = receptLista.size() + 1;
        String nev = nevField.getText();
        String leiras = leirasArea.getText();
        if (!nev.isEmpty() && !leiras.isEmpty()) {
            Recept recept = new Recept(id,nev, leiras, new ArrayList<>());
            try {
                receptDAO.addRecept(recept);
                receptLista.add(recept);
                receptListView.getItems().add(nev);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void hozzadAlapanyagot(ActionEvent event) {
        String alapanyagNev = alapanyagNevField.getText();
        String mennyiseg = mennyisegField.getText();

        if (!alapanyagNev.isEmpty() && !mennyiseg.isEmpty()) {
            try {
                String kivalasztottReceptNev = receptListView.getSelectionModel().getSelectedItem();
                if (kivalasztottReceptNev != null) {
                    for (Recept recept : receptLista) {
                        if (recept.getNev().equals(kivalasztottReceptNev)) {
                            Alapanyag ujAlapanyag = new Alapanyag(alapanyagNev, mennyiseg);
                            recept.getAlapanyagok().add(ujAlapanyag);

                            receptDAO.addAlapanyag(recept, ujAlapanyag);

                            alapanyagNevField.clear();
                            mennyisegField.clear();

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Sikeres hozzáadás");
                            alert.setHeaderText(null);
                            alert.setContentText("Az alapanyag sikeresen hozzáadva a recepthez!");
                            alert.showAndWait();

                            break;
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Nincs kijelölt recept");
                    alert.setHeaderText(null);
                    alert.setContentText("Kérlek, válassz ki egy receptet a listából!");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hiba");
                alert.setHeaderText(null);
                alert.setContentText("Hiba történt az alapanyag hozzáadása során.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hiányzó adatok");
            alert.setHeaderText(null);
            alert.setContentText("Kérlek, töltsd ki az alapanyag nevét és mennyiségét!");
            alert.showAndWait();
        }
    }

    @FXML
    private void torolReceptet(ActionEvent event) {
        String kivalasztottReceptNev = receptListView.getSelectionModel().getSelectedItem();
        if (kivalasztottReceptNev != null) {
            try {
                // Megkeressük a kijelölt receptet a listában
                Recept torlendoRecept = null;
                for (Recept recept : receptLista) {
                    if (recept.getNev().equals(kivalasztottReceptNev)) {
                        torlendoRecept = recept;
                        break;
                    }
                }

                if (torlendoRecept != null) {
                    receptDAO.deleteRecept(torlendoRecept);

                    receptLista.remove(torlendoRecept);
                    receptListView.getItems().remove(kivalasztottReceptNev);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sikeres törlés");
                    alert.setHeaderText(null);
                    alert.setContentText("A recept sikeresen törölve lett!");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hiba");
                alert.setHeaderText(null);
                alert.setContentText("Hiba történt a recept törlése során.");
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
    private void kereses() {
        String keresettSzoveg = keresesField.getText().toLowerCase();
        ObservableList<String> szuresLista = FXCollections.observableArrayList();
        try {
            receptLista = receptDAO.getAllReceptek();
            for (Recept recept : receptLista) {
                if (recept.getNev().toLowerCase().contains(keresettSzoveg)) {
                    szuresLista.add(recept.getNev());
                } else {
                    for (Alapanyag alapanyag : recept.getAlapanyagok()) {
                        if (alapanyag.getNev().toLowerCase().contains(keresettSzoveg)) {
                            szuresLista.add(recept.getNev());
                            break;
                        }
                    }
                }
            }
            receptListView.setItems(szuresLista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void receptKivalasztas() {
        String kivalasztottRecept = receptListView.getSelectionModel().getSelectedItem();
        if (kivalasztottRecept != null) {
            for (Recept recept : receptLista) {
                if (recept.getNev().equals(kivalasztottRecept)) {
                    showReceptDetails(recept);
                    break;
                }
            }
        }
    }

    private void showReceptDetails(Recept recept) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Recept Részletei");
        alert.setHeaderText("Recept: " + recept.getNev());

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
        String kivantRend = rendezesCombo.getSelectionModel().getSelectedItem();
        try {
            receptLista = receptDAO.getAllReceptek();
            if ("Név szerint növekvő".equals(kivantRend)) {
                receptLista.sort((r1, r2) -> r1.getNev().compareTo(r2.getNev()));
            } else if ("Név szerint csökkenő".equals(kivantRend)) {
                receptLista.sort((r1, r2) -> r2.getNev().compareTo(r1.getNev()));
            } else if ("Hozzáadás szerint növekvő".equals(kivantRend)) {
                receptLista.sort((r1, r2) -> Integer.compare(r1.getId(), r2.getId()));
            } else if ("Hozzáadás szerint csökkenő".equals(kivantRend)) {
                receptLista.sort((r1, r2) -> Integer.compare(r2.getId(), r1.getId()));
            }
            ObservableList<String> frissLista = FXCollections.observableArrayList();
            for (Recept recept : receptLista) {
                frissLista.add(recept.getNev());
            }
            receptListView.setItems(frissLista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void addToFavorites() {
        String kivalasztottReceptNev = receptListView.getSelectionModel().getSelectedItem();
        if (kivalasztottReceptNev != null) {
            try {
                for (Recept recept : receptLista) {
                    if (recept.getNev().equals(kivalasztottReceptNev)) {
                        receptDAO.addKedvenc(recept);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sikeres hozzáadás");
                        alert.setHeaderText(null);
                        alert.setContentText("A recept sikeresen hozzáadva a kedvencekhez!");
                        alert.showAndWait();
                        break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hiba");
                alert.setHeaderText(null);
                alert.setContentText("Hiba történt a kedvencekhez adás során.");
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
    private void addToCalendar() {
        String kivalasztottReceptNev = receptListView.getSelectionModel().getSelectedItem();
        if (kivalasztottReceptNev != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/micsurin/receptkonyv/receptkezeloapp/naptar-view.fxml"));
                Parent root = loader.load();
                micsurin.receptkonyv.receptkezeloapp.controller.NaptarView naptarController = loader.getController();

                naptarController.setReceptNev(kivalasztottReceptNev);

                Stage stage = new Stage();
                stage.setTitle("Naptár");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hiba");
                alert.setHeaderText(null);
                alert.setContentText("Hiba történt a naptárhoz adás során.");
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



    public static class Recept {
        private int id;
        private String nev;
        private String leiras;
        private List<Alapanyag> alapanyagok;

        public Recept(int id,String nev, String leiras, List<Alapanyag> alapanyagok) {
            this.id = id;
            this.nev = nev;
            this.leiras = leiras;
            this.alapanyagok = alapanyagok;
        }

        public  int getId() { return id; }
        public String getNev() { return nev; }
        public String getLeiras() { return leiras; }
        public List<Alapanyag> getAlapanyagok() { return alapanyagok; }

    }

    public static class Alapanyag {
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
