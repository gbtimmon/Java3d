package Thing;

import java.util.Arrays;
import Motivator.Motivator;

public class Thing {
	
	public static int GOBJECT_ID_GENERATOR = 1;

	//The unique id of the object
	int 		id 			     = GOBJECT_ID_GENERATOR++;
	
	//The underlying render of the Object
	Mesh 		mesh 		     = Mesh.DEFAULT;
	
	//The Motivators of the object;
	int         motiv_count      = 0; 
	Motivator[] motiv  		     = new Motivator[3]; 
	
	//The physical stats of the object. 
	//scale position and rotation. 
	public float 	scale		  = 1.0f;
	public boolean  isStationary  = false; //short circuit for motion calc.
	public boolean  hasFocus      = false; 
	public Vec 		position	  = new Vec(0.0f, 0.0f);
	public Vec 		rotation      = new Vec(1.0f, 0.0f).unit();
	public float    speed         = 0.0f; 
	public float    tspeed        = 0.0f; 
	
	
	
	public Mesh  mappedMesh;		 
	public Vec   mappedMeshPostion;   //short circuit for mesh generation
	public Vec   mappedMeshDirection;  //for mesh generation. 
	public float mappedMeshScale;     //or I could say, caching 
	
	public Thing ( Vec position, float scale ) {
		this.mesh 		= Mesh.DEFAULT.clone();
		this.position 	= position;
		this.scale 		= scale;
	}
	
	
	public Mesh getMesh() { 
		
		if( mappedMesh 				== null 		 ||
		    mappedMeshPostion.x() 	!= position.x()  ||
		    mappedMeshPostion.y() 	!= position.y()  ||
		    mappedMeshDirection.x() != rotation.x() ||
		    mappedMeshDirection.y()	!= rotation.y() ||
		    mappedMeshScale		    != scale ){
			
			mappedMesh 			= mesh.clone();
			mappedMeshPostion 	= new Vec(position);
			mappedMeshDirection = new Vec(rotation);
			mappedMeshScale 	= scale;
			
			Arrays.stream(mappedMesh.vertex).forEach( v -> { 
				v.rotate(rotation).mult(scale).add(position);
			});
		}
		return mappedMesh;
	}
	
	public Mesh getRawMesh() { 
		return mesh.clone();
	}
	
	// change place in time based on current stats. 
	public void heartbeat( long gameStep ) { 
		
		Arrays.stream(motiv).forEach( m -> {if( m != null ) m.act(this, gameStep);});
		
		rotation.rotate(tspeed * gameStep); 
		position.add( new Vec( 1.0f, 0.0f).rotate(rotation).mult(speed * gameStep) ); 
	}
	
	public String toString( ) { 
		StringBuilder sb = new StringBuilder( ) ; 
		
		sb.append("THING\n");
		sb.append("id         : " + id + "\n" );
		sb.append("position   : v " + position.x() + " " + position.y() + "\n");
		sb.append("rotation   : v " + rotation.x() + " " + rotation.y() + " rads : " + rotation.radians() + "\n");
		sb.append("scale      : " + scale + "\n" ) ;
		sb.append("MESH\n");
		sb.append((mesh == null) ? "NULL\n" : getRawMesh().toString());
		sb.append((mesh == null) ? "NULL\n" : getMesh().toString());
		
		return sb.toString();
	}
	
	public void addMotivator( Motivator m){
		
		if( motiv_count + 1 == motiv.length){
			Motivator[] nm = new Motivator[ motiv.length * 2];
			
			for( int i = 0; i < motiv.length; i++ ) 
				nm[i] = motiv[i]; 
			
			motiv = nm; 
		}
		
		motiv[motiv_count++] = m;
	}


	public void setFocus( boolean f ) {
		this.hasFocus = f; 
	}
	
	public boolean hasFocus() {
		return hasFocus;
	}
}
