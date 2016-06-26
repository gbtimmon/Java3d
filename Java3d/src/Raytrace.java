import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

public class Raytrace extends Canvas {

	// FIELDS
	private Graphics g;
	private Input inHandle;

	public static int WIDTH 		= 770;
	public static int HEIGHT 		= 790;
	public static int BLOCK_SIZE 	= 30;
	public static int MAP_X 		= 25; 
	public static int MAP_Y 		= 25;
	public static Vec BLOCK_VEC 	= Vec.square(BLOCK_SIZE);
    public static Vec GOAL          = new Vec( (float) (MAP_Y * Math.random()), (float) (MAP_X * Math.random()));
	public static class Camera {

		private Camera() {};

		static Vec loc = new Vec(5.0f, 5.0f);
		static Vec dir = new Vec(1.0f, 1.0f).unit();

		public static Camera byId(int num) {
			return new Camera();
		}
	}

	public static class Map {
		static int height = MAP_X;
		static int width  = MAP_Y;
		static Block block[][] = new Block[Map.width][Map.height];
	}

	enum Block {OPEN, CLOSED, GOAL }

	// METHODS
	public void start() {
		Dimension size = new Dimension(WIDTH, HEIGHT);

		IntStream.range(0, Map.width).forEach(i -> {
			IntStream.range(0, Map.height).forEach(j -> {
				Map.block[i][j] = (Math.random() < 0.5) ? Block.OPEN : Block.CLOSED;
			});
		});

		Map.block[4][4] = Block.OPEN;
		Map.block[4][5] = Block.OPEN;
		Map.block[5][4] = Block.OPEN;
		Map.block[5][5] = Block.OPEN;
		Map.block[5][6] = Block.GOAL;

		IntStream.range(0, MAP_X).forEach( i->{
			Map.block[0][i] 		= Block.CLOSED;
			Map.block[MAP_Y - 1][i] = Block.CLOSED;
		});
		
		IntStream.range(0, MAP_Y).forEach( i-> {
			Map.block[i][0] 	  = Block.CLOSED;
			Map.block[i][MAP_X-1] = Block.CLOSED;
		});

		
		this.inHandle = new Input();
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

	private BufferedImage renderImage() {
		BufferedImage bf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics bg = bf.getGraphics();

		IntStream.range(0, Map.width).forEach(i -> {
			IntStream.range(0, Map.height).forEach(j -> {
				switch (Map.block[i][j]) {
				case OPEN:
					break;
				case CLOSED:{
					int I = i * BLOCK_SIZE;
					int J = j * BLOCK_SIZE;
					bg.drawRect(J, I, BLOCK_SIZE, BLOCK_SIZE);
					bg.drawLine(J, I, J + BLOCK_SIZE, I + BLOCK_SIZE);
					bg.drawLine(J, I + BLOCK_SIZE, J + BLOCK_SIZE, I);

					break;
				}
				case GOAL : {
					int I = i * BLOCK_SIZE;
					int J = j * BLOCK_SIZE;
					bg.drawArc( I, J, BLOCK_SIZE, BLOCK_SIZE, 0, 360);
					break;
				}
				default:
					throw new RuntimeException("Undefined block!");
				}
			});
		});

		Vec loc = Camera.loc.multN(BLOCK_SIZE);
		VecDraw.Circle(bg, loc.addN(Vec.square(-BLOCK_SIZE / 6)), Vec.square(BLOCK_SIZE / 3));

		Vec p1 = loc.addN(Camera.dir.multN(Vec.square(BLOCK_SIZE / 6).mag()));
		Vec p2 = loc.addN(Camera.dir.multN(BLOCK_SIZE / 6).rotate((float) Math.PI / 4));
		Vec p3 = loc.addN(Camera.dir.multN(BLOCK_SIZE / 6).rotate(-(float) Math.PI / 4));
		VecDraw.Line(bg, loc, p1);
		VecDraw.Line(bg, p1, p2);
		VecDraw.Line(bg, p1, p3);

		return bf;
	}

	public void paint(final Graphics g) {
		/* undefined */}

	private long  GAME_TIME 	= System.nanoTime();
	private float MOVE_STEP 	= 0.000000004f;
	private float TURN_SPEED 	= 0.01f;

	public void processKeys() {
		long time_now = System.nanoTime();
		long time_step = time_now - GAME_TIME;
		GAME_TIME = time_now;

		Vec hist = new Vec(Camera.loc);
		if (Input.Up())
			Camera.loc.add(Camera.dir.multN(MOVE_STEP * time_step));

		if (Input.Down())
			Camera.loc.add(Camera.dir.multN(-MOVE_STEP * time_step));

		if( Input.StrafeLeft() )
			Camera.loc.x(Camera.loc.x() - MOVE_STEP * (float) time_step);

		if (Input.StrafeRight())
			Camera.loc.x(Camera.loc.x() + MOVE_STEP * (float) time_step);

		if (Input.TurnLeft())
			Camera.dir.rotate(-TURN_SPEED);

		if (Input.TurnRight())
			Camera.dir.rotate(+TURN_SPEED);

		if (Map.block[(int) Camera.loc.y()][(int) Camera.loc.x()] == Block.CLOSED) {
			Camera.loc = hist;
		}
	}
	
	
	public void checkRules( ){
	}
}