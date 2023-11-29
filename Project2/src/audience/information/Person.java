package audience.information;

import java.util.Scanner;

public class Person {
	final static Scanner scan = new Scanner(System.in);// Scanner
	public static int age;
	public static String fullname;

	public Person(int age, String fullname) {// Constructor
		super();
		Person.age = age;
		Person.fullname = fullname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		Person.age = age;
	}

	public static String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		Person.fullname = fullname;
	}
}
