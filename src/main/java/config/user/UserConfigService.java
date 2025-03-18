package config.user;

import config.ConfigServiceManager;

import java.io.IOException;

public class UserConfigService {

    public static String getBaseURL() {
        return getConfigValue(UserConfig::getBaseUrl);
    }

    public static String getUserEmail() {
        return getConfigValue(UserConfig::getUserEmail);
    }

    public static String getUserPassword() {
        return getConfigValue(UserConfig::getPassword);
    }

    public static String getUserName() {
        return getConfigValue(UserConfig::getUsername);
    }

    /// Generic method to safely retrieve config values
    private static String getConfigValue(UserConfigGetter getter) {
        try {
            return getter.get(ConfigServiceManager.loadUserConfig());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface UserConfigGetter {
        String get(UserConfig config);
    }
}