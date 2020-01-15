package armase.anothernight;

import armase.anothernight.properties.Props;

public class Launcher {

	public static void main(String[] args) {
		Game game = new Game(Props.GAME_TITLE + " - " + Props.VERSION,
							 Props.WINDOW_WIDTH, Props.WINDOW_HEIGHT);
		game.start();
	}
}
