package tests;

import micsurin.receptkonyv.receptkezeloapp.service.ReceptDAO;
import micsurin.receptkonyv.receptkezeloapp.controller.ReceptController.Recept;
import micsurin.receptkonyv.receptkezeloapp.controller.ReceptController.Alapanyag;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReceptTest {

    private ReceptDAO receptDAO;

    @BeforeAll
    void setUp() throws SQLException {
        receptDAO = new ReceptDAO();
        clearDatabase();
    }

    @AfterEach
    void cleanUp() throws SQLException {
        clearDatabase();
    }

    private void clearDatabase() throws SQLException {
        List<Recept> receptek = receptDAO.getAllReceptek();
        for (Recept recept : receptek) {
            receptDAO.deleteRecept(recept);
        }
    }

    //Bence Teszt
    @Test
    void testAddRecept() throws SQLException {
        Recept recept = new Recept(1, "Teszt Recept", "Ez egy teszt recept.", List.of(new Alapanyag("Teszt Alapanyag", "100g")));

        receptDAO.addRecept(recept);

        List<Recept> receptek = receptDAO.getAllReceptek();
        assertTrue(receptek.stream().anyMatch(r -> r.getNev().equals("Teszt Recept")));
    }

    @Test
    void testGetAllReceptek() throws SQLException {
        Recept recept1 = new Recept(1, "Recept 1", "Leírás 1", List.of());
        Recept recept2 = new Recept(2, "Recept 2", "Leírás 2", List.of());

        receptDAO.addRecept(recept1);
        receptDAO.addRecept(recept2);

        List<Recept> receptek = receptDAO.getAllReceptek();

        assertNotNull(receptek);
        assertEquals(2, receptek.size());
    }

    @Test
    void testDeleteRecept() throws SQLException {
        Recept recept = new Recept(1, "Törlendő Recept", "Ez egy törlendő recept.", List.of());

        receptDAO.addRecept(recept);
        receptDAO.deleteRecept(recept);

        List<Recept> receptek = receptDAO.getAllReceptek();
        assertFalse(receptek.stream().anyMatch(r -> r.getNev().equals("Törlendő Recept")));
    }

    @Test
    void testAddAlapanyag() throws SQLException {
        Recept recept = new Recept(1, "Alapanyag Teszt Recept", "Ez egy teszt recept.", List.of());
        receptDAO.addRecept(recept);

        Alapanyag alapanyag = new Alapanyag("Teszt Alapanyag", "200g");
        receptDAO.addAlapanyag(recept, alapanyag);

        List<Recept> receptek = receptDAO.getAllReceptek();
        Recept retrievedRecept = receptek.stream().filter(r -> r.getNev().equals("Alapanyag Teszt Recept")).findFirst().orElse(null);

        assertNotNull(retrievedRecept);
        assertEquals(1, retrievedRecept.getAlapanyagok().size());
        assertEquals("Teszt Alapanyag", retrievedRecept.getAlapanyagok().get(0).getNev());
    }

    @Test
    void testSearchReceptByName() throws SQLException {
        Recept recept = new Recept(1, "Keresett Recept", "Ez egy keresett recept.", List.of());
        receptDAO.addRecept(recept);

        List<Recept> receptek = receptDAO.getAllReceptek();
        assertTrue(receptek.stream().anyMatch(r -> r.getNev().contains("Keresett")));
    }

    @Test
    void testSearchReceptByAlapanyag() throws SQLException {
        Recept recept = new Recept(1, "Alapanyag Keresés Teszt", "Ez egy teszt recept.", List.of(new Alapanyag("Keresett Alapanyag", "100g")));
        receptDAO.addRecept(recept);

        List<Recept> receptek = receptDAO.getAllReceptek();
        assertTrue(receptek.stream().anyMatch(r -> r.getAlapanyagok().stream().anyMatch(a -> a.getNev().contains("Keresett"))));
    }

}