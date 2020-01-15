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

		currentBackdrop = Assets.placeholder;
		animBackdrop = new Animation(Assets.placeholders);
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

	public void setCurrentBackdrop(BufferedImage currentBackdropImage) {
		this.currentBackdrop = currentBackdropImage;
		isAnimated = false;
		
	}
	
	public void setCurrentBackdrop(BufferedImage[] animBackdrop) {
		this.animBackdrop = new Animation(animBackdrop);
		isAnimated = true;
	}
}
