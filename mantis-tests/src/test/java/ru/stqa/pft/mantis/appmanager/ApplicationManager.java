package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;

    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) { // обеспечивает отсутствие повторонй инициализации
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) { // если помощник еще не инициал-ан, то...
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() { // метод ленивой инициализации браузера.
        // драйвер будет инициализ. в тот момент, когда к нему кто-то обратится
        if (wd == null) {
            if (browser.equals("ChromeDriver")) {
                wd = new ChromeDriver();
            } else if (browser.equals("FirefoxDriver")) {
                wd = new FirefoxDriver();
            } else if (browser.equals("InternetExplorerDriver")) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
}