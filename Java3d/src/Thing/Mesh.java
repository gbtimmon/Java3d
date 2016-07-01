package Thing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.stream.IntStream;

import com.gg.VecDraw;

public class Mesh {
	
	
	public Vec [] 			vertex;
	Triangle [] 	triangle;
	Color 			color 	= Color.WHITE;
	boolean 		fill 	= false; //TODO -- This is a hack fix. 
	                                 // will be taken out sometime. 
	
	
	/** A Default mesh object. 
	 *              Stored once. 
	 */
	public static final Mesh DEFAULT;
	static {
		System.out.println("static initializer called");
		DEFAULT 			= new Mesh();
		DEFAULT.vertex 		= new Vec[4];
		DEFAULT.triangle 	= new Triangle[4];

		DEFAULT.vertex[0] 	= new Vec( 1.0f,  1.0f);
		DEFAULT.vertex[1] 	= new Vec( 1.0f, -1.0f);
		DEFAULT.vertex[2] 	= new Vec(-1.0f, -1.0f);
		DEFAULT.vertex[3] 	= new Vec(-1.0f,  1.0f);
		
		DEFAULT.triangle[0] = new Triangle(0, 1, 2);
		DEFAULT.triangle[1] = new Triangle(0, 1, 3);
		DEFAULT.triangle[2] = new Triangle(1, 2, 3);
		DEFAULT.triangle[3] = new Triangle(0, 2, 3);
		System.out.println(DEFAULT.toString());
	}
	
	/**
	 * <p>public Mesh clone() </p>
	 * <br>
	 * Creates a copy of the mesh. 
	 */
	
	public void draw( Graphics g ){
		Arrays.stream(triangle).forEach( t -> { 
			VecDraw.Tri(g, vertex[t.index[0]], vertex[t.index[1]], vertex[t.index[2]]);
		});
	}
	
	public Mesh clone() { 
		Mesh m = new Mesh();
		m.triangle = this.triangle.clone();
		m.vertex   = new Vec[this.vertex.length];
		
		IntStream.range(0, vertex.length).forEach( i ->{
			m.vertex[i] = new Vec( this.vertex[i]);
		});
		return m;
	}
	
	public String toString( ) { 
		StringBuilder sb = new StringBuilder(); 
		Arrays.stream(vertex).forEach( v -> { 
			sb.append(String.format( "v %f %f\n", v.x(), v.y()));
		});
		
		Arrays.stream(triangle).forEach(t->{
			sb.append(String.format("t %d %d %d\n", t.index[0], t.index[1], t.index[2]));
		});
		
		return sb.toString();
	}
}
