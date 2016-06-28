package com.gg;

import java.util.Arrays;

class Actor {

	private Mesh mesh; 
	
	Actor( Vec location, float rotation, float size) {
		this.location 	= location; 
		this.setOrinetation(rotation);
		this.size 		= size; 
	};

	/* EXAMPLE DEFAULTS */
	Vec location    = new Vec(5.0f, 5.0f);
	Vec direction   = new Vec(1.0f, 0.0f);
	float rotation  = 0.0f;
	float size   	= 20.0f;
	float speed     = 0.0f;
	float accel     = 0.00003f;
	float maxS      = 3.5f;
	float dragc     = 0.000020f;
	float turnSpeed = 0.0001f;

	Mesh getMesh() { 
		if( mesh == null ){ // lazy initialization of Actor mesh 
			                // allows easy override of the Actor class
			 				// with different meshes. 
			
			mesh = new Mesh();
			mesh.triangle = new Triangle[2];
			mesh.vertex   = new Vec[4];
		
			mesh.vertex[0] = new Vec( 0.0f,  0.0f);
			mesh.vertex[1] = new Vec( 1.0f, -0.5f);
			mesh.vertex[2] = new Vec(-1.0f, -0.5f);
			mesh.vertex[3] = new Vec( 0.0f,  1.0f);
			
			mesh.triangle[0] = new Triangle(1, 3, 0);
			mesh.triangle[1] = new Triangle(0, 3, 2); 
		}
		
		Mesh ret = mesh.clone();
		
		Arrays.stream(ret.vertex).forEach( v ->{
			v.mapRelativeVector(location, rotation, size);
		});
		
		return ret; 
	}
	
	public void setOrinetation( float rads ){
		this.rotation 	= rads % ( 2.0f * (float) Math.PI);
		this.direction  = new Vec(0.0f, 1.0f).rotate(this.rotation).unit();
	}
	
	public void turn( float rads ) {
		this.rotation 	= (rotation + rads) % ( 2.0f * (float) Math.PI);
		this.direction  = new Vec(0.0f, 1.0f).rotate(this.rotation).unit();
	}
}