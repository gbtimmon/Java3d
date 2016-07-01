package com.gg;

import Thing.Vec;

public class Tester {
	public static void main( String ... args ) { 
		System.out.println(new Vec( 1.0f,  0.0f).unit().radians() / Math.PI); 
		System.out.println(new Vec( 1.0f, -1.0f).unit().radians() / Math.PI); 
		System.out.println(new Vec( 0.0f, -1.0f).unit().radians() / Math.PI); 
		System.out.println(new Vec(-1.0f, -1.0f).unit().radians() / Math.PI); 
		System.out.println(new Vec(-1.0f,  0.0f).unit().radians() / Math.PI); 
		System.out.println(new Vec(-1.0f,  1.0f).unit().radians() / Math.PI); 
		System.out.println(new Vec( 0.0f,  1.0f).unit().radians() / Math.PI); 
		System.out.println(new Vec( 1.0f,  1.0f).unit().radians() / Math.PI); 
	}
}
