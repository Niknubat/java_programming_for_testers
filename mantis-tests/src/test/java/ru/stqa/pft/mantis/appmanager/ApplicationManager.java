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
    WebDriver wd;

    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

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

    public void stop() throws InterruptedException {
//        Thread.sleep(2);
        wd.quit();
    }

}
