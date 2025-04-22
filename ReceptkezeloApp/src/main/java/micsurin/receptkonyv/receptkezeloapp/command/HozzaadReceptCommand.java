package micsurin.receptkonyv.receptkezeloapp.command;

import micsurin.receptkonyv.receptkezeloapp.model.Recept;
import micsurin.receptkonyv.receptkezeloapp.observer.ReceptTarolo;
import micsurin.receptkonyv.receptkezeloapp.log.LoggerService;

public class HozzaadReceptCommand implements Command {
    private ReceptTarolo tarolo;
    private Recept recept;

    public HozzaadReceptCommand(ReceptTarolo tarolo, Recept recept) {
        this.tarolo = tarolo;
        this.recept = recept;
    }

    public void vegrehajt() {
        tarolo.ujRecept(recept);
        LoggerService.log("Recept hozzáadva: " + recept.getNev());
    }
}