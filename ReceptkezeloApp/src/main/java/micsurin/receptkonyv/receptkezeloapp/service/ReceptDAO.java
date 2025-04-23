package micsurin.receptkonyv.receptkezeloapp.service;

import micsurin.receptkonyv.receptkezeloapp.controller.ReceptController.Recept;
import micsurin.receptkonyv.receptkezeloapp.controller.ReceptController.Alapanyag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceptDAO {

    public List<Recept> getAllReceptek() throws SQLException {
        List<Recept> receptek = new ArrayList<>();
        String query = "SELECT * FROM receptek";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nev = rs.getString("nev");
                String leiras = rs.getString("leiras");

                List<Alapanyag> alapanyagok = getAlapanyagokByReceptId(id);
                receptek.add(new Recept(nev, leiras, alapanyagok));
            }
        }
        return receptek;
    }

    public List<Alapanyag> getAlapanyagokByReceptId(int receptId) throws SQLException {
        List<Alapanyag> alapanyagok = new ArrayList<>();
        String query = "SELECT * FROM alapanyagok WHERE recept_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, receptId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String nev = rs.getString("nev");
                    String mennyiseg = rs.getString("mennyiseg");
                    alapanyagok.add(new Alapanyag(nev, mennyiseg));
                }
            }
        }
        return alapanyagok;
    }

    public void addRecept(Recept recept) throws SQLException {
        String insertRecept = "INSERT INTO receptek (nev, leiras) VALUES (?, ?)";
        String insertAlapanyag = "INSERT INTO alapanyagok (recept_id, nev, mennyiseg) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement receptStmt = conn.prepareStatement(insertRecept, Statement.RETURN_GENERATED_KEYS)) {

            receptStmt.setString(1, recept.getNev());
            receptStmt.setString(2, recept.getLeiras());
            receptStmt.executeUpdate();

            try (ResultSet generatedKeys = receptStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int receptId = generatedKeys.getInt(1);

                    try (PreparedStatement alapanyagStmt = conn.prepareStatement(insertAlapanyag)) {
                        for (Alapanyag alapanyag : recept.getAlapanyagok()) {
                            alapanyagStmt.setInt(1, receptId);
                            alapanyagStmt.setString(2, alapanyag.getNev());
                            alapanyagStmt.setString(3, alapanyag.getMennyiseg());
                            alapanyagStmt.addBatch();
                        }
                        alapanyagStmt.executeBatch();
                    }
                }
            }
        }
    }
    public void addAlapanyag(Recept recept, Alapanyag alapanyag) throws SQLException {
        String query = "INSERT INTO alapanyagok (recept_id, nev, mennyiseg) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, getReceptIdByName(recept.getNev())); // Megkeressük a recept ID-ját
            pstmt.setString(2, alapanyag.getNev());
            pstmt.setString(3, alapanyag.getMennyiseg());
            pstmt.executeUpdate();
        }
    }

    private int getReceptIdByName(String nev) throws SQLException {
        String query = "SELECT id FROM receptek WHERE nev = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nev);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new SQLException("Recept nem található: " + nev);
                }
            }
        }
    }
    public void deleteRecept(Recept recept) throws SQLException {
        String deleteAlapanyagokQuery = "DELETE FROM alapanyagok WHERE recept_id = ?";
        String deleteReceptQuery = "DELETE FROM receptek WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement deleteAlapanyagokStmt = conn.prepareStatement(deleteAlapanyagokQuery);
             PreparedStatement deleteReceptStmt = conn.prepareStatement(deleteReceptQuery)) {

            int receptId = getReceptIdByName(recept.getNev());

            deleteAlapanyagokStmt.setInt(1, receptId);
            deleteAlapanyagokStmt.executeUpdate();

            deleteReceptStmt.setInt(1, receptId);
            deleteReceptStmt.executeUpdate();
        }
    }
}
