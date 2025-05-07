package tests;

import micsurin.receptkonyv.receptkezeloapp.service.ReceptDAO;
import micsurin.receptkonyv.receptkezeloapp.controller.ReceptController.Recept;
import micsurin.receptkonyv.receptkezeloapp.controller.ReceptController.Alapanyag;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceptTest {

    /*@Test
    void testAddRecept() throws SQLException {
        ReceptDAO receptDAO = new ReceptDAO();
        Recept recept = new Recept("Teszt Recept", "Ez egy teszt recept.", List.of(new Alapanyag("Teszt Alapanyag", "100g")));

        receptDAO.addRecept(recept);

        List<Recept> receptek = receptDAO.getAllReceptek();
        assertTrue(receptek.stream().anyMatch(r -> r.getNev().equals("Teszt Recept")));
    }

    @Test
    void testGetAllReceptek() throws SQLException {
        ReceptDAO receptDAO = new ReceptDAO();

        List<Recept> receptek = receptDAO.getAllReceptek();

        assertNotNull(receptek);
        assertTrue(receptek.size() > 0); // Assuming the database is pre-populated
    }

    @Test
    void testDeleteRecept() throws SQLException {
        ReceptDAO receptDAO = new ReceptDAO();
        Recept recept = new Recept("Törlendő Recept", "Ez egy törlendő recept.", List.of());

        receptDAO.addRecept(recept);
        receptDAO.deleteRecept(recept);

        List<Recept> receptek = receptDAO.getAllReceptek();
        assertFalse(receptek.stream().anyMatch(r -> r.getNev().equals("Törlendő Recept")));
    }*/
}