package config.AWS.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.AWS.model.CookieModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AWSCookieFileManager {

    private final static String AWS_CONFIG_FILE = "aws_tokens.json";

    /**
     * Reads AWS tokens from the configuration file and maps them to a list of CookieModel objects.
     *
     * @return List of CookieModel objects parsed from the AWS configuration file.
     * @throws IOException if the file cannot be read or parsed.
     */
    public static List<CookieModel> readAWSTokensFile() throws IOException {

        // Get AWS file
        var tokensFile = getTokensFileIfExists();

        // Return CookieModel Data
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(tokensFile, new TypeReference<List<CookieModel>>() {});
    }

    //AWS_tokensTEST.json
    public static void updateAWSTokensFile(List<CookieModel> updatedModelData) throws IOException {

        // Get AWS file
        var tokensFile = getTokensFileIfExists();

        // Write updated list back to file
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(tokensFile, updatedModelData);
    }

    // PRIVATE ⚙️

    /**
     * Retrieves the AWS configuration file if it exists.
     *
     * @return File object representing the AWS configuration file.
     * @throws IOException if the file does not exist.
     */
    private static File getTokensFileIfExists() throws IOException {
        File tokensFile = new File(AWS_CONFIG_FILE);
        if (!tokensFile.exists()) { throw new IOException("❌ file NOT found - " + AWS_CONFIG_FILE); }
        return tokensFile;
    }
}
