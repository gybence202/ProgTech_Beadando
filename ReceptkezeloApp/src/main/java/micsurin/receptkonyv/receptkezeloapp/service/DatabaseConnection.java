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
            ('Gyümölcssaláta', 'Vágd fel a gyümölcsöket, keverd össze.')
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
            (3, 'Narancs', '1 db')
        """);
    }
}
