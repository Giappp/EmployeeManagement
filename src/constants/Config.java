package constants;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class Config {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    public static final String FILE_NAME = "employee.csv";
    public static final String CSV_HEADER = "ID,NAME,EMAIL,PHONE,STATUS,SALARY,DIVISION,JOIN_DATE";
    public static final String DELIMITER = ",";
    public static final Path FILE_PATH = Path.of(Config.FILE_NAME);

    private Config() {
    }
}
