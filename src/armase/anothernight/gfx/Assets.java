package armase.anothernight.gfx;

import java.awt.image.BufferedImage;

// TODO : This file is an example, use it as a template or delete it
// LEAVE IT UNTIL ANOTHER FILE ARRIVES IN THIS PACKAGE (Git is whiny)

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage dirt, grass, stone; // Tiles
	public static BufferedImage tree, rock; // Static Entities
	public static BufferedImage blueGuy, redGuy; // Creatures
	public static BufferedImage[] blueGuy_down, blueGuy_up, blueGuy_left, blueGuy_right;
	public static BufferedImage[] redGuy_down, redGuy_up, redGuy_left, redGuy_right;
	public static BufferedImage[] btn_start, btn_quit;
	
	public static void init() {
		
		// Sheets
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tiles.png"));
		SpriteSheet blueGuySheet = new SpriteSheet(ImageLoader.loadImage("/textures/blueguy.png"));
		
		// Tile Sheet Crops (static)
		dirt = tileSheet.crop(width * 0, 0, width, height);
		grass = tileSheet.crop(width * 1, 0, width, height);
		stone = tileSheet.crop(width * 2, 0, width, height);
		
		// BlueGuy Sheet Crops (animated)
		blueGuy_down = new BufferedImage[2];
		blueGuy_up = new BufferedImage[2];
		blueGuy_left = new BufferedImage[2];
		blueGuy_right = new BufferedImage[2];
		blueGuy_down[0] = 	blueGuySheet.crop(0, 0, width, height);
		blueGuy_down[1] = 	blueGuySheet.crop(width, 0, width, height);
		blueGuy_up[0] = 	blueGuySheet.crop(0, height, width, height);
		blueGuy_up[1] = 	blueGuySheet.crop(width, height, width, height);
		blueGuy_left[0] = 	blueGuySheet.crop(0, height * 2, width, height);
		blueGuy_left[1] = 	blueGuySheet.crop(width, height * 2, width, height);
		blueGuy_right[0] = 	blueGuySheet.crop(0, height * 3, width, height);
		blueGuy_right[1] = 	blueGuySheet.crop(width, height * 3, width, height);
		blueGuy = 			blueGuySheet.crop(0, height * 4, width, height);
	}
}
