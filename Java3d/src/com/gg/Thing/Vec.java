package com.gg.Thing;

import java.util.Arrays;

import com.gg.Config;

public class Vec {
	
	float[] e = new float[2];
	public static Vec square( float n ){
		return new Vec(n,n);
	}
	
	public Vec( Vec v ){
		this( v.asArray() );
	}
	
	public Vec( float ... v ){
		e = Arrays.copyOf(v, 2);
	}
	
	public Vec( int ... i ){
		e[0] = (float) i[0];
		e[1] = (float) i[1];
	}
	public float mag() {
		return (float) Math.pow( e[0] * e[0] + e[1] * e[1] , .5);
	} 
	
	public Vec unit() { 
		float m = this.mag();
		e[0] = e[0] / m;
		e[1] = e[1] / m;
		return this; 
	}
	
	public Vec unitN() { 
		Vec n = new Vec( this );
		float m = n.mag();
		n.x( n.x() / m );
		n.y( n.y() / m );
		return n; 
	}
	
	public Vec rotate(Vec rotation) {
		return this.rotate( rotation.unitN().radians() );
	}
	
	
	public Vec rotate( float rads ){
		float a = (float) Math.cos(rads) * e[0] - (float) Math.sin(rads) * e[1];
		float b = (float) Math.sin(rads) * e[0] + (float) Math.cos(rads) * e[1];
		e[0] = a;
		e[1] = b;
		return this; 
	}
	
	public Vec rotateN( Vec rotation) {
		return this.rotateN( rotation.unitN().radians() );
	}
	public Vec rotateN( float rads ){
		Vec n = new Vec(this);
		n.x((float) Math.cos(rads) * e[0] - (float) Math.sin(rads) * e[1]);
		n.y((float) Math.sin(rads) * e[0] + (float) Math.cos(rads) * e[1]);
		return n;
	}
	public float x () { 
		return e[0];
	}
	
	public float y () {
		return e[1];
	}
	
	public float x( float f ){
		e[0] = f;
		return f; 
	}
	
	public float y( float f ){
		e[1] = f;
		return f; 
	}
	
	public float[] asArray( ){
		return e; 
	}
	
	public Vec add( Vec v){
		return this.add(v.asArray());
		
	}
	
	public Vec add( float ... f){
		e[0] += f[0];
		e[1] += f[1];
		return this; 
	}
	
	public Vec addN( Vec v){
		return addN( v.asArray() );
	}
	
	public Vec addN( float ...f ){
		Vec n = new Vec(this);
		n.x( n.x() + f[0]);
		n.y( n.y() + f[1]);
		return n;
	}

	public Vec sub( Vec v){
		return this.sub(v.asArray());
		
	}
	
	public Vec sub( float ... f){
		e[0] -= f[0];
		e[1] -= f[1];
		return this; 
	}
	
	public Vec subN( Vec v){
		return subN( v.asArray() );
	}
	
	public Vec subN( float ...f ){
		Vec n = new Vec(this);
		n.x( n.x() - f[0]);
		n.y( n.y() - f[1]);
		return n;
	}

	public Vec mult( float f ){
		e[0] *= f; 
		e[1] *= f; 
		return this; 
	}
	
	public Vec multN( float f ){
		Vec n = new Vec( this );
		n.x( n.x() * f);
		n.y( n.y() * f);
		return n;
	}
	
	public float radians(){
		if( e [ 1 ]  > -0.0000000000001)
			return (float) Math.acos(e[0]);
		else 
			return (float) ((2 * Config.PI) - Math.acos(e[0]));
	}
	
	public Vec mapRelativeVector( Vec loc, float rot, float sz){
		return this.rotate(rot).mult(sz).add(loc);
	}
	
	public void toConsole(){
		System.out.print(String.format( "<%f, %f>\n", e[0], e[1]));
	}


	
	
	
}
