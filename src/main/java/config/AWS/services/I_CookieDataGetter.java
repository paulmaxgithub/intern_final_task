package config.AWS.services;

import config.AWS.model.CookieModel;
import org.openqa.selenium.Cookie;
import java.util.List;

@FunctionalInterface
interface I_CookieDataGetter {
    List<Cookie> get(List<CookieModel> cookieModelList);
}
