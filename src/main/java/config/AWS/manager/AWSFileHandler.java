package config.AWS.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.AWS.model.CookieModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AWSFileHandler {

    private final static String AWS_CONFIG_FILE = "aws_tokens.json";

    /**
     * Reads AWS tokens from the configuration file and maps them to a list of CookieModel objects.
     *
     * @return List of CookieModel objects parsed from the AWS configuration file.
     * @throws IOException if the file cannot be read or parsed.
     */
    public static List<CookieModel> readAWSTokensFile() throws IOException {

        // Get AWS file
        var tokensFile = getTokensFile();

        // Return CookieModel Data
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(tokensFile, new TypeReference<List<CookieModel>>() {});
    }

    //AWS_tokensTEST.json
    public static void updateAWSTokensFile(List<CookieModel> updatedModelData) throws IOException {

        // Get AWS file
        var tokensFile = getTokensFile();

        // Write updated list back to file
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(tokensFile, updatedModelData);
    }

    // PRIVATE ⚙️

    /**
     * Retrieves the tokens file, or creates and returns a fallback file if it doesn't exist.
     *
     * @return The tokens file if it exists, or a fallback file.
     */
    private static File getTokensFile() {
        File tokensFile = new File(AWS_CONFIG_FILE);

        // If the file doesn't exist, create a fallback and return it
        if (!tokensFile.exists()) {
            System.err.println("❌ File not found: " + AWS_CONFIG_FILE);
            return createFallbackFile();
        }

        // If the file exists, return it
        return tokensFile;
    }

    /**
     * Creates a fallback file if it doesn't exist.
     *
     * @return The fallback file, whether newly created or already existing.
     */
    private static File createFallbackFile() {
        File fallbackFile = new File(AWS_CONFIG_FILE);

        // Attempt to create the file if it doesn't exist
        if (!fallbackFile.exists()) {
            try {
                if (fallbackFile.createNewFile()) {
                    System.out.println("Default config file created.");
                } else {
                    System.err.println("Failed to create default config file.");
                }
            } catch (IOException e) {
                System.err.println("Error creating default file: " + e.getMessage());
            }
        }

        return fallbackFile;
    }
}
