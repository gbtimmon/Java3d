package com.gg;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;


public class Game extends Canvas {

	String[] console = new String[] {"","","","","","","","","",""};

	
	private static final long serialVersionUID = 1L;

	private Graphics g;
	public Actor player;
	private long GAME_TIME;
	private long GAME_STEP;
	
	// METHODS
	public void start() {

		this.g = getGraphics();
		this.player = new Actor( new Vec(50.0f, 50.0f), Config.PI, 10.0f);
		Map.act.add( this.player );
		Map.hardCoded();
		
		new Thread() {
			public void run() {
				GAME_TIME = System.nanoTime();
				while (true) {
					GAME_STEP = System.nanoTime() - GAME_TIME;
					GAME_TIME = GAME_TIME + GAME_STEP;
					GAME_STEP /= 10000;
					
					g.drawImage(renderImage(), 0, 0, null);
					processKeys();
					checkRules();
					setInfo();
				}
			}
		}.start();
	}
	
	
	private BufferedImage renderImage() {
		BufferedImage bf = new BufferedImage(Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT + 50 + Config.INFO_PANEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics bg = bf.getGraphics();
		
		
		Map.map.draw(bg);
		
		// ACTORS
		for( Actor a : Map.act )
			a.getMesh().draw(bg);

	
		
		bg.fillRect(0, Config.CANVAS_HEIGHT, Config.CANVAS_WIDTH, 400);
		bg.setColor(Color.BLUE);
		IntStream.range(0,10).forEach( x -> {
			bg.drawString( String.format( "%02d : %s", x+ 1, console[x]), 15, Config.CANVAS_HEIGHT  + (15 * x) + 15);
		});

		
		return bf;
	}

	public void paint(final Graphics g) {
		/* undefined */}


	
	public void processKeys() {

		while (Input.hasEvent()) {
			switch( Input.consumeEvent() ){
				case Input.K_TOGGLE_RENDER_BCK : 
					Config.is3DView = ! Config.is3DView;
					break;
				case Input.K_TOGGLE_RENDER_FWD :
					Config.is3DView = ! Config.is3DView;
					break;
			}
		}
	
		if( Input.Up() )
			player.speed += (player.accel * (float) GAME_STEP) * ((player.maxS - player.speed) / player.maxS)
					;
		
		if( Input.Down() )
			player.speed -= (player.accel * (float) GAME_STEP) * ((player.maxS - player.speed) / player.maxS);
		
		if( Input.TurnLeft())
			player.turn( - player.turnSpeed * GAME_STEP);
		
		if( Input.TurnRight())
			player.turn( + player.turnSpeed * GAME_STEP);
		
		if( Input.StrafeLeft()){
			player.size -= 0.00006f * GAME_STEP;
		}
		
		if( Input.StrafeRight()){
			player.size += 0.00006f * GAME_STEP;
		}
		
		if( player.speed > 0) player.speed = Math.max( 0.0f, player.speed - (player.dragc * GAME_STEP));
		if( player.speed < 0) player.speed = Math.min( 0.0f, player.speed + (player.dragc * GAME_STEP));
		
		player.location.add( player.direction.multN(player.speed));
		
	};
	
	private void checkRules() {
		
	};
	
	private void setInfo() { 
		console[0] = " player speed : " + player.speed + " player rotation : " + player.rotation;
		console[1] = " player accel : " + player.accel + " player turnspeed : " + player.turnSpeed;
		console[2] = " player maxS  : " + player.maxS + " player accel modifier : " + ((player.maxS - player.speed) / player.maxS);
		console[3] = " player size  : " + player.size;
		console[4] = " game step    : " + GAME_STEP;
	};
}