package ru.stqa.pft.sandbox;

public class Equality {
    public static void main(String[] args) {
        String s1 = "ChromeDriver";
        String s2 = new String(s1); // Новый объект создается(разные ссылки)
        // String s2 = s1; / оба объекта с одной ссылкой

        System.out.println(s1 == s2); // сравнивает ссылки на объект
        System.out.println(s1.equals(s2)); // сравнивает содержимое ссылок
    }
}
