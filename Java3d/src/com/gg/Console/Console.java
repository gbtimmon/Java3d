package com.gg.Console;


//* Can I make this dependency injected to make it so I can call console uniform through out with vaired behaior based on config ??
// Lets come back to it. ,.... 

public class Console {

	protected int		 size = 0; 
	protected String[] line = null; 
	
	public Console( int size ) { 
		line = new String[size];
		this.size = size; 
	}
	
	public String getLine( int i ){
		if( i >= size ) 
			throw new IllegalArgumentException("String index out of bounds : " + i 
					                       + "\nMax available index is " + size);
		return line[i];
	}
	
	public void setLine(int i, String s) { 
		if( i >= size ) 
			throw new IllegalArgumentException("String index out of bounds : " + i 
					                       + "\nMax available index is " + size);
		
		line[i] = s; 
	}
	
	public int getSize( ) { 
		return size;  
	}
}
