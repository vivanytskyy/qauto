package com.gmail.ivanytskyy.vitaliy.ui;

import com.github.javafaker.Faker;
import com.gmail.ivanytskyy.vitaliy.listeners.UIExtentReportsListener;
import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import com.gmail.ivanytskyy.vitaliy.utils.PasswordGenerateService;
import com.gmail.ivanytskyy.vitaliy.utils.TestPropertiesSupplier;
import com.gmail.ivanytskyy.vitaliy.ui.utils.WebDriverHolder;
import com.gmail.ivanytskyy.vitaliy.utils.UserAuthorizationService;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.html5.WebStorage;
import org.testng.annotations.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 30/06/2023
 */
@Listeners({UIExtentReportsListener.class})
public class BaseTest {
    protected WebDriver webDriver;
    protected TempUser tempUser;
    private static final String LOGIN;
    private static final String PASSWORD;
    protected static final String BASE_URL;
    private static final String PATH_TO_DOWNLOADS_FOLDER;
    private static final String USER_FIRST_NAME;
    private static final String USER_LAST_NAME;
    private static final String USER_EMAIL;
    private static final String USER_PASSWORD;
    static {
        LOGIN = TestPropertiesSupplier.getInstance().getProperty("site_login");
        PASSWORD = TestPropertiesSupplier.getInstance().getProperty("site_password");
        BASE_URL = "https://" + LOGIN + ":" + PASSWORD + "@qauto.forstudy.space";
        PATH_TO_DOWNLOADS_FOLDER = new File("target" + File.separator + "downloads") .getAbsolutePath();
        USER_FIRST_NAME = TestPropertiesSupplier.getInstance().getProperty("user_first_name");
        USER_LAST_NAME = TestPropertiesSupplier.getInstance().getProperty("user_last_name");
        USER_EMAIL = TestPropertiesSupplier.getInstance().getProperty("user_email");
        USER_PASSWORD = TestPropertiesSupplier.getInstance().getProperty("user_password");
    }
    @BeforeTest
    public void authorizeUser(){
        UserAuthorizationService.authorizeUser();
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
        this.tempUser = null;
    }
    @AfterClass(alwaysRun = true)
    public void tearDown(){
        if(webDriver != null){
            webDriver.quit();
        }
    }
    @AfterTest(alwaysRun = true)
    public void deleteUser(){
        UserAuthorizationService.deleteUser();
    }
    protected MainPage openApp(){
        webDriver.get(BASE_URL);
        return new MainPage();
    }
    protected boolean isFileDownloaded(File file) throws InterruptedException {
        long waitingTime = 12000;
        long timeStep = 3000;
        do {
            waitingTime -= timeStep;
            if(waitingTime <= 0) return false;
            Thread.sleep(timeStep);
        } while(!file.exists());
        return true;
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
    protected String getUserFirstName(){
        return USER_FIRST_NAME;
    }
    protected String getUserLastName(){
        return USER_LAST_NAME;
    }
    protected String getUserEmail(){
        return USER_EMAIL;
    }
    protected String getUserPassword(){
        return USER_PASSWORD;
    }
    protected void createUser(String firstName, String lastName, String email, String password){
        openApp()
                .openSingUpBox()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setReEnterPassword(password)
                .clickRegisterButtonPositiveCase()
                .moveToUserSidebar()
                .logout();
    }
    protected void deleteUserThroughSidebar(String email, String password){
        openApp()
                .openSingInBox()
                .setEmail(email)
                .setPassword(password)
                .clickLoginButtonPositiveCase()
                .moveToUserSidebar()
                .openSettings()
                .removeAccount()
                .clickRemove();
        webDriver.manage().deleteAllCookies();
    }
    protected void deleteUserThroughDropdown(String email, String password){
        openApp()
                .openSingInBox()
                .setEmail(email)
                .setPassword(password)
                .clickLoginButtonPositiveCase()
                .openUserProfileDropdown()
                .openSettings()
                .removeAccount()
                .clickRemove();
        webDriver.manage().deleteAllCookies();
    }
    @Data
    protected static class TempUser {
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String password;
        private TempUser(){
            Faker faker = new Faker();
            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            email = faker.internet().emailAddress();
            password = new PasswordGenerateService.Builder()
                    .useDigits(true)
                    .useUpperCaseLetters(true)
                    .useLowerCaseLetters(true)
                    .build()
                    .generatePassword(8, 15);
        }
    }
}