package com.gg.Thing;

import java.awt.Color;

public class Triangle {
	int[] index = new int[3];
	Color d; 
	
	public Triangle(int a, int b, int c) {
		index[0] = a;
		index[1] = b;
		index[2] = c;
		d = Color.WHITE;
	}
	
	public Triangle( int a, int b, int c, Color d){
		index[0] = a;
		index[1] = b;
		index[2] = c;
		this.d 	 = d; 
	}
}