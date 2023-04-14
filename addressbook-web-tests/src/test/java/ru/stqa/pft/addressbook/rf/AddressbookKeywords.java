package ru.stqa.pft.addressbook.rf;

import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.IOException;

public class AddressbookKeywords {

    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    private ApplicationManager app;

    public void initApplicationManager() throws IOException {
        app = new ApplicationManager(System.getProperty("browser", "firefox"));
        app.init();
    }

    public void stopApplicationManager() throws InterruptedException {
        app.stop();
        app = null;
    }

    public int getGroupCount() {
        app.goTo().groupPage();
        return app.group().count();
    }

    public void createGroup(String name, String header, String footer) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(name).withHeader(header).withFooter(footer));
    }
}