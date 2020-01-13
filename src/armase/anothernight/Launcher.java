package armase.anothernight;

public class Launcher {
	private static String gameTitle = "Another Night";
	private static String version = "BETA v1.1";
	
	public static void main(String[] args) {
		Game game = new Game(gameTitle + " - " + version, 800, 600);
		game.start();
	}
}
