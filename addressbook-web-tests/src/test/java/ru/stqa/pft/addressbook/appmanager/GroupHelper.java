package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);

    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        // Данным способом(заком строка) выполнила IDE что тоже работает + в классе тоже отличия
      // wd.findElement(By.name("group_name")).sendKeys(groupData.name());
        type(By.name("group_header"), groupData.getHeader());
        // Данным способом(заком строка) выполнила IDE что тоже работает + в классе тоже отличия
      // wd.findElement(By.name("group_header")).sendKeys(groupData.header());
        type(By.name("group_footer"), groupData.getFooter());
        // Данным способом(заком строка) выполнила IDE что тоже работает + в классе тоже отличия
      // wd.findElement(By.name("group_footer")).sendKeys(groupData.footer());
    }

    public void initGroupCrreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }
}
