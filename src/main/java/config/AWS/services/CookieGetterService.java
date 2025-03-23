package config.AWS.services;

import config.AWS.manager.AWSFileHandler;
import config.AWS.model.CookieModel;
import org.openqa.selenium.Cookie;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CookieGetterService {

    /// Get Cookie list
    public static List<Cookie> getAWSCookieList() {
        return getCookieList(CookieGetterService::convert);
    }

    // PRIVATE ⚙️
    /// Converts a list of CookieModel objects to a list of Selenium Cookie objects.
    private static List<Cookie> convert(List<CookieModel> cookieModelList) {
        return cookieModelList.stream()
                .map(cookieModel -> new Cookie(
                        cookieModel.getName(),
                        cookieModel.getValue(),
                        cookieModel.getDomain(),
                        cookieModel.getPath(),
                        null
                )).collect(Collectors.toList());
    }

    /// Generic method to safely retrieve config values
    private static List<Cookie> getCookieList(I_CookieDataGetter getter) {
        try {
            return getter.get(AWSFileHandler.readAWSTokensFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}