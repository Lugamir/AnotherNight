package armase.anothernight.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	// final sprite size for cropping
	private static final int width = 32, height = 32;

	// Placeholder
	public static BufferedImage placeholder;
	
	// Backdrops // TODO : Animate
	public static BufferedImage mainMenuBackdrop, campSceneBackdrop, fightSceneBackdrop;

	// Menu Elements
	public static BufferedImage[] btn_start, btn_scoreboard, btn_quit;
	
	// Creatures
	public static BufferedImage player;
	public static BufferedImage[] player_idle;
	public static BufferedImage[] player_attack;
	public static BufferedImage[] player_death;
	public static BufferedImage mushdrool;
	public static BufferedImage[] mushdrool_idle;
	public static BufferedImage[] mushdrool_attack;
	public static BufferedImage[] mushdrool_death;
	public static BufferedImage doggo;
	public static BufferedImage[] doggo_idle;
	public static BufferedImage[] doggo_attack;
	public static BufferedImage[] doggo_death;
	public static BufferedImage skeleton;
	public static BufferedImage[] skeleton_idle;
	public static BufferedImage[] skeleton_attack;
	public static BufferedImage[] skeleton_death;
	
	// TODO : fill with missing sprites & backdrops, structure is ready

	public static void init() {

		// Sheets
		SpriteSheet missingSheet = new SpriteSheet(ImageLoader.loadImage("/textures/img_missing_16.png"));
		SpriteSheet buttonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/buttons.png"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/mushdrool.png"));
		SpriteSheet mushdroolSheet = new SpriteSheet(ImageLoader.loadImage("/textures/mushdrool.png"));
		SpriteSheet doggoSheet = new SpriteSheet(ImageLoader.loadImage("/textures/dundoggo.png"));
		SpriteSheet skeletonSheet = new SpriteSheet(ImageLoader.loadImage("/textures/skeleton.png"));

		// Placeholder
		placeholder = ImageLoader.loadImage("/textures/placeholder.jpg");
		
		// Backdrops
		mainMenuBackdrop = ImageLoader.loadImage("/textures/forest.png");
		campSceneBackdrop = ImageLoader.loadImage("/textures/nightsky.png");
//		fightSceneBackdrop[0] = ImageLoader.loadImage("/textures/forest.png");
		
		// Button Sheet Crops
		btn_start = new BufferedImage[2];
		btn_scoreboard = new BufferedImage[2];
		btn_quit = new BufferedImage[2];
		btn_start[0] =		buttonSheet.crop(0, 0, width * 4, height * 2);
		btn_start[1] =		buttonSheet.crop(width * 4, 0, width * 4, height * 2);
		btn_scoreboard[0] =	buttonSheet.crop(0, height * 2, width * 4, height * 2);
		btn_scoreboard[1] =	buttonSheet.crop(width * 4, height * 2, width * 4, height * 2);
		btn_quit[0] =		buttonSheet.crop(0, height * 4, width * 4, height * 2);
		btn_quit[1] =		buttonSheet.crop(width * 4, height * 4, width * 4, height * 2);
		
		// Player Sheet Crops
		player_idle = new BufferedImage[4];
		player_attack = new BufferedImage[4];
		player_death = new BufferedImage[4];
		for (int i = 0; i < 4; i++)
			player_idle[i] = missingSheet.crop(width * i, 0, width, height);
		for (int i = 0; i < 4; i++)
			player_attack[i] = missingSheet.crop(width * i, 0, width, height);
		for (int i = 0; i < 4; i++)
			player_death[i] = missingSheet.crop(width * i, 0, width, height);
		player = missingSheet.crop(0, 0, width, height);
		
		// Mushdrool Sheet Crops
		mushdrool_idle = new BufferedImage[4];
		mushdrool_attack = new BufferedImage[4];
		mushdrool_death = new BufferedImage[4];
		for (int i = 0; i < 4; i++)
			mushdrool_idle[i] = mushdroolSheet.crop(width * i, 0, width, height);
		for (int i = 0; i < 4; i++)
			mushdrool_attack[i] = missingSheet.crop(width * i, 0, width, height);
		for (int i = 0; i < 4; i++)
			mushdrool_death[i] = missingSheet.crop(width * i, 0, width, height);
		mushdrool = missingSheet.crop(0, 0, width, height);
		
		// Danger Doggo Sheet Crops
		doggo_idle = new BufferedImage[4];
		doggo_attack = new BufferedImage[4];
		doggo_death = new BufferedImage[4];
		for (int i = 0; i < 4; i++)
			doggo_idle[i] = doggoSheet.crop(width * i, 0, width, height);
		for (int i = 0; i < 4; i++)
			doggo_attack[i] = missingSheet.crop(width * i, 0, width, height);
		for (int i = 0; i < 4; i++)
			doggo_death[i] = missingSheet.crop(width * i, 0, width, height);
		doggo = missingSheet.crop(0, 0, width, height);
		
		// Sneaky Skeleton Sheet Crops
		skeleton_idle = new BufferedImage[4];
		skeleton_attack = new BufferedImage[4];
		skeleton_death = new BufferedImage[4];
		for (int i = 0; i < 4; i++)
			skeleton_idle[i] = skeletonSheet.crop(width * i, 0, width, height);
		for (int i = 0; i < 4; i++)
			skeleton_attack[i] = missingSheet.crop(width * i, 0, width, height);
		for (int i = 0; i < 4; i++)
			skeleton_death[i] = missingSheet.crop(width * i, 0, width, height);
		skeleton = missingSheet.crop(0, 0, width, height);

		/* Reference
		// Sheets
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tiles.png"));
		SpriteSheet blueGuySheet = new SpriteSheet(ImageLoader.loadImage("/textures/blueguy.png"));
		
		// Tile Sheet Crops (static)
		dirt = tileSheet.crop(width * 0, 0, width, height);
		grass = tileSheet.crop(width * 1, 0, width, height);
		stone = tileSheet.crop(width * 2, 0, width, height);

		*/
	}
}
