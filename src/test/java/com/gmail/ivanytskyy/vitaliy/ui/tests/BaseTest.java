package com.gmail.ivanytskyy.vitaliy.ui.tests;

import com.github.javafaker.Faker;
import com.gmail.ivanytskyy.vitaliy.api.exceptions.UnexpectedHttpStatusCodeException;
import com.gmail.ivanytskyy.vitaliy.api.utils.ApiPropertiesSupplier;
import com.gmail.ivanytskyy.vitaliy.ui.listeners.UIExtentReportsListener;
import com.gmail.ivanytskyy.vitaliy.ui.pages.HomePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserGaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.utils.UICookieHolder;
import com.gmail.ivanytskyy.vitaliy.utils.PasswordGenerateService;
import com.gmail.ivanytskyy.vitaliy.utils.TestPropertiesSupplier;
import com.gmail.ivanytskyy.vitaliy.ui.utils.WebDriverHolder;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Data;
import okhttp3.*;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.html5.WebStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static com.gmail.ivanytskyy.vitaliy.api.utils.ControllerNames.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.07
 * @date 11/01/2024
 */
@Listeners({UIExtentReportsListener.class})
public class BaseTest {
    protected WebDriver webDriver;
    protected TempUser tempUser;
    protected final String cookieName = "sid";
    protected static final String BASE_URL;
    private static final String LOGIN;
    private static final String PASSWORD;
    private static final String PATH_TO_DOWNLOADS_FOLDER;
    private static final String API_BASE_URL;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    static {
        LOGIN = TestPropertiesSupplier.getInstance().getProperty("site_login");
        PASSWORD = TestPropertiesSupplier.getInstance().getProperty("site_password");
        BASE_URL = "https://" + LOGIN + ":" + PASSWORD + "@qauto.forstudy.space";
        PATH_TO_DOWNLOADS_FOLDER = new File("target" + File.separator + "downloads") .getAbsolutePath();
        API_BASE_URL = ApiPropertiesSupplier.getInstance().getProperty("api_base_url");
    }

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser){
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver(getChromeOptions());
        }else if(browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver(getFirefoxOptions());
        }else{
            throw new IllegalArgumentException("Incorrect browser name");
        }
        webDriver.manage().window().maximize();
        WebDriverHolder.setWebDriver(webDriver);
    }
    @BeforeMethod
    public void preCondition(){
        this.tempUser = new TempUser();
    }
    @AfterMethod(alwaysRun = true)
    public void postCondition(){
        WebStorage webStorage = (WebStorage) webDriver;
        webStorage.getSessionStorage().clear();
        webDriver.manage().deleteAllCookies();
        Cookie cookie = UICookieHolder.getCookie();
        if(cookie != null){
            try {
                deleteUserByApi(cookie);
                UICookieHolder.setCookie(null);
            } catch (IOException e) {
                LOGGER.error("Exception occurred: ", e);
            }
        }
        this.tempUser = null;
    }
    @AfterClass(alwaysRun = true)
    public void tearDown(){
        if(webDriver != null){
            webDriver.quit();
        }
    }
    private ChromeOptions getChromeOptions(){
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", PATH_TO_DOWNLOADS_FOLDER);
        return new ChromeOptions().setExperimentalOption("prefs", prefs);
    }
    private FirefoxOptions getFirefoxOptions(){
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", PATH_TO_DOWNLOADS_FOLDER);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/zip");
        return new FirefoxOptions().setProfile(profile);
    }
    protected HomePage openApp(){
        webDriver.get(BASE_URL);
        return new HomePage();
    }
    protected void deleteUserThroughSidebar(String email, String password){
        openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .setEmail(email)
                .setPassword(password)
                .clickLoginButtonPositiveCase()
                .moveToSidebar()
                .openSettings()
                .removeAccount()
                .clickRemove();
        webDriver.manage().deleteAllCookies();
    }
    protected void deleteUserThroughDropdown(String email, String password){
        openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .setEmail(email)
                .setPassword(password)
                .clickLoginButtonPositiveCase()
                .moveToHeader()
                .openUserProfileDropdown()
                .openSettings()
                .removeAccount()
                .clickRemove();
        webDriver.manage().deleteAllCookies();
    }
    protected String deleteUserByApi(Cookie cookie) throws IOException {
        String url = API_BASE_URL + USERS.getPath();
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie", String.format("%s=%s", cookieName, cookie.getValue()))
                .delete()
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if(response.code() != 200){
                throw new UnexpectedHttpStatusCodeException(response.code());
            }
            assert response.body() != null;
            JSONObject jsonObject = new JSONObject(response.body().string());
            return jsonObject.getString("status");
        }
    }
    protected void userPreRegistrationByUI(TempUser tempUser){
        signUpAsTempUser(tempUser)
                .moveToSidebar()
                .logout();
    }
    protected UserGaragePage signUpAsTempUser(TempUser tempUser){
        return openApp()
                .openSingUpBox()
                .registerPositiveCase(
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getEmail(),
                        tempUser.getPassword());
    }
    @Data
    public static class TempUser {
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String password;
        private final String country;
        private final Date birthday;

        private TempUser(){
            Faker faker = new Faker();
            firstName = faker.name().firstName().replace("'", "");
            lastName = faker.name().lastName().replace("'", "");
            email = faker.internet().emailAddress();
            password = new PasswordGenerateService.Builder()
                    .useDigits(true)
                    .useUpperCaseLetters(true)
                    .useLowerCaseLetters(true)
                    .build()
                    .generatePassword(8, 15);
            country = faker.country().name();
            birthday = faker.date().birthday();
        }
    }
}