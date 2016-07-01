package com.gg.Thing;


public class Wall extends Thing {

	public Wall(Vec start, Vec stop, float thickness) {
		super(new Vec(0.0f, 0.0f), 0.0f);
		
		this.position = start.addN(stop).mult(0.5f);
		Vec path 	  = stop.subN(start);
		
		this.scale			  = path.mag(); 
		this.rotation 		  = path.unit();
		float ratio 		  = thickness / this.scale; 
		
		this.mesh 			  = new Mesh(); 
		this.mesh.vertex   	  = new Vec[4];
		this.mesh.triangle    = new Triangle[2]; 
		
		this.mesh.vertex[0]   = new Vec(-0.5f, -ratio / 2 );
		this.mesh.vertex[1]   = new Vec( 0.5f, -ratio / 2 );
		this.mesh.vertex[2]   = new Vec( 0.5f, +ratio / 2 );
		this.mesh.vertex[3]   = new Vec(-0.5f, +ratio / 2 );
		
		this.mesh.triangle[0] = new Triangle(0,1,2); 
		this.mesh.triangle[1] = new Triangle(0,2,3);
		
		
	}
}
