package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

    @Test
    public void testArea() {
        Square s = new Square(5);
        assert s.area() == 25;
    }
    @Test
    public void testArea2() {
        Square s = new Square(6);
        Assert.assertEquals(s.area(), 36.0);
    }
}
