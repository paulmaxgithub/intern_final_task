package config.AWS;

import config.ConfigServiceManager;
import config.user.UserConfig;

import java.io.IOException;

public class AWSConfigService {

    public String getSession_id() {
        return getConfigValue( AWSConfig::getSession_id);
    }

    public String getSession_token() {
        return getConfigValue( AWSConfig::getSession_token);
    }

    public String getSession_id_time() {
        return getConfigValue( AWSConfig::getSession_id_time);
    }

    public String getUbid_main() {
        return getConfigValue( AWSConfig::getUbid_main);
    }

    public String getX_main() {
        return getConfigValue( AWSConfig::getX_main);
    }

    public static void setSessionAuthToken(String token) {
        try {
            ConfigServiceManager.updateConfig(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /// Generic method to safely retrieve config values
    private static String getConfigValue(AWSConfigGetter getter) {
        try {
            return getter.get(ConfigServiceManager.loadAWSConfig());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface AWSConfigGetter {
        String get(AWSConfig config);
    }
}
