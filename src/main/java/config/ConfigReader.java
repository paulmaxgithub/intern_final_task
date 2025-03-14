package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class ConfigReader {

    //PRIVATE SETUP
    private final static String FILE_PATH = "config.json";

    private static EnvConfig loadConfig() throws IOException {

        // Get the resource as InputStream from the classpath
        InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(FILE_PATH);

        if (inputStream == null) {
            throw new RuntimeException("⚠️config.json not found in the classpath");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStream, EnvConfig.class);  // Read directly from InputStream
    }


    //PUBLIC METHOD
    public static String getBaseURLFromConfigJSON() {
        try {
            return loadConfig().getBaseUrl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserEmailFromConfigJSON() {
        try {
            return loadConfig().getUserEmail();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserPasswordFromConfigJSON() {
        try {
          return loadConfig().getPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserNameFromConfigJSON() {
        try {
            return loadConfig().getUsername();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAppNameFromConfigJSON() {
        try {
            return loadConfig().getAppName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}