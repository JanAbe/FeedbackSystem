package common;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConfig {
    // maybe turn a lot / all attributes static, idk though
    private String connectionString;
    private String username;
    private String password;
    private final String CONFIG_FILE_NAME = "\\.config\\database.json";
    private final static Logger LOGGER = Logger.getLogger(DatabaseConfig.class.getName());

    public DatabaseConfig() {
        this.initialize();
    }

    // ---------- Public methods ----------

    public String connectionString() {
        return this.connectionString;
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    // ---------- Private methods ----------

    private void initialize() {
        try {
            JSONObject config = (JSONObject) this.readJSONConfig();
            this.connectionString = (String) config.get("connectionString");
            this.username = (String) config.get("username");
            this.password = (String) config.get("password");
        } catch (Exception e) {
            LOGGER.log(Level.CONFIG, "Exception occurred while reading/parsing config file: ", e);
        }
    }

    private Object readJSONConfig() throws Exception {
        var configPath = projectPath() + this.CONFIG_FILE_NAME;
        var fileReader = new FileReader(configPath);
        var parser = new JSONParser();

        return parser.parse(fileReader);
    }

    private String projectPath() {
        var file = new File("").getAbsoluteFile();
        return file.getAbsolutePath();
    }
}
