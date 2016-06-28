package com.gg;

import java.util.LinkedList;
import java.util.List;


class Map {
	
	private Map ( ) { } 
	
	static Mesh        map    = new Mesh();            
	static List<Actor> act    = new LinkedList<Actor>();
	
	static final float MAP_SIZE_X = 1000.0f;
	static final float MAP_SIZE_Y = 1000.0f;
	
	static void getRandomMap( ) {
		//TODO TODO TODO.
	}
	
	static void loadMapFile( ) {
		//TODO TODO TODO
	}
	
	static void hardCoded( ) { 
		map = new Mesh();
		
		map.vertex = new Vec[4];
		map.triangle = new Triangle[2];
		
		map.vertex[0] = new Vec(0.0f,       0.0f);
		map.vertex[1] = new Vec(0.0f,       10.0f);
		map.vertex[2] = new Vec(MAP_SIZE_Y, 10.0f);
		map.vertex[3] = new Vec(MAP_SIZE_Y, 0.0f);
		
		map.triangle[0] = new Triangle(0, 1, 2 );
		map.triangle[1] = new Triangle(2, 3, 0 );
		
	}

}