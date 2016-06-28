package com.gg;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Mesh {
	
	Vec[]      vertex;
	Triangle[] triangle;
	int[]      r;
	int[]      g;
	int[]      b;
	
	public Mesh clone() { 
		Mesh m = new Mesh();
		m.triangle = this.triangle.clone();
		m.vertex   = new Vec[this.vertex.length];
		
		IntStream.range(0, vertex.length).forEach( i ->{
			m.vertex[i] = new Vec( this.vertex[i]);
		});
		return m;
	}
	
	public void draw( Graphics g ){
		Arrays.stream(triangle).forEach( t-> {
			VecDraw.Tri(g, vertex[t.index[0]], vertex[t.index[1]], vertex[t.index[2]]);
		});
	}
}
