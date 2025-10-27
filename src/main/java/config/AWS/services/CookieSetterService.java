package config.AWS.services;

import config.AWS.manager.AWSFileHandler;
import config.AWS.model.CookieModel;
import org.openqa.selenium.Cookie;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CookieSetterService {

    /// Update the JSON file
    public static void updateAWSCookieList(List<Cookie> cookies) throws IOException {
        List<CookieModel> cookieModels = convertToCookieModels(cookies);
        AWSFileHandler.updateAWSTokensFile(cookieModels);
    }

    // PRIVATE ⚙️
    /// Convert List<Cookie> to the CookieModel list
    private static List<CookieModel> convertToCookieModels(List<Cookie> cookieList) {
        return cookieList.stream()
                .map(cookie -> {
                 CookieModel model = new CookieModel();
                 model.setName(cookie.getName());
                 model.setValue(cookie.getValue());
                 return model;
                })
                .collect(Collectors.toList());
    }
}
