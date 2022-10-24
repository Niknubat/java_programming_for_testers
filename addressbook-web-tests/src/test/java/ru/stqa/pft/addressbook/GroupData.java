package ru.stqa.pft.addressbook;

// такое исполнение(строка ниже) предложила сама IDE
//public record GroupData(String name, String header, String footer) {}

// Так используется в курсе(более расширенно)
// объект с 3-мя атрибутами, есть конструктор и три метода возвращающие эти атрибуты
public class GroupData {
    private final String name;
    private final String header;
    private final String footer;

    public GroupData (String name, String header, String footer) {
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public String getName() { return name; }
    public String getHeader() { return header; }
    public String getFooter() { return footer; }
}