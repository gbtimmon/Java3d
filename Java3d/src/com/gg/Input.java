package com.gg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class Input implements KeyListener {

	Input() {
	}

	/* Default key bindings. */
	public static final int K_NULL = 0;

	public static final int K_DOWN = KeyEvent.VK_S;
	public static final int K_DOWN_ALT = KeyEvent.VK_DOWN;

	public static final int K_STRAFE_LEFT = KeyEvent.VK_Q;
	public static final int K_STRAFE_LEFT_ALT = K_NULL;

	public static final int K_STRAFE_RIGHT = KeyEvent.VK_E;
	public static final int K_STRAFE_RIGHT_ALT = K_NULL;

	public static final int K_TURN_LEFT = KeyEvent.VK_A;
	public static final int K_TURN_LEFT_ALT = KeyEvent.VK_LEFT;

	public static final int K_TURN_RIGHT = KeyEvent.VK_D;
	public static final int K_TURN_RIGHT_ALT = KeyEvent.VK_RIGHT;

	public static final int K_UP = KeyEvent.VK_W;
	public static final int K_UP_ALT = KeyEvent.VK_UP;

	public static final int K_TOGGLE_RENDER_FWD = '>';
	public static final int K_TOGGLE_RENDER_BCK = '<';

	public static boolean[] keyPressed = new boolean[1000];

	public static void bind() {
		// TODO - add key remapping code.
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		keyPressed[arg0.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		System.out.println("released " + arg0.getKeyCode());
		keyPressed[arg0.getKeyCode()] = false;
	}

	public static boolean Up() {
		return (keyPressed[K_UP] || keyPressed[K_UP_ALT]);
	}

	public static boolean Down() {
		return (keyPressed[K_DOWN] || keyPressed[K_DOWN_ALT]);
	}

	public static boolean StrafeLeft() {
		return (keyPressed[K_STRAFE_LEFT] || keyPressed[K_STRAFE_LEFT_ALT]);
	}

	public static boolean StrafeRight() {
		return (keyPressed[K_STRAFE_RIGHT] || keyPressed[K_STRAFE_RIGHT_ALT]);
	}

	public static boolean TurnLeft() {
		return (keyPressed[K_TURN_LEFT] || keyPressed[K_TURN_LEFT_ALT]);
	}

	public static boolean TurnRight() {
		return (keyPressed[K_TURN_RIGHT] || keyPressed[K_TURN_RIGHT_ALT]);
	}

	/* event buffer */
	private static int KEY_BUFFER_SIZE = 20;
	public static int KEY_BUFFER_EMPTY = -99;
	private static int[] keyBuffer = new int[KEY_BUFFER_SIZE];
	private static int keyBufferReadIndex = 0;
	private static int keyBufferWriteIndex = 0;

	public static boolean hasEvent() {
		return (keyBufferReadIndex != keyBufferWriteIndex);
	}

	public static int consumeEvent() throws BufferUnderflowException {
		if (keyBufferReadIndex == keyBufferWriteIndex)
			throw new BufferUnderflowException();

		int cd = keyBuffer[keyBufferReadIndex];
		keyBufferReadIndex = (keyBufferReadIndex + 1) % KEY_BUFFER_SIZE;
		return cd;
	}

	public static void emitEvent(int code) throws BufferOverflowException {
		int newWrite = (keyBufferWriteIndex + 1) % KEY_BUFFER_SIZE;

		if (newWrite == keyBufferReadIndex)
			throw new BufferOverflowException();

		keyBuffer[keyBufferWriteIndex] = code;
		keyBufferWriteIndex = newWrite;
	}

	@Override
	public void keyTyped(KeyEvent k) {
		int cd = k.getKeyChar();
		if (cd == K_TOGGLE_RENDER_BCK || cd == K_TOGGLE_RENDER_FWD) {
			emitEvent(k.getKeyChar());

			/*************************/
			/** PRINT BUFFER DEBUG **/
			/*************************/
			/*
			 * Arrays.stream( keyBuffer ).forEach(n -> {
			 * System.out.print(String.format("%3d ", n)); });
			 * System.out.println();
			 * 
			 * IntStream.range(0, KEY_BUFFER_SIZE).forEach( n->{ if( n ==
			 * keyBufferReadIndex && n == keyBufferWriteIndex){
			 * System.out.print("(o) "); }else if( n == keyBufferWriteIndex){
			 * System.out.print("(e) "); }else if ( n == keyBufferReadIndex){
			 * System.out.print("(s) "); }else{ System.out.print("    "); } });
			 * System.out.println();
			 */
		}
	}
}
