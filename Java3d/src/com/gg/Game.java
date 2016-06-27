package com.gg;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.stream.IntStream;

public class Game extends Canvas {

	private static final long serialVersionUID = 1L;

	// FIELDS
	private Graphics g;
	public static boolean view3D 	= false;
	public static int WIDTH 		= 770;
	public static int HEIGHT 		= 790;
	public static int BLOCK_SIZE 	= 15;
	public static int MAP_X 		= 50;
	public static int MAP_Y 		= 50;
	public static Vec BLOCK_VEC 	= Vec.square(BLOCK_SIZE);
	public static Vec GOAL 			= new Vec(	(float) (MAP_Y * Math.random()), 
												(float) (MAP_X * Math.random()));

	public static class Camera {

		private Camera() {
		};

		static Vec loc = new Vec(5.0f, 5.0f);
		static Vec dir = new Vec(1.0f, 1.0f).unit();

		public static Camera byId(int num) {
			return new Camera();
		}
	}

	public static class Map {
		static int height = MAP_X;
		static int width = MAP_Y;
		static Block block[][] = new Block[Map.width][Map.height];
	}

	enum Block {
		OPEN, CLOSED, GOAL
	}

	// METHODS
	public void start() {

		IntStream.range(0, Map.width).forEach(i -> {
			IntStream.range(0, Map.height).forEach(j -> {
				Map.block[i][j] = (Math.random() < 1.000) ? Block.OPEN : Block.CLOSED;
			});
		});

		Map.block[4][4] = Block.OPEN;
		Map.block[4][5] = Block.OPEN;
		Map.block[5][4] = Block.OPEN;
		Map.block[5][5] = Block.OPEN;
		Map.block[5][6] = Block.GOAL;

		IntStream.range(0, MAP_X).forEach(i -> {
			Map.block[0][i] = Block.CLOSED;
			Map.block[MAP_Y - 1][i] = Block.CLOSED;
		});

		IntStream.range(0, MAP_Y).forEach(i -> {
			Map.block[i][0] = Block.CLOSED;
			Map.block[i][MAP_X - 1] = Block.CLOSED;
		});

		this.g = getGraphics();

		new Thread() {
			public void run() {
				while (true) {
					g.drawImage(renderImage(), 0, 0, null);
					processKeys();
					checkRules();
				}
			}
		}.start();
	}
	
	
	private static final long COLOR_ROTATION_SPEED = 1000000000L;
	private BufferedImage renderImage() {
		BufferedImage bf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics bg = bf.getGraphics();

		
		IntStream.range(0, Map.width).forEach(i -> {
			IntStream.range(0, Map.height).forEach(j -> {
				switch (Map.block[i][j]) {
				case OPEN:
					break;
				case CLOSED: {
					int I = i * BLOCK_SIZE;
					int J = j * BLOCK_SIZE;

					float mod1 = ((float)3*(i+j)) / ((float)(MAP_X + MAP_Y));
					float mod2 = ((float) (GAME_TIME % COLOR_ROTATION_SPEED)) / (float) COLOR_ROTATION_SPEED ;
					float mod3 = mod1 + mod2;
					float off1 = 1.0f / 3.0f;
					float off2 = 2.0f / 3.0f;
					Color c = new Color( mod3 % 1.0f, (mod3 + off1) % 1.0f, (mod3 + off2) % 1.0f);
					
					
					
					bg.setColor(c);
					bg.fillRect(J, I, BLOCK_SIZE, BLOCK_SIZE);
					bg.setColor(Color.WHITE);

					break;
				}
				case GOAL: {
					int I = i * BLOCK_SIZE;
					int J = j * BLOCK_SIZE;
					bg.drawArc(J, I, BLOCK_SIZE, BLOCK_SIZE, 0, 360);
					break;
				}
				default:
					throw new RuntimeException("Undefined block!");
				}
			});
		});

		Vec loc = Camera.loc.multN(BLOCK_SIZE);
		VecDraw.Circle(bg, loc.addN(Vec.square(-BLOCK_SIZE / 3)), Vec.square(BLOCK_SIZE));

		Vec p1 = loc.addN(Camera.dir.multN(Vec.square(BLOCK_SIZE / 3).mag()));
		Vec p2 = loc.addN(Camera.dir.multN(BLOCK_SIZE / 3).rotate((float) Math.PI / 4));
		Vec p3 = loc.addN(Camera.dir.multN(BLOCK_SIZE / 3).rotate(-(float) Math.PI / 4));
		VecDraw.Line(bg, loc, p1);
		VecDraw.Line(bg, p1, p2);
		VecDraw.Line(bg, p1, p3);

		bg.fillRect(0, 0, HEIGHT, 25);
		bg.setColor(Color.BLUE);
		String s = String.format(" MOVE_SPEED : %3.15f ... %s", MOVE_SPEED, (view3D)? "3d Mode":"Overhead Mode");
		bg.drawString(s, 15, 15);
		
		return bf;
	}

	public void paint(final Graphics g) {
		/* undefined */}

	private long GAME_TIME 		 = System.nanoTime();
	private float MOVE_ACC  	 = 0.000000000011f;
	private float MOVE_DRAG      = 0.000000000005f;
	private float MOVE_SPEED	 = 0.0f;
	private float MOVE_SPEED_MAX = 0.000000020f;
	private float TURN_SPEED 	 = 0.01f;

	
	public void processKeys() {
		long time_now = System.nanoTime();
		long time_step = time_now - GAME_TIME;
		GAME_TIME = time_now;

		while (Input.hasEvent()) {
			switch( Input.consumeEvent() ){
				case Input.K_TOGGLE_RENDER_BCK : 
					view3D = !view3D;
					break;
				case Input.K_TOGGLE_RENDER_FWD :
					view3D = !view3D;
					break;
			}
		}
		
		Vec hist = new Vec(Camera.loc);
		if (Input.Up())
			MOVE_SPEED += MOVE_ACC;

		if (Input.Down())
			MOVE_SPEED -= MOVE_ACC;
		
		MOVE_SPEED -= Math.signum(MOVE_SPEED) * MOVE_DRAG;
		MOVE_SPEED = Math.max(-MOVE_SPEED_MAX, MOVE_SPEED);
		MOVE_SPEED = Math.min( MOVE_SPEED_MAX, MOVE_SPEED);
		
		Camera.loc.add(Camera.dir.multN( MOVE_SPEED * time_step));

		if (Map.block[(int) Camera.loc.y()][(int) Camera.loc.x()] == Block.CLOSED) {
			Camera.loc = hist;
			MOVE_SPEED = 0.0f;
		}

//		if (Input.StrafeLeft())
//			Camera.loc.x(Camera.loc.x() - MOVE_STEP * (float) time_step);

//		if (Input.StrafeRight())
//			Camera.loc.x(Camera.loc.x() + MOVE_STEP * (float) time_step);

		if (Input.TurnLeft())
			Camera.dir.rotate(-TURN_SPEED);

		if (Input.TurnRight())
			Camera.dir.rotate(+TURN_SPEED);

	
	}
	
	private void checkRules() {
		// TODO Auto-generated method stub
		
	}

}
