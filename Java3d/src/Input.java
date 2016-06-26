import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

	public class Input implements KeyListener{

		/* Default key bindings. */
		private static int K_NULL				= 0; 
		private static int K_DOWN				= KeyEvent.VK_S;
		private static int K_DOWN_ALT 			= KeyEvent.VK_DOWN;
		private static int K_STRAFE_LEFT		= KeyEvent.VK_Q;
		private static int K_STRAFE_LEFT_ALT	= K_NULL;
		private static int K_STRAFE_RIGHT		= KeyEvent.VK_E;
		private static int K_STRAFE_RIGHT_ALT	= K_NULL;
		private static int K_TURN_LEFT			= KeyEvent.VK_A;
		private static int K_TURN_LEFT_ALT		= KeyEvent.VK_LEFT;
		private static int K_TURN_RIGHT 		= KeyEvent.VK_D;
		private static int K_TURN_RIGHT_ALT		= KeyEvent.VK_RIGHT;
		private static int K_UP 				= KeyEvent.VK_W;
		private static int K_UP_ALT 			= KeyEvent.VK_UP;
		public static boolean[] keyPressed = new boolean[1000]; 
		
		
		public static void bind( ) {
			//TODO - add key remapping code. 
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			System.out.println("Pressed " + arg0.getKeyCode());
			keyPressed[ arg0.getKeyCode() ] = true;
		}
	
		@Override
		public void keyReleased(KeyEvent arg0) {

			System.out.println("released " + arg0.getKeyCode());
		    keyPressed[ arg0.getKeyCode() ]	= false;
		}

		@Override
		public void keyTyped(KeyEvent arg0) {}
		
		public static boolean Up ( ){
			return ( keyPressed[ K_UP ] || keyPressed[ K_UP_ALT] );
		}
		
		public static boolean Down( ) {
			return ( keyPressed[ K_DOWN ] || keyPressed[ K_DOWN_ALT ]);
		}
		
		public static boolean StrafeLeft( ) { 
			return ( keyPressed[ K_STRAFE_LEFT ] || keyPressed[ K_STRAFE_LEFT_ALT ]);
		}
		
		public static boolean StrafeRight( ) {
			return ( keyPressed[ K_STRAFE_RIGHT ] || keyPressed[ K_STRAFE_RIGHT_ALT] );
		}

		public static boolean TurnLeft( ) { 
			return ( keyPressed[ K_TURN_LEFT ] || keyPressed[ K_TURN_LEFT_ALT ]);
		}
		
		public static boolean TurnRight( ) {
			return ( keyPressed[ K_TURN_RIGHT ] || keyPressed[ K_TURN_RIGHT_ALT] );
		}
	}
