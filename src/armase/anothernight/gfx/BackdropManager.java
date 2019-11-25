package armase.anothernight.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import armase.anothernight.Handler;

public class BackdropManager {
	
	private Handler handler;

	private BufferedImage currentBackdrop;
	private Animation animBackdrop;
	private boolean isAnimated = false;

	public BackdropManager(Handler handler) {
		this.handler = handler;
		
		// Animations - set anim speed in ms here
		int animSpeed = 300;

		currentBackdrop = Assets.placeholder;
		animBackdrop = new Animation(animSpeed, Assets.nightBackdrop);
	}
	
	public void tick() {
		if(isAnimated)
			animBackdrop.tick();
	}
	
	public void render(Graphics g) {
		if(isAnimated)
			g.drawImage(animBackdrop.getCurrentFrame(), 0, 0, handler.getWidth(), handler.getHeight(), null);
		else
			g.drawImage(currentBackdrop, 0, 0, handler.getWidth(), handler.getHeight(), null);
	}
	
	// ### GETTERS & SETTERS

	public void setCurrentBackdropImage(BufferedImage currentBackdropImage) {
		this.currentBackdrop = currentBackdropImage;
		isAnimated = false;
		
	}
	
	public void setCurrentBackdropImage(BufferedImage[] animBackdrop) {
		this.animBackdrop = new Animation(300, animBackdrop);
		isAnimated = true;
	}
}
