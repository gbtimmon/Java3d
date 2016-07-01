package com.gg;

import java.util.ArrayList;
import java.util.List;

import Thing.Thing;



class Map {
	
	private Map ( ) { } 
	
	private static List<Thing> thing = new ArrayList<Thing>(100);

	static final float MAP_SIZE_X = 1000.0f;
	static final float MAP_SIZE_Y = 1000.0f;
	
	public static void registerObejct(  Thing go ){
		thing.add(go);
	}
	
	public static void deregisterObject( Thing go ) {
		
	}
	
	public static List<Thing> list ( ){
		return thing;
	}
}