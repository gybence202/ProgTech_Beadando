package micsurin.receptkonyv.receptkezeloapp.model;

public class Recept {
    private String nev;
    private String leiras;

    public Recept(String nev, String leiras) {
        this.nev = nev;
        this.leiras = leiras;
    }

    public String getNev() {
        return nev;
    }

    public String getLeiras() {
        return leiras;
    }

    @Override
    public String toString() {
        return nev;
    }
}
