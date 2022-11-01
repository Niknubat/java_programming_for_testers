package ru.stqa.pft.addressbook.model;

// такое исполнение(строка ниже) предложила сама IDE
//public record GroupData(String name, String header, String footer) {}

import java.util.Objects;

// Так используется в курсе(более расширенно)
// объект с 3-мя атрибутами, есть конструктор и три метода возвращающие эти атрибуты
public class GroupData {
    private int id = Integer.MAX_VALUE;
    private String name;
    private String header;
    private String footer;

    public int getId() { return id; }

    public GroupData withId(int id) {
        this.id = id;
        return this;
    }

    public GroupData withName(String name) {
        this.name = name;
        return this;
    }

    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public String getName() { return name; }
    public String getHeader() { return header; }
    public String getFooter() { return footer; }

    @Override
    public String toString() {
        return "GroupData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return id == groupData.id && Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}