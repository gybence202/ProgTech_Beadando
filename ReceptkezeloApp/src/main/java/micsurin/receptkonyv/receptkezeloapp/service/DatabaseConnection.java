package micsurin.receptkonyv.receptkezeloapp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DATABASE_NAME = "receptdb";

    public static Connection getConnection() throws SQLException {
        createDatabaseIfNotExists();
        return DriverManager.getConnection(URL+DATABASE_NAME, USER, PASSWORD);
    }

    private static void createDatabaseIfNotExists() {
        boolean isDatabaseCreated = false;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            int result = stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);
            isDatabaseCreated = (result > 0);

            try (Connection dbConn = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
                 Statement dbStmt = dbConn.createStatement()) {

                dbStmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS receptek (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nev VARCHAR(255) NOT NULL,
                        leiras TEXT NOT NULL
                    )
                """);

                dbStmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS alapanyagok (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        recept_id INT NOT NULL,
                        nev VARCHAR(255) NOT NULL,
                        mennyiseg VARCHAR(255) NOT NULL,
                        FOREIGN KEY (recept_id) REFERENCES receptek(id) ON DELETE CASCADE
                    )
                """);

                if (isDatabaseCreated) {
                    addDefaultReceptek(dbStmt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Nem sikerült létrehozni az adatbázist vagy a táblákat.", e);
        }
    }

    private static void addDefaultReceptek(Statement dbStmt) throws SQLException {
        dbStmt.executeUpdate("""
            INSERT INTO receptek (nev, leiras) VALUES
            ('Palacsinta', 'Keverd össze a hozzávalókat, süsd ki serpenyőben.'),
            ('Gulyásleves', 'Főzd össze a hozzávalókat, amíg megpuhulnak.'),
            ('Gyümölcssaláta', 'Vágd fel a gyümölcsöket, keverd össze.'),
            ('Pörkölt', 'Főzd meg a húst, adj hozzá fűszereket és hagymát.'),
            ('Rakott Krumpli', 'Főzd meg a krumplit, rétegezd a többi hozzávalóval.'),
            ('Túrós Csusza', 'Főzd meg a tésztát, keverd össze túróval és tejföllel.'),
            ('Lángos', 'Készíts tésztát, süsd ki forró olajban.'),
            ('Töltött Káposzta', 'Készíts tölteléket, rakd a káposztalevelekbe, főzd meg.'),
            ('Hortobágyi Palacsinta', 'Készíts palacsintát, töltsd meg hússal, süsd meg.'),
            ('Kacsasült', 'Süsd meg a kacsát, tálald pörkölttel vagy sült krumplival.'),
            ('Mákos Tészta', 'Főzd meg a tésztát, keverd össze mákkal és cukorral.'),
            ('Leveses Tészta', 'Főzd meg a tésztát, készíts levest zöldségekkel.'),
            ('Kéksajtos Saláta', 'Keverj össze saláta alapanyagokat, adj hozzá kéksajtot.'),
            ('Köleses Rakott Zöldség', 'Főzd meg a kölest, rétegezd zöldségekkel, süsd meg.'),
            ('Bélszín Steak', 'Süsd meg a bélszínt, tálald körettel.'),
            ('Rántott Hús', 'Panírozd be a húst, süsd ki forró olajban.'),
            ('Sült Hal', 'Fűszerezd a halat, süsd meg.'),
            ('Mákos Guba', 'Rétegezd a kiflit mákkal, cukorral, süsd meg.'),
            ('Főzelék', 'Készíts zöldségfőzeléket, fűszerezd ízlés szerint.'),
            ('Cigánka', 'Készíts tésztát, süsd ki forró olajban.')
        """);

        dbStmt.executeUpdate("""
            INSERT INTO alapanyagok (recept_id, nev, mennyiseg) VALUES
            (1, 'Liszt', '200g'),
            (1, 'Tej', '300ml'),
            (1, 'Tojás', '2 db'),
            (2, 'Marhahús', '500g'),
            (2, 'Burgonya', '3 db'),
            (2, 'Vöröshagyma', '1 db'),
            (3, 'Alma', '2 db'),
            (3, 'Banán', '1 db'),
            (3, 'Narancs', '1 db'),
            (4, 'Marhahús', '500g'),
            (4, 'Hagyma', '1 db'),
            (4, 'Paprika', '2 db'),
            (4, 'Paradicsom', '2 db'),
            (5, 'Krumpli', '4 db'),
            (5, 'Tojás', '2 db'),
            (5, 'Tejföl', '200g'),
            (5, 'Sajt', '100g'),
            (6, 'Tészta', '500g'),
            (6, 'Túró', '300g'),
            (6, 'Tejföl', '200g'),
            (7, 'Lángos tészta', '500g'),
            (7, 'Tej', '100ml'),
            (7, 'Fokhagyma', '2 gerezd'),
            (8, 'Káposzta', '1 fej'),
            (8, 'Füstölt hús', '300g'),
            (8, 'Rizs', '100g'),
            (9, 'Puliszka', '200g'),
            (9, 'Kéksajt', '150g'),
            (10, 'Kacsa', '1 db'),
            (10, 'Fűszerek', 'ízlés szerint'),
            (11, 'Tészta', '300g'),
            (11, 'Mák', '100g'),
            (11, 'Cukor', '100g'),
            (12, 'Tészta', '500g'),
            (12, 'Zöldség', '200g'),
            (13, 'Saláta', '1 fej'),
            (13, 'Kéksajt', '150g'),
            (14, 'Köles', '200g'),
            (14, 'Zöldség', '300g'),
            (15, 'Bélszín', '500g'),
            (15, 'Fűszerek', 'ízlés szerint'),
            (16, 'Hús', '500g'),
            (16, 'Panír', '100g'),
            (17, 'Hal', '2 db'),
            (17, 'Fűszerek', 'ízlés szerint'),
            (18, 'Kifli', '5 db'),
            (18, 'Mák', '150g'),
            (19, 'Zöldség', '300g'),
            (19, 'Tejföl', '100g'),
            (20, 'Tészta', '300g'),
            (20, 'Fűszeres étolaj', '100ml')
        """);
    }
}
