package micsurin.receptkonyv.receptkezeloapp.model;

import java.util.List;

public class Recept {

    private String nev;
    private String leiras;
    private List<String> alapanyagok;  // Lista az alapanyagoknak
    private List<String> mennyisegek;  // Lista az alapanyagok mennyiségeinek

    // Konstruktor
    public Recept(String nev, String leiras, List<String> alapanyagok, List<String> mennyisegek) {
        this.nev = nev;
        this.leiras = leiras;
        this.alapanyagok = alapanyagok;
        this.mennyisegek = mennyisegek;
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

    public List<String> getAlapanyagok() {
        return alapanyagok;
    }

    public void setAlapanyagok(List<String> alapanyagok) {
        this.alapanyagok = alapanyagok;
    }

    public List<String> getMennyisegek() {
        return mennyisegek;
    }

    public void setMennyisegek(List<String> mennyisegek) {
        this.mennyisegek = mennyisegek;
    }

    // Függvények a kereséshez és rendezéshez
    public boolean keres(String keresett) {
        return nev.toLowerCase().contains(keresett.toLowerCase()) ||
                leiras.toLowerCase().contains(keresett.toLowerCase());
    }

    public void addAlapanyag(String alapanyag, String mennyiseg) {
        this.alapanyagok.add(alapanyag);
        this.mennyisegek.add(mennyiseg);
    }

    // String-ként visszaadja a recept információit
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Név: ").append(nev).append("\n");
        sb.append("Leírás: ").append(leiras).append("\n");
        sb.append("Alapanyagok: ").append("\n");
        for (int i = 0; i < alapanyagok.size(); i++) {
            sb.append(alapanyagok.get(i)).append(": ").append(mennyisegek.get(i)).append("\n");
        }
        return sb.toString();
    }
}
