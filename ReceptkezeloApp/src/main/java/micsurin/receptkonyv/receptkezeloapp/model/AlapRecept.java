package micsurin.receptkonyv.receptkezeloapp.model;

public class AlapRecept {

    private String nev;
    private String leiras;

    // Konstruktor
    public AlapRecept(String nev, String leiras) {
        this.nev = nev;
        this.leiras = leiras;
    }

    // Getterek és setterek
    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }
}
