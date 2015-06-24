package com.rf.h;

public class A{
	int a0=1;
	void a1(){
		System.out.println("my name is:"+a0);
		System.out.println("");
	}
	
	public class B{
		int b0=2;
		void b1(){
			System.out.println("my name is:"+b0+",my father is:"+a0);
		}
	}
}