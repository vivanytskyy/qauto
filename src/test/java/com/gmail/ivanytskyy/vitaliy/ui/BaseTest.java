package com.gmail.ivanytskyy.vitaliy.ui;

import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import com.gmail.ivanytskyy.vitaliy.utils.TestPropertiesSupplier;
import com.gmail.ivanytskyy.vitaliy.ui.utils.WebDriverHolder;
import io.github.bonigarcia.wdm.WebDriverManager;
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
public class BaseTest {
    protected WebDriver webDriver;
    private static final String LOGIN;
    private static final String PASSWORD;
    protected static final String BASE_URL;
    private static final String PATH_TO_DOWNLOADS_FOLDER;
    static {
        LOGIN = TestPropertiesSupplier.getInstance().getProperty("site_login");
        PASSWORD = TestPropertiesSupplier.getInstance().getProperty("site_password");
        BASE_URL = "https://" + LOGIN + ":" + PASSWORD + "@qauto.forstudy.space";
        PATH_TO_DOWNLOADS_FOLDER = new File("target" + File.separator + "downloads") .getAbsolutePath();
    }
    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String browser){
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
    @AfterMethod(alwaysRun = true)
    public void postCondition(){
        WebStorage webStorage = (WebStorage) webDriver;
        webStorage.getSessionStorage().clear();
        webDriver.manage().deleteAllCookies();
    }
    @AfterClass(alwaysRun = true)
    public void tearDown(){
        if(webDriver != null){
            webDriver.quit();
        }
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
        //profile.setPreference("network.automatic-ntlm-auth.trusted-uris", "https://qauto2.forstudy.space");
        //profile.setPreference("dom.disable_beforeunload", false);
        return new FirefoxOptions().setProfile(profile);
    }
}