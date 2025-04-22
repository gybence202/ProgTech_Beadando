package micsurin.receptkonyv.receptkezeloapp.log;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerService {
    private static final String logFilePath = "log.txt";

    public static void log(String message) {
        try (FileWriter writer = new FileWriter(logFilePath, true)) {
            writer.write(LocalDateTime.now() + " - " + message + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Naplózási hiba: " + e.getMessage());
        }
    }
}
