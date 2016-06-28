package com.gg;

/* Package only class */
class Config{ 
	private Config(){}
	public static boolean is3DView 			 		= false; 
	public static final int     CANVAS_WIDTH  		= 600;
	public static final int     CANVAS_HEIGHT  		= 600;
	public static final int     BLOCK_SIZE			= 15; 
	public static final int		MAP_SIZE_X			= 50; 
	public static final int 	MAP_SIZE_Y			= 50; 
	public static final float   PI 					= (float) Math.PI;  //This saves me quite a bit of casting from the Math.PI double. 
	public static final int  	INFO_PANEL_HEIGHT 	= 200;
}