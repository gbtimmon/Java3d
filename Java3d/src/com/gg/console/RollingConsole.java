package com.gg.console;

public class RollingConsole extends Console{
	int top = 0; 
	
	public RollingConsole ( int size )  { 
		super(size);
	}
	
	public String getLine( int i ) { 
		return line[(i + top) % size]; 
	}
	
	public void setLine( int i , String s ){
		line[ (i + top) % size ] = s; 
	}
	
	public void appendLine( String s){
		line[ top ] = s; 
		top = ((top + 1) % size); 
	}
}
