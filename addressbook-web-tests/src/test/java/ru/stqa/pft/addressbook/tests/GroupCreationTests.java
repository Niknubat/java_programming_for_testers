package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
          String xml = "";
          String line = reader.readLine();
          while (line != null) {
              xml += line;
              line = reader.readLine();
          }
          XStream xstream = new XStream();
          xstream.processAnnotations(GroupData.class);
          List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
          return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
          String json = "";
          String line = reader.readLine();
          while (line != null) {
              json += line;
              line = reader.readLine();
          }
          Gson gson = new Gson();
          List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType()); // List<GroupData>.class
          return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
//        Groups before = app.group().all();  //- для списков
        Groups before = app.db().groups(); // получение списка групп до добавления новой группы
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1)); // сравнение количества групп до и после добавления новой группы
//        Groups after = app.group().all();  //- для списков
        Groups after = app.db().groups(); // получение списка групп после добавления новой группы
        assertThat(after, equalTo(
              before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test(enabled=false)
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test'");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();

        assertThat(after, equalTo(before));
    }

    //    Тест который получает данные через UI, а не через БД +
//    в файле groups с тестовыми данными, должны быть переносы строк(Пример: "header": "header\n1")
//    @Test(enabled=false)
//    @Test(dataProvider = "validGroupsFromJson")
//    public void testGroupCreation(GroupData group) {
//        app.goTo().groupPage();
//        Groups before = app.group().all();  //- для списков
//        app.group().create(group);
//        assertThat(app.group().count(), equalTo(before.size() + 1)); // сравнение количества групп до и после добавления новой группы
//        Groups after = app.group().all();  //- для списков
//        assertThat(after, equalTo(
//                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
//    }

}
