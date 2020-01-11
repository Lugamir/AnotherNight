package armase.anothernight.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import armase.anothernight.gfx.Animation;
import armase.anothernight.gfx.Assets;

public class AnimatedLogo extends UIObject {
	private Animation anim;
	private ClickListener clicker;

	public AnimatedLogo(float x, float y, int width, int height, ClickListener clicker) {
		super(x, y, width, height);
		this.clicker = clicker;
		anim = new Animation(100, Assets.logo);
	}
	
	public AnimatedLogo(float x, float y, int width, int height) {
		super(x, y, width, height);
		this.clicker = null;
		anim = new Animation(100, Assets.logo);
	}

	@Override
	public void tick() {
		anim.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentNightAnimationFrame(),
				(int) x, (int) y, width, height, null);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}
	
	private BufferedImage getCurrentNightAnimationFrame() {
		return anim.getCurrentFrame();
	}
}
