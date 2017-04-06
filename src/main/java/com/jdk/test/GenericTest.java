package com.jdk.test;

public class GenericTest {
	public static void main(String[] args) {
		Box<Integer> tI = new Box<Integer>(1);
		Box<String> ts = new Box<String>("test");
		System.out.println(tI.getData());
		System.out.println(ts.getData());
	}
	
	
}

class Box<T> {
	T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Box(T data) {
		this.data = data;
	}
	
}
