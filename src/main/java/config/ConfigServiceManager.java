package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.AWS.AWSConfig;
import config.user.UserConfig;

import java.io.*;

public class ConfigServiceManager {

    // PRIVATE CONSTANTS ⚙️
    private final static String USER_CONFIG_FILE = "config.json";
    private final static String AWS_CONFIG_FILE = "aws_tokens.json";

    ///
    public static UserConfig loadUserConfig() throws IOException {
        File configFile = new File(USER_CONFIG_FILE);

        if (!configFile.exists()) {
            throw new IOException("❌ file NOT found - " + USER_CONFIG_FILE);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(configFile, UserConfig.class);
    }

    /// REFACTOR ⚠️⚠️⚠️
    public static AWSConfig loadAWSConfig() throws IOException {
        File configFile = new File(USER_CONFIG_FILE);

        if (!configFile.exists()) {
            throw new IOException("❌ file NOT found - " + USER_CONFIG_FILE);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(configFile, AWSConfig.class);
    }

    /// Update AWS_CONFIG_FILE ⚠️
    public static void updateConfig(String value) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File configFile = new File(AWS_CONFIG_FILE);

        if (!configFile.exists()) {
            throw new IOException("❌ file NOT found - " + AWS_CONFIG_FILE);
        }

        // Read existing config
        UserConfig config = objectMapper.readValue(configFile, UserConfig.class);

        // Update sessionAuthToken
        //config.setSessionAuthToken(value);

        // Write updated config back to the file
        try (FileWriter writer = new FileWriter(configFile)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, config);
        }
    }
}