package armase.anothernight.states;

import java.awt.Graphics;

import armase.anothernight.Handler;

// TODO : This file is an example, use it as a template or delete it
// LEAVE IT UNTIL ANOTHER FILE ARRIVES IN THIS PACKAGE (Git is whiny)

public abstract class State {
	
	// Game State Manager
	private static State currentState = null;
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
}
