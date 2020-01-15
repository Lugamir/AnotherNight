package armase.anothernight.properties;

import java.io.File;

public class Props  {
	public static final int SCALING = 1; // TODO : implement
	
	public static final String GAME_TITLE = "Another Night";
	public static final String VERSION = "BETA v1.1";
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int FPS = 60;
	public static final String GAME_FOLDER_PATH = System.getProperty("user.home")
				+ File.separator + "Documents" + File.separator + "AnotherNight";
	
	public static final int DEFAULT_ANIM_SPEED = 300; // for 4 frame sprites
	public static final int UI_DISABLE_TIME = 300;
}
