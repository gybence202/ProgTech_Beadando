package micsurin.receptkonyv.receptkezeloapp.model;

public abstract class ReceptDecorator extends Recept {

    protected Recept recept;

    public ReceptDecorator(Recept recept) {
        super(recept.getNev(), recept.getLeiras(), recept.getAlapanyagok(), recept.getMennyisegek());
        this.recept = recept;
    }

    @Override
    public String getNev() {
        return recept.getNev();
    }

    @Override
    public String getLeiras() {
        return recept.getLeiras();
    }

    @Override
    public void addAlapanyag(String alapanyag, String mennyiseg) {
        recept.addAlapanyag(alapanyag, mennyiseg);
    }
}
