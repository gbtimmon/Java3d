package com.gg.Motivator;

import com.gg.Input;
import com.gg.Thing.Thing;
import com.gg.Thing.Vec;

public class TessalatorMotivator implements Motivator{

	float tessalationSpeed;
	float rotationSpeed;
	
	public TessalatorMotivator( float tessalationSpeed , float rotationSpeed){
		this.tessalationSpeed = tessalationSpeed;
		this.rotationSpeed = rotationSpeed;
	
	}
	
	public void act( Thing thing, long step){
		if( thing.hasFocus()){
			if( Input.Up())
				thing.position.add(  new Vec( 0.0f, -1.0f).mult( tessalationSpeed * step));
			
			if( Input.Down())
				thing.position.add(  new Vec( 0.0f,  1.0f).mult( tessalationSpeed * step));
			
			if( Input.Left())
				thing.position.add(  new Vec(-1.0f,  0.0f).mult( tessalationSpeed * step));
			
			if( Input.Right())
				thing.position.add(  new Vec( 1.0f,  0.0f).mult( tessalationSpeed * step));
			
			if( Input.StrafeLeft())
				thing.rotation.rotate(rotationSpeed * step);
			
			if( Input.StrafeRight())
				thing.rotation.rotate(-rotationSpeed * step);
		}
	}
}
