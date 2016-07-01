package com.gg;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gg.Console.Console;
import com.gg.Console.RollingConsole;
import com.gg.Motivator.TessalatorMotivator;
import com.gg.Thing.Thing;
import com.gg.Thing.Vec;
import com.gg.Thing.Wall;

public class Game extends Canvas {

	private static final long serialVersionUID = 1L;

	private Graphics g;
	private Console console;
	private long GAME_TIME;
	private long GAME_STEP;

	// METHODS
	public void start() {

		this.g = getGraphics();
		this.console = new RollingConsole(15); 
		

		Wall w = new Wall( new Vec( 200f, 200f), new Vec(200f, 400f), 10);
		w.addMotivator(new TessalatorMotivator(0.0075f, 0.00005f));
		Map.registerObejct(w);
		
		new Thread() {
			public void run() {
				GAME_TIME = System.nanoTime();
				while (true) {
					GAME_STEP = System.nanoTime() - GAME_TIME;
					GAME_TIME = GAME_TIME + GAME_STEP;
					GAME_STEP /= 10000;
					g.drawImage(renderImage(), 0, 0, null);
					
					for( Thing t : Map.list())
						t.heartbeat(GAME_STEP);
					
					handleGlobalInput();
				}
			}
		}.start();
	}

	boolean DRAW_GRID = true;
	int DRAW_GRID_STEP = 70;

	private BufferedImage renderImage() {
		BufferedImage bf = new BufferedImage(Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT + 50 + Config.INFO_PANEL_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics bg = bf.getGraphics();

		if (DRAW_GRID) {
			for (int i = DRAW_GRID_STEP; i < Map.MAP_SIZE_Y; i = i + DRAW_GRID_STEP) {
				bg.drawLine(0, i, (int) Map.MAP_SIZE_X, i);
				bg.setColor(Color.BLACK);
				bg.fillRect(5, i - 3, 38, i + 3);
				bg.setColor(Color.WHITE);
				bg.drawString(String.format("(y=%03d)", i), 5, i + 3);
			}
			for (int i = DRAW_GRID_STEP; i < Map.MAP_SIZE_Y; i = i + DRAW_GRID_STEP) {
				bg.drawLine(i, 0, i, (int) Map.MAP_SIZE_Y);
				bg.setColor(Color.BLACK);
				bg.fillRect(i - 15, 5, i + 15, 10);
				bg.setColor(Color.WHITE);
				bg.drawString(String.format("(x=%03d)", i), i - 15, 15);
			}
		}

		for (Thing go : Map.list()) {
			go.getMesh().draw(bg);
		}

		return bf;
	}

	private void handleGlobalInput() {
		if (Input.hasEvent()) {
			switch (Input.consumeEvent()) {
			case Input.K_DECREASE_GRID:
				DRAW_GRID_STEP = Math.max(5, DRAW_GRID_STEP - 1);
				break;
			case Input.K_INCREASE_GRID:
				DRAW_GRID_STEP = Math.max(5, DRAW_GRID_STEP + 1);
				break;
			case Input.K_TOGGLE_RENDER_BCK:
				break;
			case Input.K_TOGGLE_RENDER_FWD:
				break;
			default:
				System.out.println("Unmapped event key ");
			}

		}

	}

	/** UNDEFINED **/
	public void paint(final Graphics g) {
	};

};