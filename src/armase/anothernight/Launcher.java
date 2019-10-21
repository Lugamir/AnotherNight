package armase.anothernight;

import armase.anothernight.Game;

public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("AnotherNight", 600, 400);
		game.start();
	}
}
