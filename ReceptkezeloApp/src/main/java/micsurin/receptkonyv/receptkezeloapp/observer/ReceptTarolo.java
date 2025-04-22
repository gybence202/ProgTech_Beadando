package micsurin.receptkonyv.receptkezeloapp.observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import micsurin.receptkonyv.receptkezeloapp.model.Recept;
import java.util.*;

public class ReceptTarolo implements Subject {
    private List<Observer> figyelok = new ArrayList<>();
    private ObservableList<Recept> receptek = FXCollections.observableArrayList();

    public void ujRecept(Recept r) {
        receptek.add(r);
        ertesitObserver();
    }

    public ObservableList<Recept> getReceptek() {
        return receptek;
    }

    public void hozzaadObserver(Observer o) {
        figyelok.add(o);
    }

    public void ertesitObserver() {
        for (Observer o : figyelok) o.frissit();
    }
}