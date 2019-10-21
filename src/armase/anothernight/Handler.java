package armase.anothernight;

import armase.anothernight.input.KeyManager;

// TODO : This file is an example, use it as a template or delete it
// LEAVE IT UNTIL ANOTHER FILE ARRIVES IN THIS PACKAGE (Git is whiny)

public class Handler {
	
	private Game game;
	
	public Handler(Game game) {
		this.game = game;
	}

	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
