package micsurin.receptkonyv.receptkezeloapp.service;

import micsurin.receptkonyv.receptkezeloapp.model.Recept;
import micsurin.receptkonyv.receptkezeloapp.log.LoggerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReceptService {
    private final List<Recept> receptek = new ArrayList<>();
    private final LoggerService logger;

    public ReceptService(LoggerService logger) {
        this.logger = logger;
    }

    public void hozzaad(Recept recept) {
        receptek.add(recept);
        logger.log("Recept hozzáadva: " + recept.getNev());
    }

    public List<Recept> getReceptek() {
        return receptek;
    }

    public List<Recept> keresReceptet(String szuro) {
        return receptek.stream()
                .filter(r -> r.getNev().toLowerCase().contains(szuro.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void rendezesNév(String irany) {
        if ("csökkenő".equals(irany)) {
            receptek.sort((r1, r2) -> r2.getNev().compareTo(r1.getNev()));
        } else {
            receptek.sort((r1, r2) -> r1.getNev().compareTo(r2.getNev()));
        }
    }
}
