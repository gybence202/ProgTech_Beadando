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
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);

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
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Nem sikerült létrehozni az adatbázist vagy a táblákat.", e);
        }
    }
}
