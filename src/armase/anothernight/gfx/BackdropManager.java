package armase.anothernight.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import armase.anothernight.Handler;

public class BackdropManager {
	
	private Handler handler;
	
	// TODO : animate, ImageIO.read doesn't read, idk why
//	private Animation animMenu, animCamp, animFight;
//	private Animation currentBackdropBLA;

	private BufferedImage currentBackdropImage;

	public BackdropManager(Handler handler) {
		this.handler = handler;
		
		// Animations - set anim speed in ms here
		int animSpeed = 300;
//		animMenu = new Animation(animSpeed, Assets.mainMenuBackdrop);
//		animCamp = new Animation(animSpeed, Assets.mainMenuBackdrop);
//		animFight = new Animation(animSpeed, Assets.mainMenuBackdrop);
//		currentBackdrop = animMenu;
		currentBackdropImage = Assets.placeholder;
	}
	
	public void tick() {
//		currentBackdrop.tick();
	}
	
	public void render(Graphics g) {
//		g.drawImage(getCurrentAnimationFrame(), 0, 0, handler.getWidth(), handler.getHeight(), null);
		g.drawImage(currentBackdropImage, 0, 0, handler.getWidth(), handler.getHeight(), null);
	}
	
	// ### GETTERS & SETTERS

	public void setCurrentBackdropImage(BufferedImage currentBackdropImage) {
		this.currentBackdropImage = currentBackdropImage;
	}
}
