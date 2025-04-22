package micsurin.receptkonyv.receptkezeloapp.model;
public class VegetarianusDecorator extends ReceptDecorator {
    public VegetarianusDecorator(Recept recept) {
        super(recept);
    }

    public String getLeiras() {
        return recept.getLeiras() + " (Vegetáriánus)";
    }
}