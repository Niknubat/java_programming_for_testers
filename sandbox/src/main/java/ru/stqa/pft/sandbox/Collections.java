package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "Python", "PHP"};

        //Ниже операции с превращением массива в список Arrays.asList()

        // Строгое указание типа данных в списке
        List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
        for (String l : languages) {
            System.out.println("Я хочу выучить " + l);
        }

        // Можно сделать без указания тиа данных в списке и тогда указываем Object и обращаться по индексу
        // Самый удобный для перебора коллекции
        List languages1 = Arrays.asList("Java", "C#", "Python", "PHP");
        for (Object l1 : languages1) {
            System.out.println("Я хочу выучить " + l1);
        }

        // Получение элементов через индекс
        List<String> languages2 = Arrays.asList("Java", "C#", "Python", "PHP");
        for (int i = 0; i < languages2.size(); i++) {
            System.out.println("Я хочу выучить " + languages2.get(i));
        }
    }
}
