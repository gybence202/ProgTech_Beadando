package micsurin.receptkonyv.receptkezeloapp.model;
public class GyorsDecorator extends ReceptDecorator {
    public GyorsDecorator(Recept recept) {
        super(recept);
    }

    public String getLeiras() {
        return recept.getLeiras() + " (Gyors elkészítés)";
    }
}