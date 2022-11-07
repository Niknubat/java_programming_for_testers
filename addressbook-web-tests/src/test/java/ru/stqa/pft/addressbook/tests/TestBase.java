package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", "ChromeDriver"));
//    protected static final ApplicationManager app = new ApplicationManager("InternetExplorerDriver");
//    protected static final ApplicationManager app = new ApplicationManager("FirefoxDriver");

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

}
