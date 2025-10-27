package config.user;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class ConfigFileManager {

    // PRIVATE CONSTANTS ⚙️
    private final static String USER_CONFIG_FILE = "config.json";

    ///
    public static UserConfig loadUserConfig() throws IOException {
        File configFile = new File(USER_CONFIG_FILE);

        if (!configFile.exists()) {
            throw new IOException("❌ file NOT found - " + USER_CONFIG_FILE);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(configFile, UserConfig.class);
    }
}