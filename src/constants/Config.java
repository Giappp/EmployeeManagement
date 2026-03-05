package constants;

import java.time.format.DateTimeFormatter;

public class Config {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    private Config() {
    }
}
