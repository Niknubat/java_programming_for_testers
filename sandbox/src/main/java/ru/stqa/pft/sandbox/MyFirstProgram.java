package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {
		System.out.println("Hello, world!");		//	Hello, world!

		System.out.println(2 + 2);		//	4
		System.out.println(2 * 2);		//	4
		System.out.println(2 / 2);		//	1
		System.out.println(2 - 2);		//	0

		System.out.println(1 / 2);		//	0
		System.out.println(1.0 / 2);		//	0.5
		System.out.println(1 / 2.0);		//	0.5
		System.out.println(1.0 / 2.0);		//	0.5

		System.out.println("2" + "2");		//	22
		System.out.println("2" + 2);		//	22
		System.out.println(2 + "2");		//	22

		System.out.println(2 + 2 * 2);		//	6
		System.out.println((2 + 2) * 2);		//	8

		System.out.println("2 + 2 = " + 2 + 2);		//	2 + 2 = 22
		System.out.println("2 + 2 = " + (2 + 2));		//	2 + 2 = 4
	}
}