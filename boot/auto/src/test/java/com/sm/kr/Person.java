package com.sm.kr;



public class Person {
	
	private String name;
	
	public void sayHello() {
		System.out.println("Hello, I'm " + name);
	}

	public static void main(String[] args) {
		Person p = new Person(); //p객체 생성
		p.sayHello();
		//Person.sayHello();

	}

}
