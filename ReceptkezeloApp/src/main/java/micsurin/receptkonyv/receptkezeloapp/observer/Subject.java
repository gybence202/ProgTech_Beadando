package micsurin.receptkonyv.receptkezeloapp.observer;
public interface Subject {
    void hozzaadObserver(Observer o);
    void ertesitObserver();
}
